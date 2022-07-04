package cn.ahcoder.spring.test.bean;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class UserService {

    private String testProperty;

    private UserDao userDao;

    public String queryUserInfo(String uid) {
        return "查询id为" + uid + "的用户: " + userDao.queryUserName(uid) + " testProperty: " + testProperty;
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
}
