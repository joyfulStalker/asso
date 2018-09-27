package cn.songlin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.songlin.dto.errColl.ErrCollectionDto;
import cn.songlin.dto.errColl.ErrListQueryDto;
import cn.songlin.entity.TtErrCollection;
import tk.mybatis.mapper.common.BaseMapper;

public interface TtErrCollectionMapper extends BaseMapper<TtErrCollection> {

	List<ErrCollectionDto> errList(@Param("queryDto") ErrListQueryDto queryDto);

	void errDel(@Param("errCollId")long errCollId);
}