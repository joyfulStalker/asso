package cn.songlin.dto.user;

import cn.songlin.common.dto.base.BaseQuery;

public class UserQueryDto extends BaseQuery {

	private String searchKey;// 搜索词

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

}
