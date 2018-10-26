package cn.songlin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.bean.BeanUtil;
import cn.songlin.dto.user.UserAccountDto;
import cn.songlin.dto.user.UserLoginDto;
import cn.songlin.entity.UserAccount;
import cn.songlin.exception.AssoException;
import cn.songlin.mapper.UserAccountMapper;
import cn.songlin.utils.MyStringUtils;
import cn.songlin.utils.ValidateUtils;

@Service
@SuppressWarnings(value = { "all" })
public class UserAccountService {

	@Autowired
	private UserAccountMapper mapper;

	/**
	 * 用户注册
	 * 
	 * @author liusonglin
	 * @date 2018年7月30日
	 * @param userAccountDto
	 */

	public void register(UserAccountDto userAccountDto) {
		if (!ValidateUtils.checkMobilephone(userAccountDto.getMobilePhone())) {// 不是手机号
			throw new AssoException().NOT_MOBLIEPHONE;// 不是手机号
		}
		if (userAccountDto.getEmail() != null && !ValidateUtils.checkEmail(userAccountDto.getEmail())) {
			throw new AssoException().NOT_EMAIL;// 不是邮箱号
		}
		if (mapper.checkUserName(userAccountDto.getName()) > 0
				|| mapper.checkUserName(userAccountDto.getNickName()) > 0) {
			throw new AssoException().NICK_EXIST;// 该昵称已存在
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

	public UserAccount login(UserLoginDto userLoginDto) {
		UserAccount userAccount = mapper.login(userLoginDto);// 支持昵称，用户名，电话登录
		if (userAccount != null) {// 登陆成功
			userAccount.setPassword(null);
			userAccount.setLastLoginDate(new Date());// 刷新最后登录时间
			mapper.updateByPrimaryKeySelective(userAccount);
		}
		return userAccount;
	}

}
