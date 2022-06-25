package cn.ahcoder.spring;

/**
 * @description: bean定义信息
 * @author：AhHao
 * @date: 2022/6/25
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
