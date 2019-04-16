package cn.m.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.m.config.interceptor.ParamInterceptor;

/**
 * 系统配置
 * @author Administrator
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ParamInterceptor()).addPathPatterns("/**");
	}

	
}
