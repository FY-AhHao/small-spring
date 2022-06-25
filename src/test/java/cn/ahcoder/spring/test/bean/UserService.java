package cn.ahcoder.spring.test.bean;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class UserService {

    public String queryUserInfo(String uid){
        return "查询id为" + uid + "的用户";
    }

}
