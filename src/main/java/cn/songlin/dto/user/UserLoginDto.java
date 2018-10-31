package cn.songlin.dto.user;

import cn.songlin.common.utils.MyStringUtils;

public class UserLoginDto {

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		// MD5加密
		return MyStringUtils.getMd5(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLoginDto [userName=" + userName + ", password=" + password + "]";
	}

}
