package cn.songlin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_log")
public class UserLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 访问该地址的用户id
     */
    @Column(name = "log_userId")
    private String logUserid;

    /**
     * 访问该地址的用户名
     */
    @Column(name = "log_userName")
    private String logUsername;

    @Column(name = "log_url")
    private String logUrl;

    /**
     * 访问改地址的用户id
     */
    @Column(name = "log_ip")
    private String logIp;

    /**
     * 访问时间
     */
    @Column(name = "log_data_time")
    private Date logDataTime;

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
     * 获取访问该地址的用户id
     *
     * @return log_userId - 访问该地址的用户id
     */
    public String getLogUserid() {
        return logUserid;
    }

    /**
     * 设置访问该地址的用户id
     *
     * @param logUserid 访问该地址的用户id
     */
    public void setLogUserid(String logUserid) {
        this.logUserid = logUserid;
    }

    /**
     * 获取访问该地址的用户名
     *
     * @return log_userName - 访问该地址的用户名
     */
    public String getLogUsername() {
        return logUsername;
    }

    /**
     * 设置访问该地址的用户名
     *
     * @param logUsername 访问该地址的用户名
     */
    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    /**
     * @return log_url
     */
    public String getLogUrl() {
        return logUrl;
    }

    /**
     * @param logUrl
     */
    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    /**
     * 获取访问改地址的用户id
     *
     * @return log_ip - 访问改地址的用户id
     */
    public String getLogIp() {
        return logIp;
    }

    /**
     * 设置访问改地址的用户id
     *
     * @param logIp 访问改地址的用户id
     */
    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    /**
     * 获取访问时间
     *
     * @return log_data_time - 访问时间
     */
    public Date getLogDataTime() {
        return logDataTime;
    }

    /**
     * 设置访问时间
     *
     * @param logDataTime 访问时间
     */
    public void setLogDataTime(Date logDataTime) {
        this.logDataTime = logDataTime;
    }
}