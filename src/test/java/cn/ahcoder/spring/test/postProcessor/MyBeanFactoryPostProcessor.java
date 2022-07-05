package cn.ahcoder.spring.test.postProcessor;

import cn.ahcoder.spring.beans.PropertyValue;
import cn.ahcoder.spring.beans.PropertyValues;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.ahcoder.spring.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/5
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition userServiceBeanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userServiceBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("testBeanFactoryPostProcessorProperty","MyBeanFactoryPostProcessor修改beanDefinition"));
    }
}
