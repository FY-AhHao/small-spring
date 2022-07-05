package cn.ahcoder.spring.core.io;

/**
 * @description: 资源加载器
 * @author：AhHao
 * @date: 2022/7/4
 */
public interface ResourceLoader {

    /**
     * 类路径前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}
