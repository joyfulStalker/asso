package cn.songlin.dto.user;

import cn.songlin.common.utils.MyStringUtils;

public class UserChangePwdDto {

	private String oldPassword;

	private String newPassword;

	public String getOldPassword() {
		return MyStringUtils.getMd5(oldPassword);
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return MyStringUtils.getMd5(newPassword);
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UserChangePwdDto [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}

}
