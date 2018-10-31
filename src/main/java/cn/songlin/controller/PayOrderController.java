package cn.songlin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.common.anno.Monitor;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.service.PayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("pay")
@Api("订单")
@ResponseBody
@SuppressWarnings(value = { "all" })
public class PayOrderController {

	private static final Logger logger = LoggerFactory.getLogger(PayOrderController.class);

	@Autowired
	private PayOrderService orderService;
	
	@PostMapping("/createOrder")
	@Monitor
	@ApiOperation(value = "测试订单号")
	public ResponseBeanResult<Void> createOrder() {
		orderService.createOrder();
		return new ResponseBeanResult();
	}
}
