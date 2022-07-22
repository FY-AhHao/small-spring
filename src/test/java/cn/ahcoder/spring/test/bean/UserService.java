package cn.ahcoder.spring.test.bean;

import cn.ahcoder.spring.beans.factory.*;
import cn.ahcoder.spring.context.ApplicationContext;
import cn.ahcoder.spring.context.ApplicationContextAware;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

    private String testProperty;

    private String testBeanFactoryPostProcessorProperty;

    private String testBeanPostProcessorProperty;

    private UserDao userDao;

    private IUserDao userDaoProxy;

    public String queryUserInfo(String uid) {
        return "查询id为" + uid + "的用户: " + userDao.queryUserName(uid)
                + "\n testProperty: " + testProperty
                + "\n testBeanFactoryPostProcessorProperty: " + testBeanFactoryPostProcessorProperty
                + "\n testBeanPostProcessorProperty: " + testBeanPostProcessorProperty;
    }

    public String queryUserInfo2(String uid) {
        return "(factoryBean)查询id为" + uid + "的用户: " + userDaoProxy.queryUserName(uid)
                + "\n testProperty: " + testProperty
                + "\n testBeanFactoryPostProcessorProperty: " + testBeanFactoryPostProcessorProperty
                + "\n testBeanPostProcessorProperty: " + testBeanPostProcessorProperty;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }

    public String getTestBeanFactoryPostProcessorProperty() {
        return testBeanFactoryPostProcessorProperty;
    }

    public void setTestBeanFactoryPostProcessorProperty(String testBeanFactoryPostProcessorProperty) {
        this.testBeanFactoryPostProcessorProperty = testBeanFactoryPostProcessorProperty;
    }

    public String getTestBeanPostProcessorProperty() {
        return testBeanPostProcessorProperty;
    }

    public void setTestBeanPostProcessorProperty(String testBeanPostProcessorProperty) {
        this.testBeanPostProcessorProperty = testBeanPostProcessorProperty;
    }


    public IUserDao getUserDaoProxy() {
        return userDaoProxy;
    }

    public void setUserDaoProxy(IUserDao userDaoProxy) {
        this.userDaoProxy = userDaoProxy;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("userService执行destroy方法");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("userService执行afterPropertiesSet方法");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("userService感知beanClassLoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("userService感知beanFactory:" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("userService感知beanName: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("userService感知application: " + applicationContext);
    }
}
