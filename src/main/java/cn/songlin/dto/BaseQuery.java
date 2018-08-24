package cn.songlin.dto;

import javax.persistence.Transient;

/**
 * 说明：用于分页数据的获取
 * @author 刘松林
 * @version 1.0
 * @date 2018年3月9日
 */
public class BaseQuery {

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

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
}
