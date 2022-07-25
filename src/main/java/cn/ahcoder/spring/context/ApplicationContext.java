package cn.ahcoder.spring.context;

import cn.ahcoder.spring.beans.factory.HierarchicalBeanFactory;
import cn.ahcoder.spring.beans.factory.ListableBeanFactory;

/**
 * @description: 应用上下文顶级接口
 * @author：AhHao
 * @date: 2022/7/5
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,ApplicationEventPublisher {

}
