package cn.songlin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.common.anno.Monitor;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.dto.menu.MenuListDto;
import cn.songlin.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("menu")
@Api("动态路由")
@ResponseBody
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	@GetMapping("/menuList")
	@Monitor
	@ApiOperation(value = "菜单列表查询--加载所有")
	public ResponseBeanResult<List<MenuListDto>> menuList() {
		List<MenuListDto> menuList = menuService.menuList();
		return new ResponseBeanResult<>(menuList);
	}
}
