package cn.ahcoder.spring.context.support;

import cn.ahcoder.spring.core.io.ClassPathResource;
import cn.ahcoder.spring.core.io.Resource;

/**
 * @description: 类路径xml解析应用上下文
 * @author：AhHao
 * @date: 2022/7/5
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private Resource[] configResources;

    public ClassPathXmlApplicationContext(){}

    public ClassPathXmlApplicationContext(String configResource) {
        this(new String[]{configResource});
    }

    public ClassPathXmlApplicationContext(String[] configResources) {
        this.configResources = new Resource[configResources.length];
        for (int i = 0; i < configResources.length; i++) {
            if (configResources[i].startsWith(CLASSPATH_URL_PREFIX)){
                this.configResources[i] = new ClassPathResource(configResources[i].substring(CLASSPATH_URL_PREFIX.length()));
            }else {
                this.configResources[i] = new ClassPathResource(configResources[i]);
            }
        }
        // 调用AbstractApplicationContext中的refresh()刷新容器
        refresh();
    }

    @Override
    protected Resource[] getConfigResources() {
        return this.configResources;
    }
}
