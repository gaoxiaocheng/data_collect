package com.machine.record.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean startViewServlet() {
        ServletRegistrationBean druidServlet = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "root");

        initParams.put("allow", "");//默认就是允许
        initParams.put("deny", "192.168.15.21");

        druidServlet.setInitParameters(initParams);

        return druidServlet;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        filter.setInitParameters(initParams);
        filter.setUrlPatterns(Arrays.asList("/*"));

        return filter;
    }


}