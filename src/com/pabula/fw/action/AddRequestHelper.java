package com.pabula.fw.action;

import com.xcooper.ENV;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.utility.AbstractRequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * add������
 * 
 * @author pabula 2015-6-27����11:21:05
 */
public class AddRequestHelper extends AbstractRequestHelper {

	public AddRequestHelper(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		super(request, response, application);

		// ��request�е�ֵ������VO��
		VO vo = this.getVOClass(request);
		super.requestValueToVO(request, vo);

		super.setVO(vo);
	}

	public ServletContext getApplication() {
		return null;
	}
	
	

	/**
	 * ���action���󣬶�̬����ҵ���VO��
	 * @param request
	 * @return
	 * @author pabula 2015-6-27 ����11:35:16
	 */
	public VO getVOClass(HttpServletRequest request) {
		VO vo = null;

		String voClassMainName = "";
		
		//VO��������ƾ���actionֵȥ��ǰ���add�ַ�����action=addMember����ȥ��add������member

		String subPackageName = (String)(StrUtil.split(request.getParameter("action"),'!').get(0));
		String action = (String)(StrUtil.split(request.getParameter("action"),'!').get(1));

		System.out.println("packageName:"+subPackageName);
		voClassMainName = action.substring("Add".length());

		//ƴ�ӳ� ҵ��vo ����·�� ������ com.pabula.hh.Member.vo.MemberVO
		String className = ENV.VO_PACKAGE_NAME + "." + subPackageName + ".vo." + voClassMainName + "VO";

		System.err.println("sunsaiVOClass: " + className);

		System.err.println("VOClass: " + className);

		try {
			//��̬����vo��
			vo = (VO) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return vo;
	}

	
}
