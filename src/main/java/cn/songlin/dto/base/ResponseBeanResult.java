package cn.songlin.dto.base;


/**
* 普通值响应
* @author liusonglin
* @date 2018年9月25日
* @param <T>
*/
public class ResponseBeanResult<T> extends BaseResponseResult {

	private static final long serialVersionUID = -4077937592583352387L;
	
	public ResponseBeanResult() {
	}
	
	public ResponseBeanResult(T data) {
		this.data = data;
	}
	
	public ResponseBeanResult(T data,Integer resultCode) {
		this.setResultCode(resultCode);
		this.data = data;
	}

	public ResponseBeanResult<T> FAILED() {
		return this;
	}
	
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseBeanResult [data=" + data + "]";
	}

}
