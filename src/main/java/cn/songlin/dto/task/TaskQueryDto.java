package cn.songlin.dto.task;

import java.util.Date;

import cn.songlin.common.dto.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TaskQueryDto extends BaseQuery {

	/**
	 * 任务名
	 */
	@ApiModelProperty(example = "test")
	private String jobName;

	/**
	 * 任务描述
	 */
	@ApiModelProperty(example = "test")
	private String description;

	/**
	 * 任务状态
	 */
	@ApiModelProperty(example = "1")
	private String jobStatus;

	/**
	 * 任务分组
	 */
	@ApiModelProperty(example = "test")
	private String jobGroup;

	/**
	 * 被调用的url
	 */

	@ApiModelProperty(example = "http://localhost:8090/task/test")
	private String invokedUrl;

	/**
	 * 创建者
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取任务名
	 *
	 * @return job_name - 任务名
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 设置任务名
	 *
	 * @param jobName
	 *            任务名
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取任务描述
	 *
	 * @return description - 任务描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置任务描述
	 *
	 * @param description
	 *            任务描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取任务状态
	 *
	 * @return job_status - 任务状态
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * 设置任务状态
	 *
	 * @param jobStatus
	 *            任务状态
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * 获取任务分组
	 *
	 * @return job_group - 任务分组
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 设置任务分组
	 *
	 * @param jobGroup
	 *            任务分组
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 获取被调用的url
	 *
	 * @return invoked_url - 被调用的url
	 */
	public String getInvokedUrl() {
		return invokedUrl;
	}

	/**
	 * 设置被调用的url
	 *
	 * @param invokedUrl
	 *            被调用的url
	 */
	public void setInvokedUrl(String invokedUrl) {
		this.invokedUrl = invokedUrl;
	}

	/**
	 * 获取创建者
	 *
	 * @return create_by - 创建者
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * 设置创建者
	 *
	 * @param createBy
	 *            创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	@Override
	public String toString() {
		return "TaskQueryDto [jobName=" + jobName + ", description=" + description + ", jobStatus=" + jobStatus
				+ ", jobGroup=" + jobGroup + ", invokedUrl=" + invokedUrl + ", createBy=" + createBy + ", createTime="
				+ createTime + "]";
	}

}
