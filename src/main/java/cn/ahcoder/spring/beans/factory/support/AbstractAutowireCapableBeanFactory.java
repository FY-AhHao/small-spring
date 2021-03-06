package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.PropertyValue;
import cn.ahcoder.spring.beans.PropertyValues;
import cn.ahcoder.spring.beans.factory.BeanClassLoaderAware;
import cn.ahcoder.spring.beans.factory.BeanFactoryAware;
import cn.ahcoder.spring.beans.factory.BeanNameAware;
import cn.ahcoder.spring.beans.factory.InitializingBean;
import cn.ahcoder.spring.beans.factory.config.AutowireCapableBeanFactory;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @description: 抽象的有自动装配能力的bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            //反射创建bean对象
            bean = createBeanInstance(name, beanDefinition, args);

            //bean属性填充
            applyPropertyValues(name, bean, beanDefinition);

            //初始化bean对象
            bean = initializeBean(name, bean, beanDefinition);

        } catch (Exception e) {
            throw new BeansException("创建" + name + "对象失败", e);
        }

        //注册需要做销毁动作的bean对象(实现了DisposableBean接口)
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);

        //注册bean对象到单例bean注册中心
        if (beanDefinition.isSingleton()){
            registerSingleton(name, bean);
        }
        return bean;
    }


    /**
     * 初始化单例bean对象
     *
     * @param name
     * @param bean
     * @param beanDefinition
     * @return
     */
    protected Object initializeBean(String name, Object bean, BeanDefinition beanDefinition) {
        invokeAwareMethods(name, bean);

        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(name, bean);

        try {
            invokeInitMethod(name, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("调用[" + name + "]的初始化方法失败", e);
        }

        wrappedBean = applyBeanPostProcessorsAfterInitialization(name, bean);

        return wrappedBean;
    }

    private void invokeAwareMethods(String name, Object bean) {
        if (bean instanceof BeanNameAware) {
            ((BeanNameAware) bean).setBeanName(name);
        }

        if (bean instanceof BeanClassLoaderAware) {
            ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassloader());
        }

        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
    }


    /**
     * 调用BeanPostProcessor的postProcessBeforeInitialization方法
     *
     * @param name
     * @param bean
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(String name, Object bean) {
        Object resultBean = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object tempResultBean = beanPostProcessor.postProcessBeforeInitialization(name, resultBean);
            if (tempResultBean == null) {
                return resultBean;
            }
            resultBean = tempResultBean;
        }
        return resultBean;
    }

    /**
     * 初始化方法
     *
     * @param name
     * @param wrappedBean
     * @param beanDefinition
     */
    private void invokeInitMethod(String name, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
        //如果bean对象实现了InitializingBean接口，则调用初始化方法
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }

        //如果xml中配置了init-method,则调用对应的初始化方法(注意避免重复初始化)
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotBlank(initMethodName)
                && !(wrappedBean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            Method method = wrappedBean.getClass().getMethod(initMethodName);
            if (method == null) {
                throw new BeansException(name + "中找不到初始化方法: " + initMethodName);
            }
            method.invoke(wrappedBean);
        }

    }

    /**
     * 调用BeanPostProcessor的postProcessAfterInitialization方法
     *
     * @param name
     * @param bean
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(String name, Object bean) {
        Object resultBean = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object tempResultBean = beanPostProcessor.postProcessAfterInitialization(name, resultBean);
            if (tempResultBean == null) {
                return resultBean;
            }
            resultBean = tempResultBean;
        }
        return resultBean;
    }

    /**
     * bean属性填充
     *
     * @param name
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String name, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String propertyName = propertyValue.getName();
                Object propertyVal = propertyValue.getValue();

                //判断依赖的属性是否为对象,是的话递归获取依赖的bean对象
                if (propertyVal instanceof BeanReference) {
                    String beanName = ((BeanReference) propertyVal).getBeanName();
                    propertyVal = getBean(beanName);
                }

                //反射设置属性值
                BeanUtil.setFieldValue(bean, propertyName, propertyVal);
            }
        } catch (Exception e) {
            throw new BeansException(name + " bean属性填充发生错误", e);
        }
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
