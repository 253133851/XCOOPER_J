package com.pabula.fw.action;

import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.AbstractRequestHelper;
import com.pabula.fw.utility.Command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ�õ� ajax ��ʽ�� ������
 * @author pabula 2015-6-29 20:28
 */
public class CommonAjaxRequestHelper extends AbstractRequestHelper {

	public CommonAjaxRequestHelper(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		super(request, response, application);
	}
	
	/**
	 * �ع�getCommand��������ݵ�validate����
	 */
	public Command getCommand() throws RuleException {
		Command command = super.getCommand();
		
		//ִ��ʵ���ҵ��Command�е�validate ���� ������ݼ�⣬�������ݲ��Ϸ��ģ����׳�RuleException����CMSController����
		ValidateUtil validate = new ValidateUtil();
		command.validate(getRequest(),getVO(),validate);
		
		if (validate.hasError()) {
			//如果验证有错误
			RuleException e = new RuleException();
			e.setErrColl(validate.getErrors());
			throw e;
		}
		
		return command;
	}
	

	public ServletContext getApplication() {
		return null;
	}
	

}
