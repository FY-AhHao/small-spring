package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.factory.DisposableBean;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.beans.factory.config.ConfigurableBeanFactory;
import cn.ahcoder.spring.util.ClassUtils;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽象bean工厂
 * @author：AhHao
 * @date: 2022/6/25
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * beanPostProcessor缓存
     */
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    private final ClassLoader beanClassloader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        return (T) getBean(beanName);
    }

    /**
     * 获取bean
     *
     * @param beanName
     * @param args
     * @return
     */
    protected Object doGetBean(final String beanName, final Object[] args) {
        //尝试从单例bean注册中心获取单例bean
        Object singleton = getSingleton(beanName);

        if (singleton != null) {
            return singleton;
        }

        //单例bean注册中心没有则获取beanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        //通过beanDefinition去创建单例bean，并放入单例bean注册中心
        singleton = createBean(beanName, beanDefinition, args);

        return singleton;
    }

    /**
     * 创建bean
     * AbstractAutowireCapableBeanFactory子类实现
     *
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

    /**
     * 获取bean定义
     * DefaultListableBeanFactory子类实现
     *
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessorList;
    }


    public ClassLoader getBeanClassloader() {
        return beanClassloader;
    }

    /**
     * 注册需要做销毁动作的bean对象
     *
     * @param name
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String name, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotBlank(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition));
        }
    }

}

