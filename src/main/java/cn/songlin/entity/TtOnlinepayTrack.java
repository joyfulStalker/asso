package cn.songlin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_onlinepay_track")
public class TtOnlinepayTrack {
    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private String userid;

    /**
     * 请求的uri
     */
    private String uri;

    /**
     * 请求的资源路径
     */
    @Column(name = "request_source")
    private String requestSource;

    /**
     * 请求者ip地址
     */
    @Column(name = "request_ip")
    private String requestIp;

    /**
     * 请求数据 json格式
     */
    @Column(name = "request_data")
    private String requestData;

    /**
     * 响应数据 json格式
     */
    @Column(name = "respone_data")
    private String responeData;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取请求的uri
     *
     * @return uri - 请求的uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置请求的uri
     *
     * @param uri 请求的uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 获取请求的资源路径
     *
     * @return request_source - 请求的资源路径
     */
    public String getRequestSource() {
        return requestSource;
    }

    /**
     * 设置请求的资源路径
     *
     * @param requestSource 请求的资源路径
     */
    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    /**
     * 获取请求者ip地址
     *
     * @return request_ip - 请求者ip地址
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * 设置请求者ip地址
     *
     * @param requestIp 请求者ip地址
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    /**
     * 获取请求数据 json格式
     *
     * @return request_data - 请求数据 json格式
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * 设置请求数据 json格式
     *
     * @param requestData 请求数据 json格式
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    /**
     * 获取响应数据 json格式
     *
     * @return respone_data - 响应数据 json格式
     */
    public String getResponeData() {
        return responeData;
    }

    /**
     * 设置响应数据 json格式
     *
     * @param responeData 响应数据 json格式
     */
    public void setResponeData(String responeData) {
        this.responeData = responeData;
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

	@Override
	public String toString() {
		return "TtOnlinepayTrack [id=" + id + ", userid=" + userid + ", uri=" + uri + ", requestSource=" + requestSource
				+ ", requestIp=" + requestIp + ", requestData=" + requestData + ", responeData=" + responeData
				+ ", createTime=" + createTime + "]";
	}
    
}