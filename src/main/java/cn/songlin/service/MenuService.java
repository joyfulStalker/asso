package cn.songlin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.songlin.common.exception.AssoException;
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

}
