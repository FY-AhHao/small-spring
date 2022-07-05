package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.ahcoder.spring.beans.factory.xml.XmlBeanDefinitionReader;
import cn.ahcoder.spring.core.io.Resource;

/**
 * @description: 抽象的解析xml的应用上下文
 * @author：AhHao
 * @date: 2022/7/5
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        //1.创建xml bean定义读取器
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);

        //2.获取xml路径
        Resource[] configResources = getConfigResources();

        //3.加载xml配置文件，注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinitions(configResources);
    }

    /**
     * 获取配置路径
     * @return
     */
    protected abstract Resource[] getConfigResources();
}
