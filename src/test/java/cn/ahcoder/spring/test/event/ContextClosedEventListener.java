package cn.ahcoder.spring.test.event;

import cn.ahcoder.spring.context.ApplicationListener;
import cn.ahcoder.spring.context.event.ContextClosedEvent;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/25
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭");
    }
}
