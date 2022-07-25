package cn.ahcoder.spring.context.event;

/**
 * @description: 上下文刷新事件
 * @author：AhHao
 * @date: 2022/7/22
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
