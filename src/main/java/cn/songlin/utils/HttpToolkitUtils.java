package cn.songlin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
* 远程调用（模拟浏览器）
* @author liusonglin
* @date 2018年8月15日
*/
	
public class HttpToolkitUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpToolkitUtils.class);

	public static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5000L, TimeUnit.MILLISECONDS)
			// 其他配置
			.readTimeout(5000L, TimeUnit.MILLISECONDS).writeTimeout(5000L, TimeUnit.MILLISECONDS).build();

	public static OkHttpClient getInstance() {
		return okHttpClient;
	}

	private HttpToolkitUtils() {

	}

	/**
	 * post请求 ，返回响应字符串 Content-Type:Application/json
	 * 
	 * @param url
	 *            链接
	 * @param json
	 *            json字符串 ，不能为null
	 * @return
	 */
	public static String sendJsonAndPost(String url, String json) {
		return sendJsonAndPost(url, json, null);
	}

	/**
	 * post请求，返回响应字符串 Content-Type:Application/json
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            json字符串 ，不能为null
	 * @param header
	 *            header信息
	 * @return
	 */
	public static String sendJsonAndPost(String url, String param, Map<String, String> header) {
		try {
			RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), param);
			Builder build = new Request.Builder();
			build.url(url);
			build.post(body);
			if (header != null) {
				Iterator<String> iter = header.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String val = header.get(key);
					build.addHeader(key, val);
				}
			}
			Request request = build.build();
			OkHttpClient client = getInstance();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			if (response.code() != 200) {
				return null;
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * post请求，返回序列化对象 Content-Type:Application/json
	 * 
	 * @param url
	 *            链接
	 * @param json
	 *            json字符串 ,不能为null
	 * @param header
	 *            header信息
	 * @param t
	 *            反序列化对象
	 * @return
	 */
	public static <T> T sendJsonAndPost(String url, String json, Map<String, String> header, Class<T> t) {
		try {
			String result = sendJsonAndPost(url, json, header);
			if (result == null) {
				return null;
			}
			T obj = JSON.parseObject(result, t);
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * get 请求，返回响应字符串
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            Map对象
	 * @return
	 */
	public static String sendDataAndGet(String url, Map<String, Object> param) {
		return sendDataAndGet(url, param, null);
	}

	/**
	 * get 请求，返回响应字符串
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            Map对象
	 * @param header
	 *            header信息
	 * @return
	 */
	public static String sendDataAndGet(String url, Map<String, Object> param, Map<String, String> header) {
		try {
			String parameter = urlEncodeMap(param, "utf-8");
			if (parameter != null) {
				url = url + "?" + parameter;
			}
			logger.info("uri:" + url);
			OkHttpClient client = getInstance();
			Builder build = new Request.Builder();
			build.url(url);
			build.get();
			if (header != null) {
				Iterator<String> iter = header.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String val = header.get(key);
					build.addHeader(key, val);
				}
			}
			Request request = build.build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			if (response.code() != 200) {
				return null;
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}

		return null;
	}

	public static String sendDataAndGet(String url) {
		try {
			Request request = new Request.Builder().url(url).get().header("x-client-id", "APP").build();
			OkHttpClient client = getInstance();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			logger.info("code:" + response.code());
			if (response.code() != 200) {
				return null;
			}
			return result;
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	public static Object sendDataAndPost(String url, String param) {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");// 数据类型为json格式，
			RequestBody body = RequestBody.create(JSON, param);

			Request request = new Request.Builder().url(url).post(body).header("x-client-id", "PORTAL").build();
			OkHttpClient client = getInstance();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			logger.info("code:" + response.code());
			if (response.code() != 200) {
				return null;
			}
			return response.code();
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	public static Object sendDataAndPut(String url, String param) {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");// 数据类型为json格式，
			RequestBody body = RequestBody.create(JSON, param);
			logger.info("param:=" + param);
			Request request = new Request.Builder().url(url).put(body).header("x-client-id", "APP")
					.header("Content-Type", "application/json").build();
			OkHttpClient client = getInstance();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			logger.info("code:" + response.code());
			if (response.code() != 200) {
				return null;
			}
			return response.code();
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * get 请求，返回响应字符串
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            字符串，格式 a=b&b=d
	 * @param header
	 *            header信息
	 * @return
	 */
	public static String sendDataAndGet(String url, String param, Map<String, String> header) {
		try {
			String parameter = urlEncodeStr(param, "utf-8");
			if (parameter != null) {
				url = url + "?" + parameter;
			}
			logger.info("uri:" + url);
			OkHttpClient client = getInstance();
			Builder build = new Request.Builder();
			build.url(url);
			build.get();
			if (header != null) {
				Iterator<String> iter = header.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String val = header.get(key);
					build.addHeader(key, val);
				}
			}
			Request request = build.build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info("result:" + result);
			if (response.code() != 200) {
				return null;
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}

		return null;
	}

	/**
	 * Get 请求，序列化响应值
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            Map对象
	 * @param header
	 *            header信息
	 * @param t
	 *            反序列化对象
	 * @return
	 */
	public static <T> T sendDataAndGet(String url, Map<String, Object> param, Map<String, String> header, Class<T> t) {
		try {
			String result = sendDataAndGet(url, param, header);
			if (result == null) {
				return null;
			}
			T obj = JSON.parseObject(result, t);
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * GET 请求 ，序列化响应值
	 * 
	 * @param url
	 *            链接
	 * @param param
	 *            字符串，格式 a=b&b=d
	 * @param header
	 *            header信息
	 * @param t
	 *            反序列化对象
	 * @return
	 */
	public static <T> T sendDataAndGet(String url, String param, Map<String, String> header, Class<T> t) {
		try {
			String result = sendDataAndGet(url, param, header);
			if (result == null) {
				return null;
			}
			T obj = JSON.parseObject(result, t);
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * get请求的参数转换
	 * 
	 * @param param
	 *            字符串，格式a=b&b=d
	 * @param code
	 *            编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncodeStr(String param, String code) throws UnsupportedEncodingException {
		if (param == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String[] arr = param.split("&");
		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split("=");
			if (arr2.length > 1) {
				sb.append(arr2[0]);
				String a = URLEncoder.encode(arr2[1], code);
				sb.append("=");
				sb.append(a);
				if (i < arr.length - 1) {
					sb.append("&");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * map转序列化格式数据
	 * 
	 * @param map
	 * @param code
	 *            编码格式
	 * @return
	 */
	public static String urlEncodeMap(Map<String, Object> map, String code) {
		if (map == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			Object val = map.get(key);
			String value = String.valueOf(val);
			if ("".equals(value) || "null".equals(value)) {
				continue;
			}
			sb.append(key + "=");
			sb.append(value + "&");
		}
		return sb.substring(0, sb.length() - 1);
	}

}
