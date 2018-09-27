package cn.songlin.conf;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.songlin.dto.base.BaseCode;
import cn.songlin.dto.base.BaseResponseResult;
import cn.songlin.exception.BizException;

@ControllerAdvice
public class GlobalDefultExceptionHandler {

	@ExceptionHandler(BizException.class)
	@ResponseBody // 不加该注解，返回给浏览器的值是默认的
	public BaseResponseResult baseExceptionHandler(HttpServletResponse response, BizException ex) {
		ex.printStackTrace();
		return new BaseResponseResult(ex.getMessage(), ex.getCode());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResponseResult otherExceptionHandler(HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		return new BaseResponseResult(ex.getMessage(), BaseCode.ERROR_UNKNOWN);
	}
}
