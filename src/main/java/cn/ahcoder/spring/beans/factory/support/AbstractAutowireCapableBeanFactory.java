package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;

/**
 * @description: 抽象的有自动装配能力的bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            //反射创建bean对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("创建"+name+"对象失败",e);
        }

        //注册bean对象到单例bean注册中心
        registerSingleton(name,bean);
        return bean;
    }

}
