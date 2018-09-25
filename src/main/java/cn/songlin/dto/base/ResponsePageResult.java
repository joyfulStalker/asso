package cn.songlin.dto.base;

/**
 * 分页
 * 
 * @author liusonglin
 * @date 2018年9月25日
 */
public class ResponsePageResult extends BaseResponseResult {

	private static final long serialVersionUID = -6086160088831903762L;

	public ResponsePageResult(Object data, Long total) {
		this.data = data;
		this.total = total;
	}
	public ResponsePageResult(String errMsg, Integer resultCode) {
		this.resultCode = resultCode;
		this.errMsg = errMsg;
	}
	private Object data;

	private Long total;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ResponsePageResult [data=" + data + ", total=" + total + "]";
	}

}
