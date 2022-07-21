package cn.ahcoder.spring.beans.factory.config;

import cn.ahcoder.spring.beans.factory.BeanFactory;

/**
 * @description: 有自动装配能力的工厂接口
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * bean初始化方法之前调用,可以修改bean对象
     * @param name
     * @param bean
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(String name, Object bean);

    /**
     * bean初始化方法之后调用,可以修改bean对象
     * @param name
     * @param bean
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(String name, Object bean);
}
