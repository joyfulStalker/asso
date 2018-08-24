package cn.songlin.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.songlin.interceptor.AuthenticationInterceptor;

/**
 * @author 60204
 *         这里使用WebMvcConfigurer接口而不是用WebMvcConfigurationSupport的原因是WebMvcConfigurationSupport已经过期
 *         这两种方法都可以添加拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
	}

	/**
	 * 设置跨域全局配置全局配置
	 * 
	 * @author liusonglin
	 * @date 2018年5月22日
	 * @param registry
	 */
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("*").maxAge(3600);
	}
}