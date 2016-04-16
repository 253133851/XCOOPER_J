package com.pabula.fw.utility;

import com.pabula.fw.exception.RuleException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;


public interface RequestHelper {

  public HttpServletRequest getRequest();

  public HttpServletResponse getResponse();

  public Command getCommand() throws RuleException;

  public Properties getProperties();

  public ServletContext getApplication();
  
  public VO getVO();
  
 // public void requestValueToVO();

}
