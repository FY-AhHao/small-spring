package cn.ahcoder.spring.test;

import cn.ahcoder.spring.beans.PropertyValue;
import cn.ahcoder.spring.beans.PropertyValues;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanReference;
import cn.ahcoder.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.ahcoder.spring.beans.factory.xml.XmlBeanDefinitionReader;
import cn.ahcoder.spring.context.support.ClassPathXmlApplicationContext;
import cn.ahcoder.spring.test.bean.UserDao;
import cn.ahcoder.spring.test.bean.UserService;
import cn.ahcoder.spring.test.postProcessor.MyBeanFactoryPostProcessor;
import cn.ahcoder.spring.test.postProcessor.MyBeanPostProcessor;
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

    @Test
    public void testResource() {
        //0.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //1.创建xml bean定义读取器
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //2.加载xml配置文件，注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //3.获取bean
        UserService userService = beanFactory.getBean("userService",UserService.class);
        System.out.println(userService.queryUserInfo("333"));

    }

    @Test
    public void testPostProcessor() {
        //0.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //1.创建xml bean定义读取器
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //2.加载xml配置文件，注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //3.创建BeanFactoryPostProcessor,加载完beanDefinition后调用
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        //4.创建BeanPostProcessor,在bean初始化时被调用
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        //5.获取bean
        UserService userService = beanFactory.getBean("userService",UserService.class);
        System.out.println(userService.queryUserInfo("333"));
    }

    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:postProcessor.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo("222"));
    }
}
