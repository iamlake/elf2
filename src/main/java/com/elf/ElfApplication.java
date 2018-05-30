package com.elf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: ElfApplication
 * @Author: Liyiming
 * @Date: 2018/2/14
 */
@SpringBootApplication
@EnableTransactionManagement
public class ElfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElfApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/page/common_401");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/page/common_403");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/page/common_404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page/common_500");

            container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
        });
    }
}
