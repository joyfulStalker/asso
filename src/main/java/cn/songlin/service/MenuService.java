package cn.songlin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.dto.menu.MenuAddDto;
import cn.songlin.dto.menu.MenuConfListDto;
import cn.songlin.dto.menu.MenuDetailDto;
import cn.songlin.dto.menu.MenuListDto;
import cn.songlin.entity.TtMenu;
import cn.songlin.mapper.TtMenuMapper;

/**
 * vue动态路由
 * 
 * @author liusonglin
 * @date 2018年11月8日
 */

@Service
@Transactional
public class MenuService {

	@Autowired
	private TtMenuMapper menuMapper;

	public List<MenuListDto> menuList() {

		TtMenu record = new TtMenu();
		// 设置查询条件
		record.setDeleteFlag(1);
		List<TtMenu> list = menuMapper.select(record);

		if (null == list || list.size() == 0) {
			// 没有配置菜单
			throw AssoException.PLE_CONF_MENU;
		}
		List<MenuListDto> menuList = new ArrayList<>();
		getSortMenu(list, menuList);
		return menuList;
	}

	private void getSortMenu(List<TtMenu> list, List<MenuListDto> menuList) {
		for (TtMenu menu : list) {
			MenuListDto menuDto = new MenuListDto();
			BeanUtils.copyProperties(menu, menuDto);
			menuDto.setMenuShow(menu.getMenuShow().intValue() == 1 ? true : false);
			menuDto.setLeaf(menu.getLeaf().intValue() == 1 ? true : false);
			// 判断当前节点的父节点
			if (menu.getPid().longValue() == 0l) {// 一级节点
				menuList.add(menuDto);
			} else {
				// 遍历menuList，找寻其应该在的节点
				// 父节点在数据库的顺序一定在子节点之前
				addChildToParMenu(menuList, menuDto);
			}

		}
	}

	private void addChildToParMenu(List<MenuListDto> menuList, MenuListDto menuDto) {
		for (MenuListDto menuListDto : menuList) {
			if (menuDto.getPid().longValue() == menuListDto.getId()) {
				menuListDto.getChildren().add(menuDto);
			} else {
				if (null != menuListDto.getChildren() && menuListDto.getChildren().size() > 0) {
					addChildToParMenu(menuListDto.getChildren(), menuDto);
				}
			}
		}
	}

	public List<MenuConfListDto> menuConfList() {
		TtMenu record = new TtMenu();
		// 设置查询条件
		record.setDeleteFlag(1);
		List<TtMenu> list = menuMapper.select(record);

		if (null == list || list.size() == 0) {
			// 没有配置菜单
			throw AssoException.PLE_CONF_MENU;
		}
		List<MenuConfListDto> menuConfListDtos = new ArrayList<>();
		// 根节点
		MenuConfListDto e = new MenuConfListDto();
		e.setId(0l);
		e.setLabel("菜单配置");
		menuConfListDtos.add(e);
		getSortMenuConf(list, e.getChildren());
		return menuConfListDtos;
	}

	private void getSortMenuConf(List<TtMenu> list, List<MenuConfListDto> menuConfListDtos) {
		for (TtMenu menu : list) {
			MenuConfListDto menuDto = new MenuConfListDto();
			menuDto.setId(menu.getId());
			menuDto.setLabel(menu.getName());
			// 判断当前节点的父节点
			if (menu.getPid().longValue() == 0l) {// 一级节点
				menuConfListDtos.add(menuDto);
			} else {
				// 遍历menuList，找寻其应该在的节点
				// 父节点在数据库的顺序一定在子节点之前
				addChildToParMenu(menuConfListDtos, menu, menuDto);
			}

		}
	}

	private void addChildToParMenu(List<MenuConfListDto> menuConfListDtos, TtMenu menu, MenuConfListDto menuDto) {
		for (MenuConfListDto menuListDto : menuConfListDtos) {
			if (menu.getPid().longValue() == menuListDto.getId()) {
				menuListDto.getChildren().add(menuDto);
			} else {
				if (null != menuListDto.getChildren() && menuListDto.getChildren().size() > 0) {
					addChildToParMenu(menuListDto.getChildren(), menu, menuDto);
				}
			}
		}
	}

	public void manageMenu(MenuAddDto addDto) {
		// 数据校验
		checkMenuData(addDto);
		TtMenu record = new TtMenu();
		List<TtMenu> list = null;
		String userId = UserLocal.getLocalUser().getUserId();
		if (!"/".equals(addDto.getPath())) {
			record.setPath(addDto.getPath());
			record.setDeleteFlag(1);//可用状态
			list = menuMapper.select(record);
		}
		BeanUtils.copyProperties(addDto, record);
		if (null == addDto.getId() || 0 == addDto.getId().intValue()) {
			// 判断父节点是否为根节点 pid = 0
			if (0 != addDto.getPid().intValue()) {// 不是根节点
				// 判断父节点是否存在
				TtMenu pMenu = menuMapper.selectByPrimaryKey(addDto.getPid());
				if (null == pMenu || 2 == pMenu.getDeleteFlag()) {
					throw AssoException.PLE_CONF_MENU;
				}
			}
			// 判断路径是否有重复数据
			if (null != list && list.size() > 0) {
				throw AssoException.DUPLICATE_PATH;
			}
			record.setCreateBy(userId);
			menuMapper.insertSelective(record);
		} else {
			TtMenu pMenu = menuMapper.selectByPrimaryKey(addDto.getId());
			if (null == pMenu || 2 == pMenu.getDeleteFlag()) {
				throw AssoException.PLE_CONF_MENU;
			}
			// 判断路径是否有重复数据
			for (TtMenu ttMenu : list) {
				if (ttMenu.getId().intValue() != addDto.getId()) {
					throw AssoException.DUPLICATE_PATH;
				}
			}
			record.setUpdateBy(userId);
			record.setUpdateDate(new Date());
			menuMapper.updateByPrimaryKeySelective(record);
		}
	}

	private void checkMenuData(MenuAddDto addDto) {
		if (StringUtils.isEmpty(addDto.getPath())) {
			throw AssoException.NO_PATH;
		}
		if (StringUtils.isEmpty(addDto.getName())) {
			throw AssoException.NO_NAME;
		}
		if (StringUtils.isEmpty(addDto.getComponent())) {
			throw AssoException.NO_COMPONENT;
		}
		if (null == UserLocal.getLocalUser().getUserId()) {
			throw AssoException.PLE_LOGIN;
		}
	}

	public void remMenu(Long id) {
		TtMenu record = menuMapper.selectByPrimaryKey(id);
		if (null == record || 2 == record.getDeleteFlag().intValue()) {
			throw AssoException.PLE_CONF_MENU;
		}
		// 是否有子节点

		record.setDeleteFlag(2);// 删除
		menuMapper.updateByPrimaryKey(record);

	}

	public MenuDetailDto menuDetail(long id) {
		TtMenu ttMenu = menuMapper.selectByPrimaryKey(id);
		MenuDetailDto detailDto = new MenuDetailDto();
		BeanUtils.copyProperties(ttMenu, detailDto);
		return detailDto;
	}

}
