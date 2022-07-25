package cn.ahcoder.spring.context;

import java.util.EventObject;

/**
 * @description: 应用事件
 * @author：AhHao
 * @date: 2022/7/22
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
