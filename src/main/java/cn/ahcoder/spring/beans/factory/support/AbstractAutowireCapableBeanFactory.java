package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description: 抽象的有自动装配能力的bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            //反射创建bean对象
            bean = createBeanInstance(name, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("创建" + name + "对象失败", e);
        }

        //注册bean对象到单例bean注册中心
        registerSingleton(name, bean);
        return bean;
    }

    /**
     * 创建bean实例
     *
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) {
        Constructor ctor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            //这里为了串流程，只是做个简单判断使用哪个构造器，实际spring框架中处理会更加复杂
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                ctor = declaredConstructor;
                break;
            }
        }
        //根据具体的参数使用具体的构造器来反射创建对象
        return getInstantiationStrategy().instantiate(name, beanDefinition, ctor, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
