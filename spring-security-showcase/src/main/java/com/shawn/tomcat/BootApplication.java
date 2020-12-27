package com.shawn.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class BootApplication {

    private int port = 8080;
    private String contextPath = "";
    private static Logger logger= LoggerFactory.getLogger(BootApplication.class);

    public void prepare(){
        String osName = System.getProperties().getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            String path = BootApplication.class.getResource("/").getPath() + "/webapps";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    public void start() throws Exception {

        try{
            logger.info("tomcat开始启动");
            // 设置端口
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(port);

            String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            tomcat.setBaseDir(baseDir);
            Context ctx = tomcat.addWebapp("", baseDir);
            ctx.setParentClassLoader(Thread.currentThread().getContextClassLoader());
            //ctx.setLoader(new WebappLoader(Thread.currentThread().getContextClassLoader()));
            ctx.setRequestCharacterEncoding("UTF-8");

            tomcat.start();
            logger.info("tomcat启动成功，端口:{}",port);
            tomcat.getServer().await();

        }catch (Exception e) {
            logger.error("tomcat启动失败，",e);
        }


    }
    public static void main(String[] args) throws Exception {
        BootApplication application = new BootApplication();
        application.start();
    }
}
