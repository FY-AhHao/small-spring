package cn.ahcoder.spring.beans.factory.config;

/**
 * @description: bean工厂后置处理器
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface BeanFactoryPostProcessor {

    /**
     * 所有的beanDefinition加载完毕后，单例bean对象实例化前，会被调用，提供修改beanDefinition的扩展点
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
