package cn.songlin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_menu")
public class TtMenu {
    /**
     * 节点id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父节点id
     */
    private Long pid;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件路径(例: ./book/list.vue)
     */
    private String component;

    /**
     * 菜单显示名称
     */
    private String name;

    /**
     * 是否显示
     */
    @Column(name = "menu_show")
    private Byte menuShow;

    /**
     * 图标
     */
    @Column(name = "icon_cls")
    private String iconCls;

    /**
     * 删除标识 1 :可用 2:不可用
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 只有一个节点  (true)
     */
    private Byte leaf;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取节点id
     *
     * @return id - 节点id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置节点id
     *
     * @param id 节点id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父节点id
     *
     * @return pid - 父节点id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父节点id
     *
     * @param pid 父节点id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取组件路径(例: ./book/list.vue)
     *
     * @return component - 组件路径(例: ./book/list.vue)
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置组件路径(例: ./book/list.vue)
     *
     * @param component 组件路径(例: ./book/list.vue)
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 获取菜单显示名称
     *
     * @return name - 菜单显示名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单显示名称
     *
     * @param name 菜单显示名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否显示
     *
     * @return menu_show - 是否显示
     */
    public Byte getMenuShow() {
        return menuShow;
    }

    /**
     * 设置是否显示
     *
     * @param menuShow 是否显示
     */
    public void setMenuShow(Byte menuShow) {
        this.menuShow = menuShow;
    }

    /**
     * 获取图标
     *
     * @return icon_cls - 图标
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * 设置图标
     *
     * @param iconCls 图标
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * 获取删除标识 1 :可用 2:不可用
     *
     * @return delete_flag - 删除标识 1 :可用 2:不可用
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识 1 :可用 2:不可用
     *
     * @param deleteFlag 删除标识 1 :可用 2:不可用
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取只有一个节点  (true)
     *
     * @return leaf - 只有一个节点  (true)
     */
    public Byte getLeaf() {
        return leaf;
    }

    /**
     * 设置只有一个节点  (true)
     *
     * @param leaf 只有一个节点  (true)
     */
    public void setLeaf(Byte leaf) {
        this.leaf = leaf;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建日期
     *
     * @return create_date - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新日期
     *
     * @return update_date - 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新日期
     *
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}