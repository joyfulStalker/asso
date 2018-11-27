package cn.songlin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.common.anno.Monitor;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.dto.task.TaskQueryDto;
import cn.songlin.dto.task.TtTaskDto;
import cn.songlin.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("task")
@Api("动态任务调度")
@ResponseBody
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/taskManage")
	@Monitor
	@ApiOperation(value = "定时任务管理--新增 修改")
	public ResponseBeanResult<Void> taskManage(@RequestBody TtTaskDto taskDto) throws Exception {
		taskService.taskManage(taskDto);
		return new ResponseBeanResult<>();
	}

	@PostMapping("/changeStatus")
	@Monitor
	@ApiOperation(value = "定时任务--启动 停止")
	public ResponseBeanResult<Void> changeStatus(@RequestBody TtTaskDto taskDto) throws Exception {
		taskService.changeStatus(taskDto);
		return new ResponseBeanResult<>();
	}

	@GetMapping("/detail")
	@Monitor
	@ApiOperation(value = "定时任务--详情")
	public ResponseBeanResult<TtTaskDto> detail(@RequestParam Long id) throws Exception {
		TtTaskDto taskDto = taskService.detail(id);
		return new ResponseBeanResult<>(taskDto);
	}

	@PostMapping("/taskList")
	@Monitor
	@ApiOperation(value = "定时任务列表")
	public ResponsePageResult taskList(@RequestBody TaskQueryDto queryDto) {
		return taskService.taskList(queryDto);
	}
	
	@GetMapping("/delete")
	@Monitor
	@ApiOperation(value = "定时任务--详情")
	public ResponseBeanResult<Void> delete(@RequestParam Long id) throws Exception {
		taskService.delete(id);
		return new ResponseBeanResult<>();
	}

	@GetMapping("/test")
	@Monitor
	@ApiOperation(value = "测试任务调度")
	public ResponseBeanResult<Void> test() {
		System.out.println("调用了1");
		return new ResponseBeanResult<>();
	}

	@GetMapping("/test2")
	@Monitor
	@ApiOperation(value = "测试任务调度")
	public ResponseBeanResult<Void> test2() {
		System.out.println("调用了2");
		return new ResponseBeanResult<>();
	}
}
