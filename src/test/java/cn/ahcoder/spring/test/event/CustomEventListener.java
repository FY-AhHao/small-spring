package cn.ahcoder.spring.test.event;

import cn.ahcoder.spring.context.ApplicationListener;

import java.util.Date;

/**
 * @description: 自定义事件监听器
 * @author：AhHao
 * @date: 2022/7/25
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到" + event.getSource() + "消息,时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
