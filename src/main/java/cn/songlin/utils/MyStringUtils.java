package cn.songlin.utils;

import java.util.UUID;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 一些常用的string工具
 * 
 * @author liusonglin
 * @date 2018年8月20日
 */

public class MyStringUtils {

	/**
	 * 获取UUID
	 * 
	 * @author liusonglin
	 * @date 2018年5月31日
	 * @return
	 */

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 对目标进行MD5加密
	 * 
	 * @author liusonglin
	 * @date 2018年5月31日
	 * @param target
	 * @return
	 */

	public static String getMd5(String target) {
		return DigestUtils.md5DigestAsHex(target.getBytes()).toString();
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	private static final Integer SHORT_UUID_LEN = 8;

	/**
	 * 生成UUID
	 * 
	 * @author liusonglin
	 * @date 2018年6月4日
	 * @return
	 */

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < SHORT_UUID_LEN; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	/**
	 * 手机号脱敏处理
	 * 
	 * @author liusonglin
	 * @date 2018年8月20日
	 * @param phoneNum
	 * @return
	 */

	public static String dealSensitivePhone(String name, String phoneNum) {
		return StringUtils.isEmpty(name) == false ? name : new StringBuilder(phoneNum).replace(3, 7, "****").toString();
	}
}
