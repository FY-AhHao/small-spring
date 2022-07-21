package cn.ahcoder.spring.beans.factory;

/**
 * @description: 销毁bean接口
 * @author：AhHao
 * @date: 2022/7/21
 */
public interface DisposableBean {

    /**
     * bean对象销毁时执行
     * @throws Exception
     */
    void destroy() throws Exception;
}
