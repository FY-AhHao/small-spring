package cn.ahcoder.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public class BeanFactory {

    /**
     * 存放beanDefinition，可以理解为beanDefinition的注册中心
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }
}

