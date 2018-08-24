package cn.songlin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.annotation.Monitor;
import cn.songlin.comm.ConstantUtil;
import cn.songlin.dto.UserAccountDto;
import cn.songlin.dto.UserLoginDto;
import cn.songlin.entity.UserAccount;
import cn.songlin.exception.CommunityException;
import cn.songlin.service.SensitiveWordsService;
import cn.songlin.service.UserAaccountService;
import cn.songlin.utils.MyStringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("userAccount")
@Api("用户管理")
@SuppressWarnings(value = { "all" })
public class UserAccountController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserAaccountService userAaccountService;

	@Autowired
	private SensitiveWordsService sensitiveWordsService;
	
	@Autowired
	private Environment env;

	@PostMapping("register")
	@Monitor
	@ApiOperation(value = "用户注册")
	public ResponseEntity<Void> register(UserAccountDto userAccountDto) {
		// 过滤敏感词 含有敏感词禁止写入
		String nickName = sensitiveWordsService.useWords(userAccountDto.getNickName(),
				ConstantUtil.SENSITIVEWORD_DEALER_CODE);
		String name = sensitiveWordsService.useWords(userAccountDto.getName(), ConstantUtil.SENSITIVEWORD_DEALER_CODE);
		if (!StringUtils.isEmpty(nickName) || !StringUtils.isEmpty(name)) {
			throw new CommunityException().HIT_SENSITIVEWORD;// 含有敏感词禁止写入，提示请遵守社群规范
		}
		userAaccountService.register(userAccountDto);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("login")
	@Monitor
	@ApiOperation(value = "用户登录")
	public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
		UserAccount userAccount = userAaccountService.login(userLoginDto);
		if (userAccount != null) {// 登陆成功
			// 写入缓存
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(120);// 设置30s
			session.setAttribute("sessionId", userAccount);
			return new ResponseEntity<>("1", HttpStatus.OK);
		}
		return new ResponseEntity<>("0", HttpStatus.OK);
	}

	@PostMapping("testProp")
	@Monitor
	@ApiOperation(value = "测试system")
	public ResponseEntity<Map> testProp() {
		Map<String,Object> map = new HashMap<>();
		
		Properties properties = System.getProperties();
		Map<String, String> getenv = System.getenv();
		String property = env.getProperty("mytest.name");
		String property1 = env.getProperty(property);
		map.put("prop", properties);
		map.put("env", getenv);
		map.put("property", property);
		map.put("property1", property1);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("testMyUtil")
	@Monitor
	@ApiOperation(value = "测试我的工具类")
	public ResponseEntity<Map> testMyUtil() {
		Map<String,Object> map = new HashMap<>();
		String name = "name";
		String phone = "15206668888";
		String n = MyStringUtils.dealSensitivePhone(name, phone);
		map.put("不为空", n);
		name = null;
		n = MyStringUtils.dealSensitivePhone(name, phone);
		n = MyStringUtils.dealSensitivePhone(null, phone);
		map.put("为空", n);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
