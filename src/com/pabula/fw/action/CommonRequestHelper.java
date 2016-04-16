package com.pabula.fw.action;

import com.pabula.fw.utility.AbstractRequestHelper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommonRequestHelper extends AbstractRequestHelper{

//  private static CommandFactory commandFactory = new WebCommandFactoryImpl();

  public CommonRequestHelper(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
    super(request,response,application);
  }

//  public Command getCommand(){
//    Command cmd = null;
//    try {
//      //cmd = commandFactory.createCommand(this.getProperties().getProperty("action"));
//    	cmd = commandFactory.createCommand(this.getRequest().getParameter("action"));
//    }
//    catch (UnAcquiredCommandException ex) {
//      System.err.println("¥¥Ω®command ß∞‹");
//      ex.printStackTrace();
//    }
//    return cmd;
//  }
  
  

  public ServletContext getApplication(){
    return null;
  }


}
