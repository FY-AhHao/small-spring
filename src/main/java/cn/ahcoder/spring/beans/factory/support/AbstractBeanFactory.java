package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.factory.BeanFactory;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;

/**
 * @description: 抽象bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }

    /**
     * 获取bean
     * @param beanName
     * @param args
     * @return
     */
    protected Object doGetBean(final String beanName, final Object[] args) {
        //尝试从单例bean注册中心获取单例bean
        Object singleton = getSingleton(beanName);

        if (singleton != null) {
            return singleton;
        }

        //单例bean注册中心没有则获取beanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        //通过beanDefinition去创建单例bean，并放入单例bean注册中心
        singleton = createBean(beanName, beanDefinition,args);

        return singleton;
    }

    /**
     * 创建bean
     * AbstractAutowireCapableBeanFactory子类实现
     *
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

    /**
     * 获取bean定义
     * DefaultListableBeanFactory子类实现
     *
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);
}
