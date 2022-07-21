package cn.ahcoder.spring.test.bean;

import cn.ahcoder.spring.beans.factory.DisposableBean;
import cn.ahcoder.spring.beans.factory.InitializingBean;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class UserService implements InitializingBean, DisposableBean {

    private String testProperty;

    private String testBeanFactoryPostProcessorProperty;

    private String testBeanPostProcessorProperty;

    private UserDao userDao;

    public String queryUserInfo(String uid) {
        return "查询id为" + uid + "的用户: " + userDao.queryUserName(uid)
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

    @Override
    public void destroy() throws Exception {
        System.out.println("userService执行destroy方法");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("userService执行afterPropertiesSet方法");
    }
}
