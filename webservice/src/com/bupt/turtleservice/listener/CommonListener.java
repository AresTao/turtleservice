package com.bupt.turtleservice.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CommonListener implements ServletContextListener
{
    private static Logger logger = Logger.getLogger(CommonListener.class);

    private final static String ROOT_CONFIG_PATH = "rootConfigPath";
    private final static String LOG4J_FILE_NAME = "log4j.properties";

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        String webPath = arg0.getServletContext().getRealPath("/");
        String rootConfPath = arg0.getServletContext().getInitParameter(ROOT_CONFIG_PATH);
        PropertyConfigurator.configure(webPath + File.separator + rootConfPath + File.separator + LOG4J_FILE_NAME);
               
        
        logger.info("service start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
    }
}

