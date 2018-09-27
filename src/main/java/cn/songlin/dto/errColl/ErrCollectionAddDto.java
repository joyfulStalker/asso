package cn.songlin.dto.errColl;

import java.util.List;

public class ErrCollectionAddDto {

	/**
	 * 主键
	 */
	private Long id;

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

	/**
	 * 1:未删除 2:已删除
	 */
	private Byte status;

	private String category;

	private List<ErrReferDto> refers;

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取错误描述
	 *
	 * @return err_description - 错误描述
	 */
	public String getErrDescription() {
		return errDescription;
	}

	/**
	 * 设置错误描述
	 *
	 * @param errDescription
	 *            错误描述
	 */
	public void setErrDescription(String errDescription) {
		this.errDescription = errDescription;
	}

	/**
	 * 获取产生原因
	 *
	 * @return err_cause_by - 产生原因
	 */
	public String getErrCauseBy() {
		return errCauseBy;
	}

	/**
	 * 设置产生原因
	 *
	 * @param errCauseBy
	 *            产生原因
	 */
	public void setErrCauseBy(String errCauseBy) {
		this.errCauseBy = errCauseBy;
	}

	/**
	 * 获取解决方法
	 *
	 * @return err_solution - 解决方法
	 */
	public String getErrSolution() {
		return errSolution;
	}

	/**
	 * 设置解决方法
	 *
	 * @param errSolution
	 *            解决方法
	 */
	public void setErrSolution(String errSolution) {
		this.errSolution = errSolution;
	}

	/**
	 * 获取1:未删除 2:已删除
	 *
	 * @return status - 1:未删除 2:已删除
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置1:未删除 2:已删除
	 *
	 * @param status
	 *            1:未删除 2:已删除
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public List<ErrReferDto> getRefers() {
		return refers;
	}

	public void setRefers(List<ErrReferDto> refers) {
		this.refers = refers;
	}

}
