package cn.songlin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.dto.task.TaskQueryDto;
import cn.songlin.dto.task.TtTaskDto;
import cn.songlin.emun.JobStatusEnum;
import cn.songlin.entity.TtTask;
import cn.songlin.mapper.TtTaskMapper;
import cn.songlin.quartz.QuartzManager;
import cn.songlin.quartz.TaskDO;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TtTaskMapper taskMapper;
	@Autowired
	private QuartzManager quartzManager;

	public void initSchedule() {
		List<TtTask> list = taskMapper.select(null);
		for (TtTask ttTask : list) {
			if (JobStatusEnum.RUNNING.getVal().equals(ttTask.getJobStatus())) {
				TaskDO taskDO = new TaskDO();
				BeanUtils.copyProperties(ttTask, taskDO);
				quartzManager.addJob(taskDO);
			}
		}
	}

	public ResponsePageResult taskList(TaskQueryDto queryDto) {
		PageHelper.startPage(queryDto.getPage(), queryDto.getRows());
		List<TtTask> list = taskMapper.selectAll();
		PageInfo<TtTask> info = new PageInfo<>(list);
		return new ResponsePageResult(list, info.getTotal());
	}

	public void taskManage(TtTaskDto taskDto) throws Exception {
		// 判断新增还是修改
		if (null == taskDto.getId()) {// 新增
			// 判断是否立即执行
			if (JobStatusEnum.RUNNING.getVal().equals(taskDto.getJobStatus())) {
				TtTask task = new TtTask();
				BeanUtils.copyProperties(taskDto, task);
				task.setBeanClass("cn.songlin.quartz.ExecuteJob");
				task.setCreateTime(new Date());
				task.setCreateBy(UserLocal.getLocalUser().getUserId());
				taskMapper.insertSelective(task);

				// 执行任务
				TaskDO taskDo = new TaskDO();
				BeanUtils.copyProperties(task, taskDo);
				quartzManager.addJob(taskDo);
			}
		} else {

		}

	}

}