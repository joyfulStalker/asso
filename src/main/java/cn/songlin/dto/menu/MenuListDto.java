package cn.songlin.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuListDto {

	/**
	 * 节点id
	 */
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
	private boolean menuShow;

	/**
	 * 图标
	 */
	private String iconCls;

	private boolean leaf;

	private List<MenuListDto> children = new ArrayList<>();// 子节点

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
	 * @param id
	 *            节点id
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
	 * @param pid
	 *            父节点id
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
	 * @param path
	 *            路径
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
	 * @param component
	 *            组件路径(例: ./book/list.vue)
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
	 * @param name
	 *            菜单显示名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取是否显示
	 *
	 * @return menu_show - 是否显示
	 */
	public boolean getMenuShow() {
		return menuShow;
	}

	/**
	 * 设置是否显示
	 *
	 * @param menuShow
	 *            是否显示
	 */
	public void setMenuShow(boolean menuShow) {
		this.menuShow = menuShow;
	}

	public List<MenuListDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuListDto> children) {
		this.children = children;
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
	 * @param iconCls
	 *            图标
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public String toString() {
		return "MenuListDto [id=" + id + ", pid=" + pid + ", path=" + path + ", component=" + component + ", name="
				+ name + ", menuShow=" + menuShow + ", iconCls=" + iconCls + ", leaf=" + leaf + ", children=" + children
				+ "]";
	}

}
