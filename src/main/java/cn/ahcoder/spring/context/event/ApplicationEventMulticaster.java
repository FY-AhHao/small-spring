package cn.ahcoder.spring.context.event;

import cn.ahcoder.spring.context.ApplicationEvent;
import cn.ahcoder.spring.context.ApplicationListener;

/**
 * @description: 应用事件广播器
 * @author：AhHao
 * @date: 2022/7/22
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听器
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除事件监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
