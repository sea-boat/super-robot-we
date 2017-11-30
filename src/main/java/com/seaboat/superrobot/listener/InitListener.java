package com.seaboat.superrobot.listener;

import org.apache.log4j.Logger;

import com.seaboat.superrobot.util.Constants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>a listener acts as initializer, do something about initialization when web context is being loaded.</p>
 */
public class InitListener implements ServletContextListener {

    private static Logger LOGGER = Logger.getLogger(InitListener.class);

    public void contextInitialized(ServletContextEvent sce) {

        Properties properties = new Properties();
        try {
            properties.load(InitListener.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            LOGGER.error("error occurs when loading config.properties");
        }

        Constants.APP_ID = properties.getProperty("appId");
        Constants.APP_SECRET = properties.getProperty("appSecret");
        Constants.TOKEN = properties.getProperty("token");

    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
