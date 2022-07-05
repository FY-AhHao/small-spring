package cn.ahcoder.spring.beans.factory.config;

import cn.ahcoder.spring.beans.factory.ListableBeanFactory;

/**
 * @description: 可以列出全部bean对象且有配置能力的工厂接口，还提供了分析修改bean定义，预实例化单例的工具
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface ConfigurableListableBeanFactory extends AutowireCapableBeanFactory, ListableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取bean定义
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 预实例化单例bean对象
     */
    void preInstantiateSingletons();
}
