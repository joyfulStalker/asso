package cn.songlin.dto.errColl;

public class ErrReferDto {

	private Long id;

	/**
	 * 参考源的描述
	 */
	private String sourceDesc;

	/**
	 * 参考源的url
	 */
	private String sourceUrl;

	/**
	 * 1：有用 2：无用
	 */
	private Boolean isUseful;

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
	 * 获取参考源的描述
	 *
	 * @return source_desc - 参考源的描述
	 */
	public String getSourceDesc() {
		return sourceDesc;
	}

	/**
	 * 设置参考源的描述
	 *
	 * @param sourceDesc
	 *            参考源的描述
	 */
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	/**
	 * 获取参考源的url
	 *
	 * @return source_url - 参考源的url
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}

	/**
	 * 设置参考源的url
	 *
	 * @param sourceUrl
	 *            参考源的url
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/**
	 * 获取1：有用 2：无用
	 *
	 * @return is_useful - 1：有用 2：无用
	 */
	public Boolean getIsUseful() {
		return isUseful;
	}

	/**
	 * 设置1：有用 2：无用
	 *
	 * @param isUseful
	 *            1：有用 2：无用
	 */
	public void setIsUseful(Boolean isUseful) {
		this.isUseful = isUseful;
	}

	@Override
	public String toString() {
		return "ErrReferDto [id=" + id + ", sourceDesc=" + sourceDesc + ", sourceUrl=" + sourceUrl + ", isUseful="
				+ isUseful + "]";
	}

}
