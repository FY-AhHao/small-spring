package cn.ahcoder.spring.context;

/**
 * @description: 应用事件发布者
 * @author：AhHao
 * @date: 2022/7/25
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
