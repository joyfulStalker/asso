package cn.songlin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.songlin.common.dto.LocalUser;
import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.MyStringUtils;
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
		if (userAccount != null) {// 登陆成功
			UserAccount account = new UserAccount();
			BeanUtils.copyProperties(userAccount, account);
			account.setPassword(null);
			account.setLastLoginDate(new Date());// 刷新最后登录时间
			mapper.updateByPrimaryKeySelective(account);
		}
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
		String userId = UserLocal.getLocalUser().getUserId();
		if (null == userId) {
			throw AssoException.PLE_LOGIN;
		}
		UserAccount account = mapper.selectByPrimaryKey(UserLocal.getLocalUser().getId());
		if (!account.getPassword().equals(pwdDto.getOldPassword())) {
			throw AssoException.ERR_OLD_PWD;
		}
		account.setPassword(pwdDto.getNewPassword());// 更新密码
		account.setUpdateDate(new Date());
		mapper.updateByPrimaryKeySelective(account);
	}

}
