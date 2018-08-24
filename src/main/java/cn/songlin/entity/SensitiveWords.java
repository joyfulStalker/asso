package cn.songlin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sensitive_words")
public class SensitiveWords {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 经销商代码
     */
    @Column(name = "dealer_code")
    private String dealerCode;

    /**
     * 经销商名称
     */
    @Column(name = "dealer_name")
    private String dealerName;

    /**
     * 敏感词
     */
    @Column(name = "sensitive_words")
    private String sensitiveWords;

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
     * 创建人
     */
    @Column(name = "create_by")
    private Integer createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Integer updateBy;

    /**
     * 创建者
     */
    @Column(name = "create_name")
    private String createName;

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
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取经销商代码
     *
     * @return dealer_code - 经销商代码
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * 设置经销商代码
     *
     * @param dealerCode 经销商代码
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    /**
     * 获取经销商名称
     *
     * @return dealer_name - 经销商名称
     */
    public String getDealerName() {
        return dealerName;
    }

    /**
     * 设置经销商名称
     *
     * @param dealerName 经销商名称
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    /**
     * 获取敏感词
     *
     * @return sensitive_words - 敏感词
     */
    public String getSensitiveWords() {
        return sensitiveWords;
    }

    /**
     * 设置敏感词
     *
     * @param sensitiveWords 敏感词
     */
    public void setSensitiveWords(String sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
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

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取创建者
     *
     * @return create_name - 创建者
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建者
     *
     * @param createName 创建者
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }
}