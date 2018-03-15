package com.elf.core.common.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: MybatisGenerator
 * @author: Liyiming
 * @create: 2017-10-25 15:32
 **/
public class MybatisGenerator {
    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = false;
        // 读取配置文件
        URL url = MybatisGenerator.class.getClassLoader().getResource("config/generatorConfig.xml");
        File configFile = new File(url.getFile());
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration config;
        try {
            config = configurationParser.parseConfiguration(configFile);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator;
            try {
                myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);

                // 打印结果
                for (String str : warnings) {
                    System.out.println(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
}
