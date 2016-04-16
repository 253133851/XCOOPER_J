package com.pabula.fw.utility;

import com.pabula.common.util.StrUtil;
import com.pabula.fw.WebCommandFactoryImpl;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.UnAcquiredCommandException;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

abstract public class AbstractRequestHelper
    implements RequestHelper {

  private HttpServletRequest request = null;
  private HttpServletResponse response = null;
  private ServletContext application = null;
  private Properties properties = null;
  private VO vo = null;

  public AbstractRequestHelper(
    HttpServletRequest request,
    HttpServletResponse response,
    ServletContext application) {
    this.request = request;
    this.response = response;
    this.application = application;
    //this.properties = this.getProperties(request);
  }

  public HttpServletRequest getRequest(){
    return this.request;
  }

  public HttpServletResponse getResponse(){
    return this.response;
  }

  public ServletContext getServletContext(){
    return this.application;
  }

  public Properties getProperties(){
    return this.properties;
  }
  
  public VO getVO(){
	  return this.vo;
  }
  
  public void setVO(VO vo){
	  this.vo = vo;
  }

  //public abstract Command getCommand();
  public Command getCommand() throws RuleException {
		Command cmd = null;
		CommandFactory commandFactory = new WebCommandFactoryImpl();
		try {
			cmd = commandFactory.createCommand(this.getRequest().getParameter("action"));
		} catch (UnAcquiredCommandException ex) {
			System.err.println("����commandʧ��");
			ex.printStackTrace();
		}
		return cmd;
  }

  
  /**
	 * ����BeanUtil��Դ��ܣ���request�е�ֵ����̬��������VO������
	 * @param request
	 * @param VO
	 * @author pabula 2015-6-27 ����10:32:09
	 */
	public void requestValueToVO(HttpServletRequest request, Object VO) {
		if(null == VO){
			return;
		}

		/***********************************************************************
		 * ����BeanUtil��Դ��ܣ���request�е�ֵ����̬��������VO������
		 **********************************************************************/

		HashMap map = new HashMap();

		// ��request�õ����в���
		Enumeration names = request.getParameterNames();

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			//System.err.println(name);	//����?�������
			
			String[] value = request.getParameterValues(name); // �õ�name������ֵ
			String overValue = "";

			if (value.length > 0) {
				int i = 0;

				// ����Ӧname�е�ֵתΪ�ַ�����ж��ֵ�����ö���ƴ�ӳ��ַ�
				while (i < value.length) {
					if (StrUtil.isNotNull(value[i])) {
						if (overValue.equals("")) {
							overValue = value[i];
						} else {
							overValue = overValue + "," + value[i];
						}
					}
					i++;
				}

				if (StrUtil.isNotNull(overValue)) {
					map.put(name, overValue);
				}
			}
		}

		try {
			BeanUtils.populate(VO, map);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
//  private Properties getProperties(HttpServletRequest request){
//    Properties properties = new Properties();
//    for (Enumeration enumeration = request.getParameterNames();
//         enumeration.hasMoreElements();) {
//      Object obj = enumeration.nextElement();
//      String s = request.getParameterValues((String)obj)[0];
//      System.out.println("Parameter name =" + obj.toString() + ", Parameter value =" + s);
//      if (!isAny(s)) {
//        try {
//          properties.put(obj,new BigDecimal(s));
//        }
//        catch (NumberFormatException ex) {
//          properties.put(obj,s);
//        }
//      }
//    }
//    return properties;
//  }

  
}
