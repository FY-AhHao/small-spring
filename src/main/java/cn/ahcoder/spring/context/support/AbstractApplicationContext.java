package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.beans.factory.config.ConfigurableListableBeanFactory;
import cn.ahcoder.spring.context.ConfigurableApplicationContext;
import cn.ahcoder.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @description: 抽象的应用上下文
 * @author：AhHao
 * @date: 2022/7/5
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        //1.初始化bean工厂
        refreshBeanFactory();

        //2.获取bean工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.调用BeanFactoryPostProcessor，修改bean定义
        invokeBeanFactoryPostProcessors(beanFactory);

        //4.注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);

        //5.预实例化bean对象
        beanFactory.preInstantiateSingletons();
    }


    /**
     * 调用beanFactoryPostProcessor，修改bean定义
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册beanPostProcessor,在bean实例化后，beanPostProcessor会被调用，增强bean对象
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 初始化bean工厂
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获取bean工厂
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName,args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        return getBeanFactory().getBean(beanName, clazz);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> clazz) throws BeansException {
        return getBeanFactory().getBeanOfType(clazz);
    }


}
