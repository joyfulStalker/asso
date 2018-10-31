package cn.songlin.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.songlin.entity.TtPayOrder;
import cn.songlin.mapper.TtPayOrderMapper;
import cn.songlin.utils.GenerateOrderCodeUtils;

@Service
public class PayOrderService {
	private static final Logger logger = LoggerFactory.getLogger(PayOrderService.class);
	@Autowired
	private TtPayOrderMapper orderMapper;

	public void createOrder() {
		logger.info("创建订单");
		TtPayOrder record = new TtPayOrder();

		TtPayOrder order = new TtPayOrder();
		String code = checkCode(order, getCode());

		record.setOrderType("10011001");
		record.setOrderCode(code);
		orderMapper.insert(record);
	}

	private String checkCode(TtPayOrder order, String code) {
		order.setOrderCode(code);
		int i = orderMapper.selectCount(order);
		if (i > 0) {
			long[] usedCodes = orderMapper.getAllCode();
			return GenerateOrderCodeUtils.getUniqueCode(usedCodes, 100000, 999999) + "";
		}
		return code;
	}

	public String getCode() {
		StringBuilder sb = new StringBuilder(2);
		sb.append(new Random().nextInt(9) + 1);
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		return sb.toString();
	}
}
