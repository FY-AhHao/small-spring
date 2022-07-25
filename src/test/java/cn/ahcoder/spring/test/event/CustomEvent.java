package cn.ahcoder.spring.test.event;

import cn.ahcoder.spring.context.event.ApplicationContextEvent;

/**
 * @description: 自定义事件
 * @author：AhHao
 * @date: 2022/7/25
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomEvent(Object source,Long id,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
