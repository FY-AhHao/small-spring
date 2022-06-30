package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.factory.config.BeanDefinition;

/**
 * @description: bean定义注册中心
 * @author：AhHao
 * @date: 2022/6/25
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取beanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);
}
