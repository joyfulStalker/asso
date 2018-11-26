package cn.songlin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.dto.task.TaskQueryDto;
import cn.songlin.dto.task.TtTaskDto;
import cn.songlin.emun.JobStatusEnum;
import cn.songlin.entity.TtTask;
import cn.songlin.mapper.TtTaskMapper;
import cn.songlin.quartz.QuartzManager;
import cn.songlin.quartz.TaskDO;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {

	@Autowired
	private TtTaskMapper taskMapper;
	@Autowired
	private QuartzManager quartzManager;

	public void initSchedule() {
		List<TtTask> list = taskMapper.select(null);
		for (TtTask ttTask : list) {
			if (!ttTask.getIsDelete() && JobStatusEnum.RUNNING.getVal().equals(ttTask.getJobStatus())) {
				TaskDO taskDO = new TaskDO();
				BeanUtils.copyProperties(ttTask, taskDO);
				quartzManager.addJob(taskDO);
			}
		}
	}

	public ResponsePageResult taskList(TaskQueryDto queryDto) {
		PageHelper.startPage(queryDto.getPage(), queryDto.getRows());
		List<TtTask> list = taskMapper.selectAll();
		List<TtTaskDto> res = new ArrayList<>();
		for (TtTask ttTask : list) {
			if (!ttTask.getIsDelete()) {
				TtTaskDto dto = new TtTaskDto();
				BeanUtils.copyProperties(ttTask, dto);
				res.add(dto);
			}
		}
		PageInfo<TtTask> info = new PageInfo<>(list);
		return new ResponsePageResult(res, info.getTotal());
	}

	public void taskManage(TtTaskDto taskDto) throws Exception {
		// 判断新增还是修改
		if (null == taskDto.getId()) {// 新增
			// 判断是否立即执行
			TtTask task = new TtTask();
			BeanUtils.copyProperties(taskDto, task);
			task.setBeanClass("cn.songlin.quartz.ExecuteJob");
			task.setCreateTime(new Date());
			task.setCreateBy(UserLocal.getLocalUser().getUserId());
			task.setJobStatus(taskDto.getJobStatus());
			taskMapper.insertSelective(task);
			// 添加任务
			if (JobStatusEnum.RUNNING.getVal().equals(taskDto.getJobStatus())) {
				TaskDO taskDo = new TaskDO();
				BeanUtils.copyProperties(task, taskDo);
				quartzManager.addJob(taskDo);
			}
		} else {
			TtTask task = taskMapper.selectByPrimaryKey(taskDto.getId());
			if (null == task || task.getIsDelete()) {
				throw AssoException.NO_SUCH_TASK;
			}
			if (!JobStatusEnum.STOP.getVal().equals(task.getJobStatus())) {
				throw AssoException.PLE_STOP_FIRST;
			}
			BeanUtils.copyProperties(taskDto, task);
			// 添加任务
			if (JobStatusEnum.RUNNING.getVal().equals(taskDto.getJobStatus())) {
				TaskDO taskDo = new TaskDO();
				BeanUtils.copyProperties(task, taskDo);
				quartzManager.addJob(taskDo);
			}
			task.setUpdateBy(UserLocal.getLocalUser().getUserId());
			task.setUpdateTime(new Date());
			taskMapper.updateByPrimaryKey(task);
		}

	}

	public void changeStatus(TtTaskDto taskDto) throws Exception {
		TtTask task = taskMapper.selectByPrimaryKey(taskDto.getId());
		if (null == task || task.getIsDelete()) {
			return;
		}
		TaskDO taskDo = new TaskDO();
		BeanUtils.copyProperties(task, taskDo);
		// 判断状态
		if (taskDto.getJobStatus().equals(taskDo.getJobStatus())) {
			// 状态前后一致不做处理
			return;
		}
		if (JobStatusEnum.RUNNING.getVal().equals(taskDto.getJobStatus())) {
			taskDo.setJobStatus(JobStatusEnum.RUNNING.getVal());
			task.setJobStatus(JobStatusEnum.RUNNING.getVal());
			quartzManager.addJob(taskDo);
		} else if (JobStatusEnum.STOP.getVal().equals(taskDto.getJobStatus())) {
			quartzManager.deleteJob(taskDo);
			task.setJobStatus(JobStatusEnum.STOP.getVal());
		}
		task.setUpdateBy(UserLocal.getLocalUser().getUserId());
		task.setUpdateTime(new Date());
		taskMapper.updateByPrimaryKeySelective(task);
	}

	public TtTaskDto detail(Long id) {
		TtTask task = taskMapper.selectByPrimaryKey(id);
		TtTaskDto taskDto = new TtTaskDto();
		if (null != task) {
			BeanUtils.copyProperties(task, taskDto);
		}
		return taskDto;
	}

	public void delete(Long id) {
		TtTask task = taskMapper.selectByPrimaryKey(id);
		if (null == task || task.getIsDelete()) {
			throw AssoException.NO_SUCH_TASK;
		}
		if (!JobStatusEnum.STOP.getVal().equals(task.getJobStatus())) {
			throw AssoException.PLE_STOP_FIRST;
		}
		task.setIsDelete(true);
		task.setUpdateBy(UserLocal.getLocalUser().getUserId());
		task.setUpdateTime(new Date());
		taskMapper.updateByPrimaryKeySelective(task);
	}

}
