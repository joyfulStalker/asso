package cn.songlin.emun;

/**
 * 任务调度相关的枚举
 * 
 * @author liusonglin
 * @date 2018年11月22日
 */

public enum JobStatusEnum {

	/**
	 * 0=停止
	 */
	STOP("0", "停止"),
	/**
	 * 1=运行
	 */
	RUNNING("1", "运行"),

	;

	// 成员变量
	private String val;// 值

	private String desc;// 描述

	// 构造方法
	private JobStatusEnum(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
