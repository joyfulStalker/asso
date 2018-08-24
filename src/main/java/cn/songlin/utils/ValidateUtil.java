package cn.songlin.utils;

import java.util.regex.Pattern;

/**
 * 校验工具
 * 
 * @author liusonglin
 * @date 2018年7月31日
 */

public class ValidateUtil {
	/**
	 * 验证手机号码
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145 电信号码段:133、153、180、189
	 * 
	 * @param cellphone
	 * @return
	 */
	public static boolean checkMobilephone(String mobilePhone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
		return Pattern.matches(regex, mobilePhone);
	}

	/**
	 * 验证固话号码
	 * 
	 * @param telephone
	 * @return
	 */
	public static boolean checkFixedPhone(String fixedPhone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return Pattern.matches(regex, fixedPhone);
	}

	/**
	 * 校验邮箱
	 * 
	 * 1.@之前必须有内容且只能是字母（大小写）、数字、下划线(_)、减号（-）、点（.）
	 * 
	 * 2.@和最后一个点（.）之间必须有内容且只能是字母（大小写）、数字、点（.）、减号（-），且两个点不能挨着
	 * 
	 * 3.最后一个点（.）之后必须有内容且内容只能是字母（大小写）、数字且长度为大于等于2个字节，小于等于6个字节
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean checkEmail(String email) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	}

}
