package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.ConfigurableListableBeanFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 默认的可检索的bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, Serializable {

    /**
     * beanDefinition缓存
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("找不到" + beanName + "的BeanDefinition");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> clazz) throws BeansException {
        HashMap<String, T> resultMap = new HashMap<>();
        this.beanDefinitionMap.forEach((beanName,beanDefinition) -> {
            if (clazz.isAssignableFrom(beanDefinition.getBeanClass())) {
                resultMap.put(beanName, (T) getBean(beanName));
            }
        });
        return resultMap;
    }
}
