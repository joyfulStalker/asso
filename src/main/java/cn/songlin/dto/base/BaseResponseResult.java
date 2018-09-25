package cn.songlin.dto.base;

import java.io.Serializable;

/**
 * 统一返回格式
 * 
 * @author liusonglin
 * @date 2018年9月25日
 */

public class BaseResponseResult implements Serializable {

	private static final long serialVersionUID = -2095286764582439860L;

	public BaseResponseResult() {

	}

	public BaseResponseResult(String errMsg, Integer resultCode) {
		this.resultCode = resultCode;
		this.errMsg = errMsg;
	}

	private Integer resultCode;

	private String errMsg;

	private long elapsedMilliseconds;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public long getElapsedMilliseconds() {
		return elapsedMilliseconds;
	}

	public void setElapsedMilliseconds(long elapsedMilliseconds) {
		this.elapsedMilliseconds = elapsedMilliseconds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BaseResponseResult [resultCode=" + resultCode + ", errMsg=" + errMsg + ", elapsedMilliseconds="
				+ elapsedMilliseconds + "]";
	}

}
