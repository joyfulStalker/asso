package cn.songlin.dto.task;

import java.util.Date;

public class TtTaskDto {

	private Long id;

	/**
	 * 任务名
	 */
	private String jobName;

	/**
	 * 任务描述
	 */
	private String description;

	/**
	 * cron表达式
	 */
	private String cronExpression;

	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;

	/**
	 * 任务状态
	 */
	private String jobStatus;

	/**
	 * 任务分组
	 */
	private String jobGroup;

	/**
	 * 被调用的url
	 */
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
	 * 更新者
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * 获取cron表达式
	 *
	 * @return cron_expression - cron表达式
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 设置cron表达式
	 *
	 * @param cronExpression
	 *            cron表达式
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 获取任务执行时调用哪个类的方法 包名+类名
	 *
	 * @return bean_class - 任务执行时调用哪个类的方法 包名+类名
	 */
	public String getBeanClass() {
		return beanClass;
	}

	/**
	 * 设置任务执行时调用哪个类的方法 包名+类名
	 *
	 * @param beanClass
	 *            任务执行时调用哪个类的方法 包名+类名
	 */
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
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

	/**
	 * 获取更新者
	 *
	 * @return update_by - 更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * 设置更新者
	 *
	 * @param updateBy
	 *            更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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

	@Override
	public String toString() {
		return "TtTaskDto [id=" + id + ", jobName=" + jobName + ", description=" + description + ", cronExpression="
				+ cronExpression + ", beanClass=" + beanClass + ", jobStatus=" + jobStatus + ", jobGroup=" + jobGroup
				+ ", invokedUrl=" + invokedUrl + ", createBy=" + createBy + ", createTime=" + createTime + ", updateBy="
				+ updateBy + ", updateTime=" + updateTime + "]";
	}

}