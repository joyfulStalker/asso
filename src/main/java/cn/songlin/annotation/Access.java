package cn.songlin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 会被webmvc拦截器拦截校验
 * 
 * @author liusonglin
 * @date 2018年5月31日
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)//拦截器获取该注解需要加
public @interface Access {

}