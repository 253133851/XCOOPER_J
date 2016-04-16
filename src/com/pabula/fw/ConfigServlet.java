package com.pabula.fw;

import com.pabula.fw.utility.FileConfig;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigServlet extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=utf8";

  /**
   * 初始化日志配置
   */
  public void init(ServletConfig arg0) throws ServletException {
    super.init(arg0);
    System.err.println("pabula dev fw : 2005-06-25  NJ");
    
//    initActionMapConfig(arg0);
    initLogConfig(arg0);
    initApplicationConfig(arg0);
    
    //以下代码负责加载系统的配置项
    
    //设置网站存放的物理路径地址
    String realPath = arg0.getServletContext().getRealPath("/");
    //SysConfigVO vo = SysConfigVO.getInstance();
    //vo.setSITE_REAL_PATH(realPath);
    
  }

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    
  }

  //Clean up resources
  public void destroy() {
  }
  
  public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
  	System.err.println("********************************************************");
  	super.service(req, res);
  	this.initSiteContextConfig(req);
  }
		

  // log4j配置
    private void initLogConfig(ServletConfig config) {
        System.out.println("................log4j start");
        String log4jConfig = config.getInitParameter("log4jFile");
        if (log4jConfig == null || log4jConfig.equals("")) {
            log4jConfig = "/WEB-INF/config/log4j.properties";
        }

        try {
            log4jConfig = this.getServletContext().getRealPath(log4jConfig);
            System.err.println("------------------- "
                    + this.getServletContext().getRealPath("/"));

            // 设置log保存的根目录
            FileConfig.getInstance().setLog4jFileSavePath(
                    this.getServletContext().getRealPath("/"));

            FileConfig.getInstance().setLog4jFileName(log4jConfig);
        } catch (Exception ex) {
            log4jConfig = null;
        }

        if (log4jConfig == null) {
            System.err.println("log4j Config error!");
        } else {
            PropertyConfigurator.configure(log4jConfig);
        }

    }

//  /**
//   * 初始化Action的映射配置
//   * @param config ServletConfig
//   */
//  private void initActionMapConfig(ServletConfig config) {
//    String actMapConfig = null;
//    //Action配置
//    actMapConfig = config.getInitParameter("ActionMapping");
//    if (actMapConfig == null || actMapConfig.equals("")) {
//      actMapConfig = "/WEB-INF/config/ActionMapping.properties";
//    }
//    try {
//      actMapConfig = this.getServletContext().getRealPath(actMapConfig);
//    }
//    catch (Exception ex) {
//      actMapConfig = null;
//    }
//    if (actMapConfig == null) {
//      System.err.println("Action Maping Config error!");
//    }
//    FileConfig.getInstance().setActionMapFileName(actMapConfig);
//
//  }

  /**
   * 初始化应用程序配置
   * @param config ServletConfig
   */
  private void initApplicationConfig(ServletConfig config) {
    //应用配置
    String appConfig = config.getInitParameter("appConfigFile");
    if (appConfig == null || appConfig.equals("")) {
    	appConfig = "/WEB-INF/config/Application.properties";
    }
    try {
    	appConfig = this.getServletContext().getRealPath(appConfig);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      appConfig = null;
    }
    if (appConfig == null) {
      System.err.println("Application Config error!");
    }
    //
    FileConfig.getInstance().setAppConfigFileName(appConfig);
  }
  	
  private void initSiteContextConfig(ServletRequest req){
      
      String siteContextConfig = ((HttpServletRequest)req).getContextPath();
      System.err.println("siteContextConfig :::::::" + siteContextConfig);
  }
  

}
