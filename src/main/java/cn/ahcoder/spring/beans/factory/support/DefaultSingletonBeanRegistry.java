package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 默认的单例bean注册中心
 * @author：AhHao
 * @date: 2022/6/25
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例bean缓存
     */
    private final Map<String,Object> singletonObjectMap = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object bean) {
        singletonObjectMap.put(beanName,bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectMap.get(beanName);
    }
}
