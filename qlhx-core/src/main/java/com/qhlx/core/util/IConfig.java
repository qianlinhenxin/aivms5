package com.qhlx.core.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class IConfig {

    /**
     * 同步锁
     */
    private static final Object obj = new Object();

    /**
     * 配置文件
     */
    private static Properties prop = null;

    /**
     * 配置对象单例模式
     */
    private static IConfig config = null;

    /**
     * 配置文件名称
     */
    private final static String FILE_NAME = "/application-config.properties";

    /**
     * classes目录路径
     */
    public static String classPath = null;

    static {
        //classPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        prop = new Properties();
        try {
            prop.load(IConfig.class.getResourceAsStream(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取单例模式对象实例
     *
     * @return 唯一对象实例
     */
    public static IConfig getInstance() {
        if (null == config) {
            synchronized (obj) {
                config = new IConfig();
            }
        }
        return config;
    }

    /**
     * 获取指定key的value值
     */
    public static String get(String key) {
        return prop.getProperty(key);
    }

    /**
     *
     * <p>
     * Title:修改指定key的value值
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(classPath + FILE_NAME);
            prop.setProperty(key, value);
            prop.store(oFile, "update");
            oFile.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
