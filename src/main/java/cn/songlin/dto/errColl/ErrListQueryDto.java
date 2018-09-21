package cn.songlin.dto.errColl;

import cn.songlin.dto.BaseQuery;

/**
 * 错误列表查询参数
 * 
 * @author liusonglin
 * @date 2018年9月21日
 */

public class ErrListQueryDto extends BaseQuery {
	/**
	 * 错误描述
	 */
	private String errDescription;

	/**
	 * 产生原因
	 */
	private String errCauseBy;

	/**
	 * 解决方法
	 */
	private String errSolution;

	public String getErrDescription() {
		return errDescription;
	}

	public void setErrDescription(String errDescription) {
		this.errDescription = errDescription;
	}

	public String getErrCauseBy() {
		return errCauseBy;
	}

	public void setErrCauseBy(String errCauseBy) {
		this.errCauseBy = errCauseBy;
	}

	public String getErrSolution() {
		return errSolution;
	}

	public void setErrSolution(String errSolution) {
		this.errSolution = errSolution;
	}

	@Override
	public String toString() {
		return "ErrListQueryDto [errDescription=" + errDescription + ", errCauseBy=" + errCauseBy + ", errSolution="
				+ errSolution + "]";
	}

}
