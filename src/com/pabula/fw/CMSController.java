package com.pabula.fw;

import com.pabula.common.util.StrUtil;
import com.pabula.fw.action.*;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CMSController extends HttpServlet {

	Logger log = Logger.getLogger(CMSController.class);

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public CMSController() {
	}


	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		//response.setContentType("application/json");
		//request.setCharacterEncoding("gbk");
		request.setCharacterEncoding("UTF-8");

		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		processRequest(request, response);
	}

	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
	}

	/**
	 * �ҹ������� ����service(ServletRequest, ServletResponse)�������ж��û��Ƿ���Ȩ�޷�����Դ
	 */
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
	}

	/**
	 * Processes a request for both HTTP <code> GET </code> and
	 * <code> POST </code> methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws BusinessRuleException
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//log request here
		String next = null;

		RequestHelper requestHelper;
		
		//ҵ���������
		String busiName = (String)(StrUtil.split(request.getParameter("action"),'!').get(0));
		
		//ҵ��ʵ�����
		String action = (String)(StrUtil.split(request.getParameter("action"),'!').get(1));

		if (action.startsWith("Add")){
			requestHelper = new AddRequestHelper(request, response,this.getServletContext());
		} else if (action.startsWith("modify")) {
			requestHelper = new AddRequestHelper(request, response,this.getServletContext());
		} else if (action.startsWith("AjaxAdd")) {
			requestHelper = new AjaxAddRequestHelper(request, response,this.getServletContext());
		} else if (action.startsWith("CAjax")) {	//ͨ�õ� AJAX ���󣬲������ķ�װ����
			requestHelper = new CommonAjaxRequestHelper(request, response,this.getServletContext());
		}else if(action.startsWith("AjaxUpload")){
			requestHelper = new AjaxUploadFileHelper(request,response,this.getServletContext());
		}else if(action.startsWith("AjaxModify")) {
			requestHelper = new AjaxModifyRequestHelper(request, response,this.getServletContext());
		}else {
			requestHelper = new CommonRequestHelper(request, response,this.getServletContext());
		}


		//requestHelper.getRequest().setCharacterEncoding("UTF-8");
		//����2�仰���Է�ֹ��ҳ�����棨�����������ȣ�
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		
		try {

			Command command = requestHelper.getCommand();

			//command.validate(requestHelper, request);
			//ִ��command���еķ���
			next = command.execute(requestHelper,request);


			//COMMAND ����ֵ�Ĵ���
			if (next != null && action.indexOf("Ajax")<0) {	//��AJAX������ط�һ��URL��������ת
				dispatch(request, response, next);
			}else if(action.indexOf("Ajax")>=0){	//AJAX����ֱ������JSON������ǰ̨

				response.setContentType("application/json");
				response.getWriter().print(next);
				response.getWriter().flush();
				response.getWriter().close();
			}
			
		} catch (Exception e) {
			if (e instanceof RuleException){	//��������쳣
				//System.err.println("��������쳣�� " + StrUtil.strCollToStr(((RuleException)e).getErrColl(),","));
 				//response.getWriter().print("hi2������");

				//如果表单验证出错，则返回错误提示的json数据
				String errorMessage = StrUtil.strCollToStr(((RuleException)e).getErrColl(),",");
				//拼接成json，防止拼接的过程中出�?				String errorJson = "{\"code\":4000,\"msg\":\""+ errorMessage +"\",\"data\":\"error\"}";

				response.setContentType("application/json");
				response.getWriter().print(errorMessage);
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}else if (e instanceof ServletException) {
				throw (ServletException) e;
			}
			
			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getStackTrace().toString());
		}
	}

	/**
	 * returns a description of the servlet
	 */
	public String getServletInfo() {
		return getSignature();
	}

	private void dispatch(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {

		//		RequestDispatcher dispatcher = getServletContext()
		//				.getRequestDispatcher(page);
		
		//RELOAD��������»ص�ԭʼ����ʾURL�У���ˢ����
		if(page.equals("RELOAD")){
			page = "/common/redirect.jsp";
			String returnUrl = request.getHeader("Referer");
			request.setAttribute("RELOAD",returnUrl);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
		//����BASE_HREF���ԣ��Թ�pageʹ�á������������URL�����ʱURL���ҵ�����
		String context = request.getContextPath();	//������ ��JCMS
		String host = request.getServerName();		//������������
		int port = request.getServerPort();			//�˿ں�
		String siteUrl = "http://" + host + ":" + port + context; //�����վ��URL
		
		request.setAttribute("BASE_HREF", siteUrl + page);
		//System.err.println("host: " + host + " Context " + context + " port: " + port);
		//������Եõ�ԭʼ��URL��ʾ·����search
		//System.err.println("Referer +++++++++++++ " + request.getHeader("Referer"));

		dispatcher.forward(request, response);
	}

	private String getSignature() {
		return "ServiceToWorker-Controller  For  CMS";
	}
	
}