package com.elf.core.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title MybatisPlusConfig
 * @Description
 * @Author icelake
 * @Date 2018/3/14 10:59
 */
@Configuration
@MapperScan("com.elf.*.*.mapper")
public class MybatisPlusConfig {

    /**
     * @Description: 分页插件，自动识别数据库类型
     * @return: com.baomidou.mybatisplus.plugins.PaginationInterceptor
     * @Author: Liyiming
     * @Date: 2018/3/14
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
   /*
    * oracle数据库配置JdbcTypeForNull
    * 参考：https://gitee.com/baomidou/mybatisplus-boot-starter/issues/IHS8X
    不需要这样配置了，参考 yml:
    mybatis-plus:
      confuguration
        dbc-type-for-null: 'null'
   @Bean
   public ConfigurationCustomizer configurationCustomizer(){
       return new MybatisPlusCustomizers();
   }

   class MybatisPlusCustomizers implements ConfigurationCustomizer {

       @Override
       public void customize(org.apache.ibatis.session.Configuration configuration) {
           configuration.setJdbcTypeForNull(JdbcType.NULL);
       }
   }
   */
}
