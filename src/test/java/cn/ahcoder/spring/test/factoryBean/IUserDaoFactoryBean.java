package cn.ahcoder.spring.test.factoryBean;

import cn.ahcoder.spring.beans.factory.FactoryBean;
import cn.ahcoder.spring.test.bean.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/22
 */
public class IUserDaoFactoryBean implements FactoryBean<IUserDao> {


    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Map<String,String> userCache = new HashMap<>();
            userCache.put("111","fy");
            userCache.put("222","fy2");
            userCache.put("333","fy3");
            String arg = args.length == 0 ? "" : args[0].toString();
            return userCache.get(arg) + "(factoryBean代理查询)";
        };

        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class},invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
