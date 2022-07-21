package cn.ahcoder.spring.beans.factory;

/**
 * @description: 初始化bean接口
 * @author：AhHao
 * @date: 2022/7/6
 */
public interface InitializingBean {

    /**
     * 在bean属性注入之后会被调用，做一些初始化操作
     */
    void afterPropertiesSet();
}
