package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: factoryBean注册中心支持
 * @author：AhHao
 * @date: 2022/7/21
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * factoryBean创建出来的bean对象缓存
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 从缓存获取factoryBean创建出来的bean对象
     *
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String name) {
        return factoryBeanObjectCache.get(name);
    }

    /**
     * 从factoryBean中获取bean对象
     *
     * @param factoryBean
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object bean = factoryBeanObjectCache.get(beanName);
            if (bean == null) {
                bean = doGetObjectFromFactoryBean(factoryBean, beanName);
                factoryBeanObjectCache.put(beanName,bean);
            }
            return bean;
        }
        return doGetObjectFromFactoryBean(factoryBean, beanName);
    }

    /**
     * 从factoryBean中获取bean对象
     *
     * @param factoryBean
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("factoryBean创建[" + beanName + "]失败", e);
        }
    }


}
