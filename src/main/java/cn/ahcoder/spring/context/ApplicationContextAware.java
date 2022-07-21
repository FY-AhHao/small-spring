package cn.ahcoder.spring.context;

import cn.ahcoder.spring.beans.factory.Aware;

/**
 * @description: applicationContext感知标记接口
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);

}
