package cn.ahcoder.spring.beans.factory.config;

/**
 * @description: bean依赖对象引用
 * @author：AhHao
 * @date: 2022/7/4
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
