package cn.ahcoder.spring.beans.factory.config;

/**
 * @description: bean定义信息
 * @author：AhHao
 * @date: 2022/6/25
 */
public class BeanDefinition {

    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
