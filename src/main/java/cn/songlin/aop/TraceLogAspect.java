package cn.songlin.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import cn.songlin.common.dto.base.BaseCode;
import cn.songlin.common.dto.base.ResponseBeanResult;
import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.BizException;
import cn.songlin.common.utils.ClientIpUtils;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.entity.TtOnlinepayTrack;
import cn.songlin.mapper.TtOnlinepayTrackMapper;

/**
 * aop记录访问足迹
 * 
 * @author liusonglin
 * @date 2018年9月8日
 */

@Component
@Aspect
@Order(4)
@SuppressWarnings("all")
public class TraceLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(TraceLogAspect.class);

	@Autowired
	private TtOnlinepayTrackMapper trackMapper;

	@Around("@annotation(cn.songlin.annotation.TrackLog)")
	public Object logServiceAccess(ProceedingJoinPoint pjp) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		TtOnlinepayTrack entity = new TtOnlinepayTrack();

		// 请求参数
		Object[] args = pjp.getArgs();// 参数

		Map requestParm = new HashMap<>();

		for (Object parm : args) {

			Class<? extends Object> class1 = parm.getClass();

			if (!"org.apache.catalina.connector.RequestFacade".equals(parm.getClass().getName())) {

				requestParm.put(parm.getClass().getName(), parm);

			}
		}

		entity.setRequestData(JSON.toJSONString(requestParm));// 需要存入数据库

		entity.setUri(request.getRequestURI());// 请求地址

		entity.setCreateTime(new Date());

		entity.setRequestSource(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());

		entity.setRequestIp(ClientIpUtils.getClientIp(request));// 设置请求者ip地址

		Long userId = UserLocal.getLocalUser().getId();

		entity.setUserid(userId == null ? null : userId.toString());

		// 处理返回结果
		Object result = null;

		Object errResult = null;

		String resClass = pjp.getSignature().toLongString().split(" ")[1];// 获取返回值类型

		boolean flag = true;

		try {

			result = pjp.proceed();

		} catch (BizException e) {

			flag = false;

			result = new HashMap<>();

			((Map) result).put("errType", e.getClass().getName());

			((Map) result).put("errMsg", e.getMsg());

			errResult = getResponseInstance(resClass, e.getMsg(), e.getCode(), errResult);

		} catch (Throwable e) {

			flag = false;

			result = new HashMap<>();

			((Map) result).put("errType", e.getClass().getName());

			((Map) result).put("errMsg", e.getMessage());

			errResult = getResponseInstance(resClass, e.getMessage(), BaseCode.ERROR_UNKNOWN, errResult);

		}

		entity.setResponeData(JSON.toJSONString(result));

		trackMapper.insert(entity);// 执行插入数据到数据库

		return flag ? result : errResult;
	}

	private Object getResponseInstance(String resClass, String msg, Integer code, Object obj) {
		if ("cn.songlin.dto.base.ResponseBeanResult".contains(resClass)) {
			obj = new ResponseBeanResult(msg, code);
		}
		if ("cn.songlin.dto.base.ResponsePageResult".contains(resClass)) {
			obj = new ResponsePageResult(msg, code);
		}
		return obj;
	}
}