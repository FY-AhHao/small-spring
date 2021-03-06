package cn.ahcoder.spring.beans.factory;

/**
 * @description: bean工厂接口
 * @author：AhHao
 * @date: 2022/6/25
 */
public interface BeanFactory {

    /**
     * 通过beanName获取bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

    /**
     * 带参获取bean
     * @param beanName
     * @param args
     * @return
     */
    Object getBean(String beanName,Object... args);

    /**
     * 根据名称和类型获取bean
     * @param beanName
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T getBean(String beanName,Class<T> clazz);
}
