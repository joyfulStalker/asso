package cn.songlin.exception;

public class AssoException extends BizException {

	private static final long serialVersionUID = 3252856823627098049L;

	public static final BizException SYSTEM_ERR = new BizException(10010000, "系统错误");
	public static final BizException NICK_EXIST = new BizException(10010001, "该昵称已存在");
	public static final BizException NOT_MOBLIEPHONE = new BizException(10010002, "手机号输入不正确");
	public static final BizException NOT_EMAIL = new BizException(10010003, "邮箱输入不正确");
	public static final BizException HIT_SENSITIVEWORD = new BizException(10010004, "请遵守社群规范");
	
	
	//10021开头的为错误收集功能相关的
	public static final BizException NO_ERR_DESCRIPTION = new BizException(10021001, "请填写错误描述");
	public static final BizException NO_ERR_CATEGORY = new BizException(10021002, "请选择类别");
	public static final BizException NO_REFER_SOURCEDESC = new BizException(10021003, "请填写参考来源");
}
