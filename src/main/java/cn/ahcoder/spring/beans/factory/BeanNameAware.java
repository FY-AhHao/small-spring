package cn.ahcoder.spring.beans.factory;

/**
 * @description: beanName感知标记接口
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
