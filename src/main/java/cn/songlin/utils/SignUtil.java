package cn.songlin.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 签名生成工具类
 * 
 * @author liusonglin
 * @date 2018年10月10日
 */

public class SignUtil {

	/**
	 * 生成签名
	 */
	public static String generateSign(Map<String, String> parm) {
		String json = "";
		if (null != parm) {
			Collection<String> keyset = parm.keySet();
			List<String> list = new ArrayList<String>(keyset);
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				json += list.get(i) + "=" + parm.get(list.get(i));
			}
			json = bytesToMD5((bytesToMD5(json.getBytes()) + "ffffffffffff20181010").getBytes());
		}

		return json;
	}

	/**
	* 把字节数组转换成md5
	* @author liusonglin
	* @date 2018年10月31日
	* @param input
	* @return
	*/
		
	public static String bytesToMD5(byte[] input) {
		String md5str = null;
		try {
			// 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算后获得字节数组
			byte[] buff = md.digest(input);
			// 把数组每一字节换成16进制连成md5字符串
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString();
	}
}
