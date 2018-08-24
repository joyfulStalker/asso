package cn.songlin.exception;

public class CommunityException extends BizException {

	private static final long serialVersionUID = 3252856823627098049L;

	public static final BizException NICK_EXIST = new BizException(10010001, "该昵称已存在");
	public static final BizException NOT_MOBLIEPHONE = new BizException(10010002, "手机号输入不正确");
	public static final BizException NOT_EMAIL = new BizException(10010003, "邮箱输入不正确");
	public static final BizException HIT_SENSITIVEWORD = new BizException(10010004, "请遵守社群规范");
}
