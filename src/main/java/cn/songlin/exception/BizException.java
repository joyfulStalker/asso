package cn.songlin.exception;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常
 * 
 * @author BENJAMIN
 * 
 *         定义异常时，需要先确定异常所属模块。 例如：添加优惠券模块报错 可以定义为 [10020001] 前四位数为系统模块编号，后4位为错误代码
 *         ,唯一 <br>
 *         优惠券模块异常 1002 <br>
 *         组织服务模块异常 1003 <br>
 * 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 3380746618050866299L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BizException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int d) {
		this.code = d;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BizException newInstance(String msgFormat, Object... args) {
		return new BizException(this.code, msgFormat, args);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}
}
