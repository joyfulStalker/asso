package cn.songlin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_err_refer")
public class TtErrRefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 错误信息主键
     */
    @Column(name = "err_id")
    private Long errId;

    /**
     * 参考源的描述
     */
    @Column(name = "source_desc")
    private String sourceDesc;

    /**
     * 参考源的url
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 1：有用 2：无用
     */
    @Column(name = "is_useful")
    private Boolean isUseful;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
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
     * 获取错误信息主键
     *
     * @return err_id - 错误信息主键
     */
    public Long getErrId() {
        return errId;
    }

    /**
     * 设置错误信息主键
     *
     * @param errId 错误信息主键
     */
    public void setErrId(Long errId) {
        this.errId = errId;
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
     * @param sourceDesc 参考源的描述
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
     * @param sourceUrl 参考源的url
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
     * @param isUseful 1：有用 2：无用
     */
    public void setIsUseful(Boolean isUseful) {
        this.isUseful = isUseful;
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
     * @param createTime 创建时间
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
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "TtErrRefer [id=" + id + ", errId=" + errId + ", sourceDesc=" + sourceDesc + ", sourceUrl=" + sourceUrl
				+ ", isUseful=" + isUseful + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
    
}