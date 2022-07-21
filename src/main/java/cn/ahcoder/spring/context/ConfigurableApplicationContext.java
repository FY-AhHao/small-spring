package cn.ahcoder.spring.context;

/**
 * @description: 可配置的应用上下文
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh();

    /**
     * 注册虚拟机关闭钩子
     */
    void registerShutdownHook();

    /**
     * 关闭方法
     */
    void close();
}
