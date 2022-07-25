package cn.ahcoder.spring.context.event;

import cn.ahcoder.spring.context.ApplicationContext;
import cn.ahcoder.spring.context.ApplicationEvent;

/**
 * @description: 应用上下文事件
 * @author：AhHao
 * @date: 2022/7/22
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
