package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description: bean实例化策略
 * @author：AhHao
 * @date: 2022/7/4
 */
public interface InstantiationStrategy {

    /**
     * 实例化bean
     * @param beanName
     * @param beanDefinition
     * @param ctor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor,Object[] args) throws BeansException;
}
