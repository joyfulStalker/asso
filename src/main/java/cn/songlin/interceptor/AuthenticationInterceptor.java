package cn.songlin.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import cn.songlin.annotation.Access;
import cn.songlin.common.dto.LocalUser;
import cn.songlin.common.utils.ClientIpUtils;
import cn.songlin.common.utils.UserLocal;
import cn.songlin.entity.UserLog;

/**
 * 自定义一个权限拦截器, 继承HandlerInterceptorAdapter类
 * 
 * @author liusonglin
 * @date 2018年10月31日
 */

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 在调用方法之前执行拦截
	 * 
	 * @author liusonglin
	 * @date 2018年10月31日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 *             (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		if (servletPath != null && servletPath.contains("swagger")) {// 过滤swagger的请求
			return true;
		}

		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//		LocalUser userAccount = (LocalUser) request.getSession().getAttribute("sessionId");
		UserLocal userLocal = (UserLocal)factory.getBean("userLocal");
		LocalUser userAccount = userLocal.getLocalUser();
		// UserLogMapper userLogMapper = (UserLogMapper)
		// factory.getBean("userLogMapper");
		AmqpTemplate template = (AmqpTemplate) factory.getBean("rabbitTemplate");
		// 访问足迹记录
		UserLog userLog = new UserLog();
		userLog.setLogDataTime(new Date());
		userLog.setLogIp(ClientIpUtils.getClientIp(request));
		userLog.setLogUrl(servletPath);
		if (userAccount != null) {
			userLog.setLogUserid(userAccount.getUserId());
			userLog.setLogUsername(userAccount.getName());
		}
		// 记录采用mq转发的形式来做
		// userLogMapper.insertSelective(userLog);
		String userLogString = JSONObject.toJSONString(userLog);
		template.convertAndSend("track", userLogString);

		// access权限验证
		if (handler instanceof HandlerMethod) {// 请求是handlermethod进行强转
			// 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 从方法处理器中获取出要调用的方法
			Method method = handlerMethod.getMethod();
			// 获取出方法上的Access注解
			Access access = method.getAnnotation(Access.class);
			if (access == null) {
				// 如果注解为null, 说明不需要拦截, 直接放过
				return true;
			}
			System.out.println("开始验证权限。。。");

			// 用户类型：1：普通用户 2：媒体用户 3：官方用户
			if (userAccount == null || userAccount.getUserType() < 2) {
				System.out.println("权限不足。。。");
				return false;
			}
			System.out.println("通过验证。。。");
		}
		// 拦截之后应该返回公共结果, 这里没做处理
		return true;
	}

}