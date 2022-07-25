package cn.ahcoder.spring.context;

import java.util.EventListener;

/**
 * @description: 应用事件监听器
 * @author：AhHao
 * @date: 2022/7/22
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    /**
     * 监听处理事件
     * @param event
     */
    void onApplicationEvent(T event);
}
