package cn.ahcoder.spring.test;

import cn.ahcoder.spring.beans.PropertyValue;
import cn.ahcoder.spring.beans.PropertyValues;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanReference;
import cn.ahcoder.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.ahcoder.spring.test.bean.UserDao;
import cn.ahcoder.spring.test.bean.UserService;
import org.junit.Test;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {

        //0.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //1.创建BeanDefinition
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);

        //3.创建bean属性对象
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("testProperty","hello PropertyValue"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        userServiceBeanDefinition.setPropertyValues(propertyValues);

        //4.注册BeanDefinition
        beanFactory.registerBeanDefinition("userDao",userDaoBeanDefinition);
        beanFactory.registerBeanDefinition("userService",userServiceBeanDefinition);

        //5.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo("333"));
    }
}
