package cn.ahcoder.spring.beans.factory;

/**
 * @description: 工厂bean接口，可以提供给外部其他框架一个定义复杂bean对象的接入点
 * 像mybatis中就有实现此接口创建sqlSession来对数据库进行CRUD
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface FactoryBean<T> {

    /**
     * 获取bean对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取bean对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 生成的bean对象是否单例对象
     * @return
     */
    boolean isSingleton();

}
