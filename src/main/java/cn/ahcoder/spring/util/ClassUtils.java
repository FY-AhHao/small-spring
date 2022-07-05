package cn.ahcoder.spring.util;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/7/4
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
        }

        if (classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
        }

        return classLoader;
    }
}
