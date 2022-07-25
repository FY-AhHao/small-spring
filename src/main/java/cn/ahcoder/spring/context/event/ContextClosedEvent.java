package cn.ahcoder.spring.context.event;

/**
 * @description: 上下文关闭事件
 * @author：AhHao
 * @date: 2022/7/22
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
