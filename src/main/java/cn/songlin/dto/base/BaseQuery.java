package cn.songlin.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：用于分页数据的获取
 * 
 * @author 刘松林
 * @version 1.0
 * @date 2018年3月9日
 */
@ApiModel
public class BaseQuery {

	@ApiModelProperty(value = "页码", example = "1")
	public Integer page = 1;

	@ApiModelProperty(value = "显示条数", example = "10")
	public Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "BaseQuery [page=" + page + ", rows=" + rows + "]";
	}

}
