package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.context.ApplicationContext;
import cn.ahcoder.spring.context.ApplicationContextAware;

/**
 * @description: 应用上下文感知后置处理器，在对bean进行初始化的时候调用，
 * 对实现ApplicationContextAware接口的bean对象注入上下文
 * @author：AhHao
 * @date: 2022/7/21
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        return bean;
    }
}
