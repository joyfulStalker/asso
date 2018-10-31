package cn.songlin.dto.errColl;

import cn.songlin.common.dto.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 错误列表查询参数
 * 
 * @author liusonglin
 * @date 2018年9月21日
 */
@ApiModel
public class ErrListQueryDto extends BaseQuery {

	/**
	 * 错误描述
	 */
	@ApiModelProperty(value = "错误描述", example = "")
	private String errDescription;

	/**
	 * 产生原因
	 */
	@ApiModelProperty(value = "产生原因", example = "")
	private String errCauseBy;

	/**
	 * 解决方法
	 */
	@ApiModelProperty(value = "解决方法", example = "")
	private String errSolution;

	@ApiModelProperty(value = "分类", example = "java")
	private String category;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ErrListQueryDto [errDescription=" + errDescription + ", errCauseBy=" + errCauseBy + ", errSolution="
				+ errSolution + ", category=" + category + ", page=" + page + ", rows=" + rows + "]";
	}

}
