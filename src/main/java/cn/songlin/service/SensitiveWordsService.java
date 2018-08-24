package cn.songlin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.songlin.comm.ConstantUtil;
import cn.songlin.entity.SensitiveWords;
import cn.songlin.mapper.SensitiveWordsMapper;

@Service
@SuppressWarnings(value = { "all" })
public class SensitiveWordsService {
	private String subFlag = "\\|"; // 敏感词分隔符
	// private String ConstantUtil.DEALER_CODE = "00000"; // 厂商code
	@SuppressWarnings({ "rawtypes" })
	private Map sensitiveWordMap = null; // 敏感词
	private int minMatchTYpe = 1; // 最小匹配规则

	@Autowired
	private SensitiveWordsMapper sensitiveWordsMapper;

	public String useWords(String str, String dealerCode) {
		if (null == str || "".equals(str)) {
			return null;
		}
		try {
			// 查询敏感词过滤计时开始
			long beginTime = System.currentTimeMillis();

			SensitiveWords word = new SensitiveWords();
			word.setDealerCode(dealerCode);
			List<SensitiveWords> wordsList = sensitiveWordsMapper.select(word);
			// 获取敏感词set集合
			if (null == wordsList || wordsList.size() < 1) {
				wordsList = new ArrayList<SensitiveWords>();
			}
			addSensitiveWordToHashMap(initSensitiveWords(wordsList));
			/*
			 * if(null!=wordsList&&wordsList.size()>0){//绑定的专属店没有设置敏感词就不再过滤
			 */
			Set<String> words = getSensitiveWord(str, 1);
			if (null != words && words.size() > 0) {
				/*
				 * logger.info("含有敏感词: " + words); long endTime = System.currentTimeMillis();
				 * logger.info("总共消耗时间为：" + (endTime - beginTime));
				 */
				return words.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Set<String> initSensitiveWords(List<SensitiveWords> wordsList) {
		Set<String> set = null;
		try {
			set = new HashSet<String>();

			for (SensitiveWords tt : wordsList) {
				String aa = tt.getSensitiveWords();
				if (null != aa && !"".equals(aa)) {
					String[] bb = aa.split(subFlag);
					for (int i = 0; i < bb.length; i++) {
						set.add(bb[i]);
					}
				}
			}
			// 这里添加公共敏感词模板内容
			set.addAll(readSensitiveWordFile());
			// logger.info("敏感词设定的数量：" + set.size()+"\t设定的敏感词： "+set);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	private Set<String> readSensitiveWordFile() throws Exception {
		Set<String> set = null;
		try {
			set = new HashSet<String>();
			SensitiveWords tt = new SensitiveWords();
			tt.setDealerCode(ConstantUtil.SENSITIVEWORD_DEALER_CODE);
			List<SensitiveWords> wordsList = sensitiveWordsMapper.select(tt);

			if (null != wordsList && wordsList.size() > 0) {
				for (SensitiveWords tt1 : wordsList) {
					String aa = tt1.getSensitiveWords();
					if (null != aa && !"".equals(aa)) {
						String[] bb = aa.split(subFlag);
						for (int i = 0; i < bb.length; i++) {
							set.add(bb[i]);
						}
					}
				}
				// logger.info("厂商敏感词设定的数量：" + set.size() + "\t厂商设定的敏感词： " + set);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 文件读取模式（读取公用模板备用）
		/*
		 * File file = new File("D:\\SensitiveWord.txt"); //读取文件 InputStreamReader read
		 * = new InputStreamReader(new FileInputStream(file),ENCODING); try {
		 * if(file.isFile() && file.exists()){ //文件流是否存在 set = new HashSet<String>();
		 * BufferedReader bufferedReader = new BufferedReader(read); String txt = null;
		 * while((txt = bufferedReader.readLine()) != null){ //读取文件，将文件内容放入到set中
		 * set.add(txt); } } else{ //不存在抛出异常信息 throw new Exception("敏感词库文件不存在"); } }
		 * catch (Exception e) { throw e; }finally{ read.close(); //关闭文件流 }
		 */
		return set;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		// 迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next(); // 关键字
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取

				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
	}

	public Set<String> getSensitiveWord(String txt, int matchType) {
		Set<String> sensitiveWordList = new HashSet<String>();
		for (int i = 0; i < txt.length(); i++) {
			int length = CheckSensitiveWord(txt, i, matchType); // 判断是否包含敏感字符
			if (length > 0) {// 存在,加入list中
				sensitiveWordList.add(txt.substring(i, i + length));
				i = i + length - 1; // 减1的原因，是因为for会自增
			}
		}

		return sensitiveWordList;
	}

	@SuppressWarnings({ "rawtypes" })
	public int CheckSensitiveWord(String txt, int beginIndex, int matchType) {
		boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0; // 匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word); // 获取指定key
			if (nowMap != null) { // 存在，则判断是否为最后一个
				matchFlag++; // 找到相应key，匹配标识+1
				if ("1".equals(nowMap.get("isEnd"))) { // 如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true; // 结束标志位为true
					if (minMatchTYpe == matchType) { // 最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			} else { // 不存在，直接返回
				break;
			}
		}
		if (matchFlag < 2 || !flag) { // 长度必须大于等于1，为词
			matchFlag = 0;
		}
		return matchFlag;
	}

}
