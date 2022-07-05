package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.beans.factory.config.ConfigurableListableBeanFactory;
import cn.ahcoder.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @description: 抽象的有刷新能力的应用上下文，配合父类的refresh()方法
 * @author：AhHao
 * @date: 2022/7/5
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private volatile DefaultListableBeanFactory defaultListableBeanFactory = null;

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.defaultListableBeanFactory = beanFactory;
    }

    /**
     * 加载bean定义
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 创建bean工厂
     * @return
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.defaultListableBeanFactory;
    }
}
