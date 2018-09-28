package cn.songlin.dto.errColl;

import java.util.Date;
import java.util.List;

import cn.songlin.entity.TtErrRefer;

public class ErrCollectionDetailDto {
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
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 解决时间
	 */
	private Date solveTime;

	/**
	 * 1:未删除 2:已删除
	 */
	private Byte status;

	private String category;

	private List<TtErrRefer> refers;

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
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取解决时间
	 *
	 * @return solve_time - 解决时间
	 */
	public Date getSolveTime() {
		return solveTime;
	}

	/**
	 * 设置解决时间
	 *
	 * @param solveTime
	 *            解决时间
	 */
	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
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

	public List<TtErrRefer> getRefers() {
		return refers;
	}

	public void setRefers(List<TtErrRefer> refers) {
		this.refers = refers;
	}

	@Override
	public String toString() {
		return "ErrCollectionDetailDto [id=" + id + ", errDescription=" + errDescription + ", errCauseBy=" + errCauseBy
				+ ", errSolution=" + errSolution + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", solveTime=" + solveTime + ", status=" + status + ", category=" + category + ", refers=" + refers
				+ "]";
	}

}
