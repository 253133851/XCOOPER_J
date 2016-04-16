/*
 * Created on 2005-8-31
 */
package com.pabula.fw.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Dekn
 * CMS4j程序环境类
 */
public class CMS4JApplication {
    
    private static CMS4JApplication application = null;
    
    private static HashMap app = new HashMap();
    
    private static Properties appProperties = new Properties();

    
    private CMS4JApplication(){
        if(app.isEmpty()){
            Properties properties = new Properties();
            String fileName = FileConfig.getInstance().getAppConfigFileName();
            java.io.FileInputStream fis;
            try {
                fis = new java.io.FileInputStream(fileName);
                try {
                    properties.load(fis);
                    properties.list(System.out);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                appProperties = properties;
                String name = null;
                for (Enumeration e = properties.keys(); e.hasMoreElements();) {
                    name = (String) e.nextElement();
                    app.put(name, properties.getProperty(name));
                }
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public Properties getProperties(){
        return appProperties;
    }
    
    public static CMS4JApplication getInstance(){
        if(null == application){
            application = new CMS4JApplication();
        }
        
        return application;
    }
    
    public HashMap getAppMap(){
        return app;
    }
    
    public static CMS4JApplication resetAndGetInstance(){
    	reset();
    	return getInstance();
    }
    
    public static void reset(){
    	app = new HashMap();
    	application = null;
    }

}
