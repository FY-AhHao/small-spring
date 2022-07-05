package cn.ahcoder.spring.test.postProcessor;

import cn.ahcoder.spring.beans.factory.config.BeanPostProcessor;
import cn.ahcoder.spring.test.bean.UserService;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/5
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if ("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setTestBeanPostProcessorProperty("MyBeanPostProcessor修改userService属性值");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        return bean;
    }
}
