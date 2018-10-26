package cn.songlin.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ConvertObjToMapUtil {

	
	/**
	* 普通对象转Map
	* @author liusonglin
	* @date 2018年10月26日
	* @param obj
	* @return
	* @throws Exception
	*/
		
	public static Map<String, Object> getMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// 得到类对象
		Class<? extends Object> clazz = (Class<? extends Object>) obj.getClass();
		/* 得到类中的所有属性集合 */
		Field[] fs = clazz.getDeclaredFields();

		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			// 参数值为null的不参与加密，也不需要传
			if (!StringUtils.isEmpty(f.get(obj))) {
				// 得到此属性的值
				map.put(f.getName(), f.get(obj));// 设置键值
			}
		}
		return map;
	}

}
