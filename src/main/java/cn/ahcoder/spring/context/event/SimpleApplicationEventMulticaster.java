package cn.ahcoder.spring.context.event;

import cn.ahcoder.spring.context.ApplicationEvent;
import cn.ahcoder.spring.context.ApplicationListener;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/25
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }
}
