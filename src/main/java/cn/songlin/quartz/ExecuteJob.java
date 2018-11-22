package cn.songlin.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import cn.hutool.http.HttpUtil;

@DisallowConcurrentExecution // 作业不并发
@Component
public class ExecuteJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDataMap map = arg0.getJobDetail().getJobDataMap();
		String url = (String) map.get("url");
		HttpUtil.get(url);
	}

}