package cn.ahcoder.spring.test.event;

import cn.ahcoder.spring.context.ApplicationListener;
import cn.ahcoder.spring.context.event.ContextRefreshedEvent;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/25
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新");
    }
}
