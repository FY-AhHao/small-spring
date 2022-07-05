package cn.ahcoder.spring.beans.factory.config;

import cn.ahcoder.spring.beans.factory.HierarchicalBeanFactory;

/**
 * @description: 具有配置能力的bean工厂
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型
     */
    String SCOPE_PROTOTYPE = "prototype";

}
