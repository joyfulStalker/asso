package cn.songlin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liusonglin
 * @date 2018年8月1日
 */

@Component
@Aspect
@Order(5)
public class MonitorAspect {
	private static final Logger logger = LoggerFactory.getLogger(MonitorAspect.class);

	@Around("@annotation(cn.songlin.annotation.Monitor)")
	public Object logServiceAccess(ProceedingJoinPoint pjp) throws Throwable {

		/**
		 * 记录切面的信息
		 */
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String fullMethodName = className + "." + methodName;
		logger.info(fullMethodName + "将被调用");
		long start = System.currentTimeMillis();
		Object result = null;
		result = pjp.proceed();
		long end = System.currentTimeMillis();
		long elapsedMilliseconds = end - start;
		logger.info(fullMethodName + "调用结束," + "执行耗时:" + elapsedMilliseconds + " 毫秒");

		return result;
	}
}