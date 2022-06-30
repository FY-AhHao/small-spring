package cn.ahcoder.spring.beans;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/25
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
