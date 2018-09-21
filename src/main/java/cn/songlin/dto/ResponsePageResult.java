package cn.songlin.dto;

public class ResponsePageResult<T> {

	public ResponsePageResult(T data, Long total) {
		this.data = data;
		this.total = total;
	}

	private T data;

	private Long total;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
