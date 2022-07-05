package cn.ahcoder.spring.beans.factory.xml;

import cn.ahcoder.spring.beans.BeansException;
import cn.ahcoder.spring.beans.PropertyValue;
import cn.ahcoder.spring.beans.factory.config.BeanDefinition;
import cn.ahcoder.spring.beans.factory.config.BeanReference;
import cn.ahcoder.spring.beans.factory.support.AbstractBeanDefinitionReader;
import cn.ahcoder.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.ahcoder.spring.core.io.Resource;
import cn.ahcoder.spring.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: xml bean定义读取器
 * @author：AhHao
 * @date: 2022/7/4
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | DocumentException | ClassNotFoundException e) {
            throw new BeansException("解析xml时发生错误",e);
        }
    }


    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... configLocations) {
        for (String configLocation : configLocations) {
            loadBeanDefinitions(configLocation);
        }
    }

    /**
     * 解析xml，注册beanDefinition
     * @param inputStream
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        Element root = document.getRootElement();

        List<Element> beanEleList = root.elements("bean");
        for (Element beanEle : beanEleList) {
            String id = beanEle.attributeValue("id");
            String name = beanEle.attributeValue("name");
            String clazzStr = beanEle.attributeValue("class");
            Class<?> clazz = Class.forName(clazzStr);

            String beanName = StrUtil.isBlank(id) ? name : id;
            if (StrUtil.isBlank(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //创建bean定义
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            List<Element> propertyEleList = beanEle.elements("property");
            for (Element propertyEle : propertyEleList) {
                String propertyName = propertyEle.attributeValue("name");
                String propertyVal = propertyEle.attributeValue("value");
                String propertyRef = propertyEle.attributeValue("ref");
                Object val = StrUtil.isBlank(propertyRef) ? propertyVal : new BeanReference(propertyRef);

                //创建bean属性键值对
                PropertyValue propertyValue = new PropertyValue(propertyName, val);

                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            //不能出现重复的bean定义
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("已存在同名的beanDefinition: " + beanName);
            }

            //注册beanDefinition
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }


}
