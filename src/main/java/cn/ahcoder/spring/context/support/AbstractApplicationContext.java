package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.beans.factory.config.ConfigurableListableBeanFactory;
import cn.ahcoder.spring.context.ApplicationEvent;
import cn.ahcoder.spring.context.ApplicationListener;
import cn.ahcoder.spring.context.ConfigurableApplicationContext;
import cn.ahcoder.spring.context.event.ContextClosedEvent;
import cn.ahcoder.spring.context.event.ContextRefreshedEvent;
import cn.ahcoder.spring.context.event.SimpleApplicationEventMulticaster;
import cn.ahcoder.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;


/**
 * @description: 抽象的应用上下文
 * @author：AhHao
 * @date: 2022/7/5
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private SimpleApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() {
        //1.初始化bean工厂
        refreshBeanFactory();

        //2.获取bean工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.注册应用上下文感知对象后置处理器
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.调用BeanFactoryPostProcessor，修改bean定义
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);

        //6.初始化事件广播器
        initApplicationEventMulticaster();

        //7.注册事件监听器
        registerListeners();

        //8.预实例化bean对象
        beanFactory.preInstantiateSingletons();

        //9.发布容器刷新完毕事件
        finishRefresh();
    }

    /**
     * 发布容器刷新完毕事件
     */
    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 注册事件监听器
     */
    private void registerListeners() {
        Collection<ApplicationListener> listeners = getBeanOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : listeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 初始化事件广播器
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }


    /**
     * 调用beanFactoryPostProcessor，修改bean定义
     *
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
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }

    /**
     * 初始化bean工厂
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获取bean工厂
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
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
