package com.chuan.demo.test.beanFactoryPostProcessor;

import com.chuan.demo.test.Singleton;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 *
 * 子类可以在这步的时候添加一些特殊的 BeanFactoryPostProcessor 的实现类或做点什么事
 * 对bean进行增删改操作
 * @ClassName BeanFactoryPostProcessorTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/10 10:36
 **/
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        //添加bean
        Singleton bean = beanFactory.createBean(Singleton.class);

    }
}
