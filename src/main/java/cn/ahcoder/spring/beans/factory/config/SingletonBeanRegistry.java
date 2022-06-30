package cn.ahcoder.spring.beans.factory.config;

/**
 * @description: 单例bean注册中心
 * @author：AhHao
 * @date: 2022/6/25
 */
public interface SingletonBeanRegistry {

    /**
     * 注册单例bean
     * @param beanName
     * @param bean
     */
    void registerSingleton(String beanName,Object bean);

    /**
     * 获取单例bean
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}

