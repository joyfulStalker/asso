package cn.songlin.mapper;

import cn.songlin.entity.TtPayOrder;
import tk.mybatis.mapper.common.BaseMapper;

public interface TtPayOrderMapper extends BaseMapper<TtPayOrder> {
	long[] getAllCode();
}