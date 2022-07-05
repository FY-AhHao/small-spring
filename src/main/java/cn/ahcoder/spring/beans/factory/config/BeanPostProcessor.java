package cn.ahcoder.spring.beans.factory.config;

/**
 * @description: bean后置处理器，提供修bean对象的扩展点
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface BeanPostProcessor {

    /**
     * bean对象初始化之前执行该方法
     * @param beanName
     * @param bean
     * @return
     */
    Object postProcessBeforeInitialization(String beanName,Object bean);


    /**
     * bean对象初始化之后执行该方法
     * @param beanName
     * @param bean
     * @return
     */
    Object postProcessAfterInitialization(String beanName,Object bean);
}
