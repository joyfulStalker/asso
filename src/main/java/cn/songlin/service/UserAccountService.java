package cn.songlin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.songlin.common.dto.LocalUser;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.MyStringUtils;
import cn.songlin.common.utils.RedisUtil;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.common.utils.ValidateUtils;
import cn.songlin.dto.user.UserAccountDto;
import cn.songlin.dto.user.UserChangePwdDto;
import cn.songlin.dto.user.UserListDto;
import cn.songlin.dto.user.UserLoginDto;
import cn.songlin.dto.user.UserQueryDto;
import cn.songlin.entity.UserAccount;
import cn.songlin.mapper.UserAccountMapper;

@Service
@SuppressWarnings(value = { "all" })
public class UserAccountService {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

	@Autowired
	private UserAccountMapper mapper;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserLocal userLocal;

	@Value("${REDIS_USER_SESSIONID}")
	private String REDIS_USER_SESSIONID;

	@Value("${REDIS_USER_TOKEN_EXPIRE_TIME}")
	private Long REDIS_USER_TOKEN_EXPIRE_TIME;

	/**
	 * 用户注册
	 * 
	 * @author liusonglin
	 * @date 2018年7月30日
	 * @param userAccountDto
	 */

	public void register(UserAccountDto userAccountDto) {
		if (!ValidateUtils.checkMobilephone(userAccountDto.getMobilePhone())) {// 不是手机号
			throw AssoException.NOT_MOBLIEPHONE;// 不是手机号
		}
		if (userAccountDto.getEmail() != null && !ValidateUtils.checkEmail(userAccountDto.getEmail())) {
			throw AssoException.NOT_EMAIL;// 不是邮箱号
		}
		if (mapper.checkUserName(userAccountDto.getName()) > 0
				|| mapper.checkUserName(userAccountDto.getNickName()) > 0) {
			throw AssoException.NICK_EXIST;// 该昵称已存在
		}
		UserAccount record = new UserAccount();
		BeanUtil.copyProperties(userAccountDto, record);
		record.setCreateDate(new Date());// 创建时间
		record.setRegisterDate(record.getCreateDate());// 注册时间
		record.setUserId(MyStringUtils.getUUID());// 设置一个UUID用于用户id
		record.setPassword(MyStringUtils.getMd5(record.getPassword()));// 用户密码加密
		record.setUserType((byte) 1);// 1：普通用户 2：媒体用户 3：官方用户
		mapper.insertSelective(record);
	}

	public LocalUser login(UserLoginDto userLoginDto) {
		LocalUser userAccount = mapper.login(userLoginDto);// 支持昵称，用户名，电话登录
		if (null == userAccount) {
			throw AssoException.FAILED_LOGIN;
		}
		userAccount.setPassword(null);
		// 登陆成功
		UserAccount account = new UserAccount();
		BeanUtils.copyProperties(userAccount, account);
		account.setLastLoginDate(new Date());// 刷新最后登录时间
		mapper.updateByPrimaryKeySelective(account);
		// 生成唯一token，作为key，用户信息作为值，写到redis缓存中
		String token = MyStringUtils.getUUID();
		logger.info("token值:" + token);
		// 把token传给前端
		userAccount.setToken(token);
		//当前系统时间
		userAccount.setSystemTime(System.currentTimeMillis());
		redisUtil.hset(REDIS_USER_SESSIONID, token, userAccount);
		// 用于前端过期时间判断和定时清除过期用户判断
		userAccount.setTokenExpireTime(REDIS_USER_TOKEN_EXPIRE_TIME);
		return userAccount;
	}

	public ResponsePageResult userList(UserQueryDto queryDto) {
		PageHelper.startPage(queryDto.getPage(), queryDto.getRows());
		List<UserAccount> list = mapper.queryList(MyStringUtils.dealLikeStr(queryDto.getSearchKey()));
		List<UserListDto> res = new ArrayList<>();// 存放返回信息
		for (UserAccount userAccount : list) {
			UserListDto listDto = new UserListDto();
			BeanUtils.copyProperties(userAccount, listDto);
			listDto.setPassword(null);
			res.add(listDto);
		}
		PageInfo<UserAccount> page = new PageInfo<>(list);
		return new ResponsePageResult(res, page.getTotal());
	}

	public void changePwd(UserChangePwdDto pwdDto) {
		checkLogin();
		UserAccount account = mapper.selectByPrimaryKey(userLocal.getLocalUser().getId());
		if (!account.getPassword().equals(pwdDto.getOldPassword())) {
			throw AssoException.ERR_OLD_PWD;
		}
		account.setPassword(pwdDto.getNewPassword());// 更新密码
		account.setUpdateDate(new Date());
		mapper.updateByPrimaryKeySelective(account);
	}

	public ResponseBeanResult userDetail() {
		LocalUser localUser = userLocal.getLocalUser();
		UserAccountDto dto = new UserAccountDto();
		BeanUtils.copyProperties(localUser, dto);
		return new ResponseBeanResult(dto);
	}

	public void changeProfile(UserAccountDto accountDto) {
		checkLogin();
		if (!userLocal.getLocalUser().getId().equals(accountDto.getId())) {
			throw AssoException.NOTALLOWED_CHANGE;
		}
		UserAccount account = new UserAccount();
		accountDto.setMobilePhone(null);
		accountDto.setPassword(null);
		accountDto.setQq(null);
		BeanUtils.copyProperties(accountDto, account);
		account.setUpdateDate(new Date());
		mapper.updateByPrimaryKeySelective(account);
	}

	/**
	 * 校验用户是否登陆
	 * 
	 * @author liusonglin
	 * @date 2018年11月21日
	 */

	private void checkLogin() {
		String userId = userLocal.getLocalUser().getUserId();
		if (null == userId) {
			throw AssoException.PLE_LOGIN;
		}
	}

	/**
	 * 用户退出
	 */
	public void logout() {
		// 清除redis上用户信息
		String token = request.getHeader("Authorization");
		if (!StringUtils.isEmpty(token)) {
			redisUtil.hdel(REDIS_USER_SESSIONID, token);
		}
	}

	/**
	 * 刷新用户登录信息
	 */
	public void timerClearInvalidUser() {
		logger.info("定时任务，清除过期用户开始");
		Map<Object, Object> userInfos = redisUtil.hmget(REDIS_USER_SESSIONID);
		Set<Object> keySet = userInfos.keySet();
		for (Object key : keySet) {
			LocalUser user = (LocalUser) userInfos.get(key);
			// 判断过期时间
			if (user.getSystemTime() + REDIS_USER_TOKEN_EXPIRE_TIME <= System.currentTimeMillis()) {
				logger.info(user.getNickName() + "的登录账号已过期");
				redisUtil.hdel(REDIS_USER_SESSIONID, user.getToken());
			}
		}
		logger.info("定时任务，清除过期用户完毕");
	}

}
