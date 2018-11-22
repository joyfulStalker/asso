package cn.songlin.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class QuickTest {

	public static void main(String[] args) throws Exception {

		String encode = URLEncoder.encode(
				"https://lincoln-mp-dev.yonyouauto.com/modules/myorder.html#/detail?orderCode=36722334723752&from=1",
				"utf-8");
		System.out.println(encode);

		System.out.println(URLDecoder.decode(encode, "utf-8"));
	}
}
