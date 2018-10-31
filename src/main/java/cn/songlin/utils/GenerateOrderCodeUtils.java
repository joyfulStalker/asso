package cn.songlin.utils;

import cn.songlin.exception.AssoException;

public class GenerateOrderCodeUtils {

	/**
	 * 获取唯一订单code（10位）
	 * 
	 * @author liusonglin
	 * @date 2018年10月30日
	 * @param usedCodes
	 *            被使用过的code数组（从小到大排序）
	 * @param beginNum
	 *            起始数据
	 * @param endNum
	 *            结束数据
	 * @return long 未使用过的code
	 */

	public static long getUniqueCode(long[] usedCodes, long beginNum, long endNum) {
		int len = usedCodes.length;// 数组长度
		// 首先判断长度是否超过90 = 99 + 1 -10
		if (len >= endNum + 1 - beginNum) {// 数字已经用完
			throw AssoException.ORDER_CREATE_FAILE;
		}
		// 判断数组最小值是否大于指定最小值
		if (usedCodes[0] > beginNum) {
			return usedCodes[0] - 1;
		}
		// 判断数组最大值是否小于指定最大值
		if (usedCodes[len - 1] < endNum) {
			return usedCodes[len - 1] + 1;
		}
		// 最大值最小值用完，查看4位连续是否存在空缺
		for (int i = 0; i < len; i = i + 4) {
			if (!(i + 1 < len)) {
				if (usedCodes[i] < endNum) {
					return usedCodes[i] + 1;
				}
			}
			if (!(i + 2 < len)) {
				if (usedCodes[i + 1] < endNum) {
					return usedCodes[i + 1] + 1;
				}
			}
			if (!(i + 3 < len)) {
				if (usedCodes[i + 2] < endNum) {
					return usedCodes[i + 2] + 1;
				}
			}
			if (i > 3) {// 判断上次和本次循环时中间是否有数据
				if (usedCodes[i] - usedCodes[i - 1] > 1) {
					return usedCodes[i - 1] + 1;
				}
			}
			//四位相邻的正整数异或后结果为零
			if ((usedCodes[i] ^ usedCodes[i + 1] ^ usedCodes[i + 2] ^ usedCodes[i + 3]) != 0) {
				if (usedCodes[i + 1] - usedCodes[i] > 1) {
					return usedCodes[i] + 1;
				}
				if (usedCodes[i + 2] - usedCodes[i + 1] > 1) {
					return usedCodes[i + 1] + 1;
				}
				if (usedCodes[i + 3] - usedCodes[i + 2] > 1) {
					return usedCodes[i + 2] + 1;
				}
			}

		}
		throw AssoException.ORDER_CREATE_FAILE;
	}
}
