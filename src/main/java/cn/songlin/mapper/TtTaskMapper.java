package cn.songlin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.songlin.dto.task.TaskQueryDto;
import cn.songlin.entity.TtTask;
import tk.mybatis.mapper.common.BaseMapper;

public interface TtTaskMapper extends BaseMapper<TtTask> {

	List<TtTask> selectUsedTask(@Param("queryDto") TaskQueryDto queryDto);
}