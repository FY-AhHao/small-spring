package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.core.io.Resource;
import cn.ahcoder.spring.core.io.ResourceLoader;

/**
 * @description: bean定义读取器
 * @author：AhHao
 * @date: 2022/7/4
 */
public interface BeanDefinitionReader {

    /**
     * 获取bean定义注册中心
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载bean定义
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载bean定义
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 加载bean定义
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 加载bean定义
     * @param configLocations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... configLocations) throws BeansException;
}
