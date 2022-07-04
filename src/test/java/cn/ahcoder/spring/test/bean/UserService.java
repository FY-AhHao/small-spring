package cn.ahcoder.spring.test.bean;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public String queryUserInfo(String uid){
        return "查询id为" + uid + "的用户: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
