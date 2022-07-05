package cn.ahcoder.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 资源接口
 * @author：AhHao
 * @date: 2022/7/4
 */
public interface Resource {

    /**
     * 获取资源的输入流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
