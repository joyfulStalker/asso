package cn.songlin.service;

import org.springframework.stereotype.Service;

import cn.songlin.entity.UserLog;
import cn.songlin.mapper.UserLogMapper;

@Service
public class UserLogService {

	private UserLogMapper userLogMapper;

	public UserLog insert(UserLog userLog) {
		userLogMapper.insertSelective(userLog);
		return userLog;
	}
}
