package cn.ahcoder.spring.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/4
 */
public class UserDao {

    private static Map<String,String> userCache = new HashMap<>();

    static {
        userCache.put("111","fy");
        userCache.put("222","fy2");
        userCache.put("333","fy3");
    }

    public String queryUserName(String uid){
        return userCache.get(uid);
    }

    public void initMethod(){
        System.out.println("UserDao执行init-method方法");
    }

    public void destroyMethod(){
        System.out.println("UserDao执行destroy-method方法");
    }
}
