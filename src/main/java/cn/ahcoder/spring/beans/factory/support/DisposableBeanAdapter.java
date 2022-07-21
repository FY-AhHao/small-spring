package cn.ahcoder.spring.beans.factory.support;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.factory.DisposableBean;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;

/**
 * @description: 销毁bean适配器，目的是统一调用方式，被虚拟机关闭前的钩子调用
 * @author：AhHao
 * @date: 2022/7/21
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //如果bean对象实现了DisposableBean接口，则调用初始化方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        //如果xml中配置了init-method,则调用对应的销毁方法(注意避免重复销毁)
        if (StrUtil.isNotBlank(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            if (method == null) {
                throw new BeansException(beanName + "中找不到销毁方法: " + destroyMethodName);
            }
            method.invoke(bean);
        }
    }
}
