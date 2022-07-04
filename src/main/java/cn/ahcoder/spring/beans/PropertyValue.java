package cn.ahcoder.spring.beans;

/**
 * @description: bean的属性键值对
 * @author：AhHao
 * @date: 2022/7/4
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
