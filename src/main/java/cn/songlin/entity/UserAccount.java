package cn.songlin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_account")
public class UserAccount implements Serializable{
    
	private static final long serialVersionUID = -8868821553907742920L;

	/**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * SSO的USER表id
     */
    @Column(name = "user_id")
    private String userId;

    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "nick_name")
    private String nickName;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 用户类型：1：普通用户 2：媒体用户 3：官方用户
     */
    @Column(name = "user_type")
    private Byte userType;

    private String email;

    private String qq;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    private Date registerDate;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 用户粉丝数
     */
    private Long fans;

    /**
     * 勋章数
     */
    @Column(name = "medal_count")
    private Integer medalCount;

    /**
     * 昵称修改次数
     */
    @Column(name = "nick_change_times")
    private Integer nickChangeTimes;

    /**
     * 上次昵称修改时间
     */
    @Column(name = "last_nickchange_time")
    private Date lastNickchangeTime;

    /**
     * 禁言时长（单位：小时，-1：永久禁言）
     */
    @Column(name = "bansend_timelength")
    private Long bansendTimelength;

    /**
     * 禁言开始时间
     */
    @Column(name = "bansend_starttime")
    private Date bansendStarttime;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取SSO的USER表id
     *
     * @return user_id - SSO的USER表id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置SSO的USER表id
     *
     * @param userId SSO的USER表id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return photo_url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取用户类型：1：普通用户 2：媒体用户 3：官方用户
     *
     * @return user_type - 用户类型：1：普通用户 2：媒体用户 3：官方用户
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * 设置用户类型：1：普通用户 2：媒体用户 3：官方用户
     *
     * @param userType 用户类型：1：普通用户 2：媒体用户 3：官方用户
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取注册时间
     *
     * @return register_date - 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_date - 最后登录时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginDate 最后登录时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取用户粉丝数
     *
     * @return fans - 用户粉丝数
     */
    public Long getFans() {
        return fans;
    }

    /**
     * 设置用户粉丝数
     *
     * @param fans 用户粉丝数
     */
    public void setFans(Long fans) {
        this.fans = fans;
    }

    /**
     * 获取勋章数
     *
     * @return medal_count - 勋章数
     */
    public Integer getMedalCount() {
        return medalCount;
    }

    /**
     * 设置勋章数
     *
     * @param medalCount 勋章数
     */
    public void setMedalCount(Integer medalCount) {
        this.medalCount = medalCount;
    }

    /**
     * 获取昵称修改次数
     *
     * @return nick_change_times - 昵称修改次数
     */
    public Integer getNickChangeTimes() {
        return nickChangeTimes;
    }

    /**
     * 设置昵称修改次数
     *
     * @param nickChangeTimes 昵称修改次数
     */
    public void setNickChangeTimes(Integer nickChangeTimes) {
        this.nickChangeTimes = nickChangeTimes;
    }

    /**
     * 获取上次昵称修改时间
     *
     * @return last_nickchange_time - 上次昵称修改时间
     */
    public Date getLastNickchangeTime() {
        return lastNickchangeTime;
    }

    /**
     * 设置上次昵称修改时间
     *
     * @param lastNickchangeTime 上次昵称修改时间
     */
    public void setLastNickchangeTime(Date lastNickchangeTime) {
        this.lastNickchangeTime = lastNickchangeTime;
    }

    /**
     * 获取禁言时长（单位：小时，-1：永久禁言）
     *
     * @return bansend_timelength - 禁言时长（单位：小时，-1：永久禁言）
     */
    public Long getBansendTimelength() {
        return bansendTimelength;
    }

    /**
     * 设置禁言时长（单位：小时，-1：永久禁言）
     *
     * @param bansendTimelength 禁言时长（单位：小时，-1：永久禁言）
     */
    public void setBansendTimelength(Long bansendTimelength) {
        this.bansendTimelength = bansendTimelength;
    }

    /**
     * 获取禁言开始时间
     *
     * @return bansend_starttime - 禁言开始时间
     */
    public Date getBansendStarttime() {
        return bansendStarttime;
    }

    /**
     * 设置禁言开始时间
     *
     * @param bansendStarttime 禁言开始时间
     */
    public void setBansendStarttime(Date bansendStarttime) {
        this.bansendStarttime = bansendStarttime;
    }
}