package com.elf.core.config;

import com.elf.techcomp.services.service.impl.MdmServiceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @program: elf
 * @description: CXF配置类
 * @author: Liyiming
 * @create: 2018-05-05 18:42
 **/
@Configuration
public class CxfConfig {

    private static final Logger logger = LoggerFactory.getLogger(CxfConfig.class);

    @Autowired
    private Bus bus;

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Arrays.<Object>asList(new MdmServiceImpl()));
        endpoint.setProvider(new JacksonJaxbJsonProvider());
        logger.info("设置JSON适配器-->jacksonJaxbJsonProvider", JacksonJaxbJsonProvider.class);
        return endpoint.create();
    }
}
