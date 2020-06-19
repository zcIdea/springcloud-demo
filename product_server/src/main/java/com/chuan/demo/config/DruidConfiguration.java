package com.chuan.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 *
 * 在浏览器输入http://127.0.0.1:8768/druid/index.html，就会显示登录页面，输入上面类配置的密码即可登录
 * 然后在程序中执行查询语句，再看到druid页面，查看SQL监控就会有刚才执行的sql信息，URL监控和Session监控也有对应的信息
 *
 * 可直接在资源文件配置以下属性，不用创建config类文件
 * spring.datasource.druid.web-stat-filter.enabled=true
 * spring.datasource.druid.web-stat-filter.url-pattern=/*
 * spring.datasource.druid.web-stat-filter.exclusions=*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
 * spring.datasource.druid.web-stat-filter.session-stat-enable=true
 * spring.datasource.druid.web-stat-filter.session-stat-max-count=1000
 *
 * spring.datasource.druid.stat-view-servlet.enabled= true
 * spring.datasource.druid.stat-view-servlet.url-pattern=/druid/*
 * spring.datasource.druid.stat-view-servlet.reset-enable=true
 * spring.datasource.druid.stat-view-servlet.login-username=druid
 * spring.datasource.druid.stat-view-servlet.login-password=123456
 * spring.datasource.druid.stat-view-servlet.allow=127.0.0.1
 * spring.datasource.druid.stat-view-servlet.deny=192.168.0.19
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}