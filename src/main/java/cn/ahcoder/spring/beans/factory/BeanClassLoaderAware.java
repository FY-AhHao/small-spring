package cn.ahcoder.spring.beans.factory;

/**
 * @description: beanClassLoader感知标记接口
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
