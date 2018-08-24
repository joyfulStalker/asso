package cn.songlin.conf;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.songlin.comm.ExceptionResult;
import cn.songlin.exception.BizException;

@ControllerAdvice
public class GlobalDefultExceptionHandler {

	@ExceptionHandler(BizException.class)
	public ResponseEntity<ExceptionResult> baseExceptionHandler(HttpServletResponse response, BizException ex) {
		ExceptionResult exceptionResult = new ExceptionResult();
		exceptionResult.setMsg(ex.getMessage());
		exceptionResult.setStatus(ex.getCode());
		ex.printStackTrace();
		return new ResponseEntity<ExceptionResult>(exceptionResult, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResult> otherExceptionHandler(HttpServletResponse response, Exception ex) {
		ExceptionResult exceptionResult = new ExceptionResult();
		exceptionResult.setMsg(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<ExceptionResult>(exceptionResult, HttpStatus.OK);
	}
}
