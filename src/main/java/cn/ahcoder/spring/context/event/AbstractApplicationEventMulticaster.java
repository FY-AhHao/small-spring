package cn.ahcoder.spring.context.event;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.context.ApplicationEvent;
import cn.ahcoder.spring.context.ApplicationListener;
import cn.ahcoder.spring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description: 抽象的应用事件广播器
 * @author：AhHao
 * @date: 2022/7/22
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    /**
     * 应用事件监听器缓存
     */
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    /**
     * 根据事件获取事件监听器
     *
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> listeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                listeners.add(applicationListener);
            }
        }
        return listeners;
    }

    /**
     * 判断监听器是否对事件感兴趣
     *
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String typeName = actualTypeArgument.getTypeName();
        Class<?> eventClass = null;
        try {
            eventClass = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new BeansException("找不到" + typeName + "事件类", e);
        }
        return eventClass.isAssignableFrom(event.getClass());
    }

}
