package cn.ahcoder.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @description: 默认资源加载器
 * @author：AhHao
 * @date: 2022/7/4
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "资源路径不能为空");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }

        try {
            return new UrlResource(new URL(location));
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }
}
