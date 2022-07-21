package cn.ahcoder.spring.beans.factory;

/**
 * @description: beanFactory感知标记接口
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);
}
