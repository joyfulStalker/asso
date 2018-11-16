package cn.songlin.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuConfListDto {

	private Long id;

	private String label;

	private List<MenuConfListDto> children = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<MenuConfListDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuConfListDto> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "MenuConfListDto [id=" + id + ", label=" + label + ", children=" + children + "]";
	}

}
