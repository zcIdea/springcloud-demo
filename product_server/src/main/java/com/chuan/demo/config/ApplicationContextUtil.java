package com.chuan.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从ApplicationContextAware获取ApplicationContext上下文的情况，
 *   仅仅适用于当前运行的代码和已启动的Spring代码处于同一个Spring上下文，
 *   否则获取到的ApplicationContext是空的。
 * 参考 https://www.jianshu.com/p/4c0723615a52
 * @ClassName ApplicationContextUtil
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/25 14:17
 **/
//使用@Component来进行注册
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            return null;
        }else{
            return applicationContext.getBean(clazz);
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
