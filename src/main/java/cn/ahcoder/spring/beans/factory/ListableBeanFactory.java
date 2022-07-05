package cn.ahcoder.spring.beans.factory;

import cn.ahcoder.spring.beans.BeansException;

import java.util.Map;

/**
 * @description: 可以列出全部bean对象的工厂接口
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 是否存在同名的bean定义
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取全部bean定义名
     * @return
     */
    String[] getBeanDefinitionNames();

    /**
     * 根据类型获取bean
     * @param clazz
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String,T> getBeanOfType(Class<T> clazz) throws BeansException;
}
