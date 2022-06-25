package cn.ahcoder.spring.test;

import cn.ahcoder.spring.BeanDefinition;
import cn.ahcoder.spring.BeanFactory;
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
        BeanFactory beanFactory = new BeanFactory();

        //1.创建BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        //2.注册BeanDefinition
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo("111"));
    }
}
