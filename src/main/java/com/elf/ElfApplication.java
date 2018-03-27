package com.elf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;

/**
 * @Description: ElfApplication
 * @Author: Liyiming
 * @Date: 2018/2/14
 */
@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class ElfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElfApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/page/common_404");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/page/common_404");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page/common_404");

			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}

	@Bean
	public FilterRegistrationBean hiddenHttpMethodFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HiddenHttpMethodFilter());
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean httpPutFormContentFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HttpPutFormContentFilter());
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean requestContextFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new RequestContextFilter());
		filterRegistrationBean.setOrder(3);
		return filterRegistrationBean;
	}
}
