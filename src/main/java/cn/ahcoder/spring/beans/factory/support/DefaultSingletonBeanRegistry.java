package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.DisposableBean;
import cn.ahcoder.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
    private final Map<String, Object> singletonObjectMap = new ConcurrentHashMap<>();

    /**
     * 需要做销毁动作的bean缓存
     */
    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object bean) {
        singletonObjectMap.put(beanName, bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectMap.get(beanName);
    }

    /**
     * 注册需要做销毁动作的bean
     *
     * @param name
     * @param disposableBeanAdapter
     */
    protected void registerDisposableBean(String name, DisposableBeanAdapter disposableBeanAdapter) {
        synchronized (this.disposableBeans) {
            disposableBeans.put(name, disposableBeanAdapter);
        }
    }


    /**
     * 销毁单例bean
     */
    public void destroySingletons() {
        Set<String> beanNameSet = disposableBeans.keySet();
        String[] beanNameArr = beanNameSet.toArray(new String[0]);
        for (int i = beanNameArr.length - 1; i >= 0; i--) {
            String beanName = beanNameArr[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("销毁失败[" + beanName + "]", e);
            }
        }

    }


}
