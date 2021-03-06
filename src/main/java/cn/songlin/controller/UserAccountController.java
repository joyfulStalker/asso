package cn.songlin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.common.anno.Monitor;
import cn.songlin.common.constant.ConstantUtil;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.MyStringUtils;
import cn.songlin.dto.user.UserAccountDto;
import cn.songlin.dto.user.UserChangePwdDto;
import cn.songlin.dto.user.UserLoginDto;
import cn.songlin.dto.user.UserQueryDto;
import cn.songlin.service.SensitiveWordsService;
import cn.songlin.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("userAccount")
@Api("用户管理")
@SuppressWarnings(value = { "all" })
public class UserAccountController {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserAccountService userAaccountService;

	@Autowired
	private SensitiveWordsService sensitiveWordsService;

	@Autowired
	private Environment env;

	@GetMapping("userDetail")
	@ApiOperation(value = "用户列表")
	@Monitor
	public ResponseBeanResult userDetail() {
		return userAaccountService.userDetail();
	}

	@PostMapping("changeProfile")
	@ApiOperation(value = "更改个人信息")
	@Monitor
	public ResponseBeanResult changeProfile(@RequestBody UserAccountDto accountDto) {
		userAaccountService.changeProfile(accountDto);
		return new ResponseBeanResult();
	}

	@PostMapping("changePwd")
	@ApiOperation(value = "更改密码")
	@Monitor
	public ResponseBeanResult changePwd(@RequestBody UserChangePwdDto pwdDto) {
		userAaccountService.changePwd(pwdDto);
		return new ResponseBeanResult();
	}

	@GetMapping("userList")
	@ApiOperation(value = "用户列表")
	@Monitor
	public ResponsePageResult userList(UserQueryDto queryDto) {
		return userAaccountService.userList(queryDto);
	}

	@PostMapping("register")
	@Monitor
	// @TrackLog // 记录用户支付足迹
	@ApiOperation(value = "用户注册")
	public ResponseBeanResult register(UserAccountDto userAccountDto) {
		// 过滤敏感词 含有敏感词禁止写入
		String nickName = sensitiveWordsService.useWords(userAccountDto.getNickName(),
				ConstantUtil.SENSITIVEWORD_DEALER_CODE);
		String name = sensitiveWordsService.useWords(userAccountDto.getName(), ConstantUtil.SENSITIVEWORD_DEALER_CODE);
		if (!StringUtils.isEmpty(nickName) || !StringUtils.isEmpty(name)) {
			throw AssoException.HIT_SENSITIVEWORD;// 含有敏感词禁止写入，提示请遵守社群规范
		}
		userAaccountService.register(userAccountDto);
		return new ResponseBeanResult();
	}

	@PostMapping("login")
	@ApiOperation(value = "用户登录")
	@Monitor
	public ResponseBeanResult login(@RequestBody UserLoginDto userLoginDto) {
		return new ResponseBeanResult(userAaccountService.login(userLoginDto));
	}

	@PostMapping("testProp")
	@Monitor
	@ApiOperation(value = "测试system")
	public ResponseBeanResult<Map> testProp() {
		Map<String, Object> map = new HashMap<>();

		Properties properties = System.getProperties();
		Map<String, String> getenv = System.getenv();
		String property = env.getProperty("mytest.name");
		String property1 = env.getProperty(property);
		map.put("prop", properties);
		map.put("env", getenv);
		map.put("property", property);
		map.put("property1", property1);
		return new ResponseBeanResult<>(map);
	}

	@PostMapping("testMyUtil")
	@Monitor
	@ApiOperation(value = "测试我的工具类")
	public ResponseBeanResult<Map> testMyUtil() {
		Map<String, Object> map = new HashMap<>();
		String name = "name";
		String phone = "15206668888";
		String n = MyStringUtils.dealSensitivePhone(name, phone);
		map.put("不为空", n);
		name = null;
		n = MyStringUtils.dealSensitivePhone(name, phone);
		n = MyStringUtils.dealSensitivePhone(null, phone);
		map.put("为空", n);
		return new ResponseBeanResult<>(map);
	}

	@PostMapping("logout")
	@ApiOperation(value = "用户退出登录")
	@Monitor
	public ResponseBeanResult logout() {
		userAaccountService.logout();
		return new ResponseBeanResult("ok");
	}
	@PostMapping("timerClearInvalidUser")
	@ApiOperation(value = "定时清除过期用户")
	@Monitor
	public ResponseBeanResult timerClearInvalidUser() {
		userAaccountService.timerClearInvalidUser();
		return new ResponseBeanResult("ok");
	}
	
	@PostMapping("flushUserLoginStatus")
	@ApiOperation(value = "刷新用户登录状态")
	@Monitor
	public ResponseBeanResult flushUserLoginStatus() {
		return new ResponseBeanResult(userAaccountService.flushUserLoginStatus());
	}
}
