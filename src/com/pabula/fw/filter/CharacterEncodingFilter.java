/*
 * Created on 2005-4-5
 */
package com.pabula.fw.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dekn
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding = null;
	protected FilterConfig filterConfig = null; 
	
	public void init(FilterConfig filterConfig) throws ServletException { 
        //System.err.println("=========== CharactherEncodingFilter loaded ==========");
		this.filterConfig = filterConfig; 
	} 
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	    //System.err.println("============ CharachterEncodingFilter ==============");
        
		encoding = getUserEncoding(); // ��ǰ�û��ı��뷽ʽ�����û����õ�һ��ֵ
		if (encoding == null) {
			encoding = request.getCharacterEncoding(); // ��������صı��뷽ʽ
		}
		if (encoding == null) {
			encoding = filterConfig.getInitParameter("encoding"); // Ӧ�ó���ı��뷽ʽ
		}
		if (encoding != null) {
            //System.err.println("--------------------------------");
			request.setCharacterEncoding(encoding); // ����servlet
													// 2.3�¼ӵķ�����ר��������request���뷽ʽ
			//response.setContentType("\"text/html;charset=gb2312\""); // ����response���뷽ʽ
		}

        
        String urlSearchStr = ((HttpServletRequest)request).getQueryString();
        
        if(null != urlSearchStr && !urlSearchStr.trim().equals("")){
            if(!URLCheck(urlSearchStr)){
                System.err.println("URL�а����Ƿ��ַ�������ʧ��  " + urlSearchStr);
                response.setCharacterEncoding(getUserEncoding());
                response.getOutputStream().println("URL�а����Ƿ��ַ�������ʧ��\r\n");
                response.getOutputStream().println("Copyright 2004 - 2005 www.CMS4J.com��All Rights Reserved");
                return;
            }
        }
        
		

        
		chain.doFilter(request, response);

	} 
    
    /**
     * ���URL�еķǷ��ַ�
     * @param checkStr
     * @return
     */
    public boolean URLCheck(String checkStr){
        boolean isOK = true;
        
        if(checkStr.indexOf("'") > -1 ||
                checkStr.indexOf("\"") > -1 ||
                checkStr.indexOf("$") > -1 ||
                checkStr.indexOf(">") > -1 ||
                checkStr.indexOf("<") > -1 ||
                checkStr.indexOf("(") > -1 ||
                checkStr.indexOf(")") > -1 ||
                checkStr.indexOf("+") > -1){
            isOK = false;
        }
        
        return isOK;
    }
	
	
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	} 

	private String getUserEncoding() {
		return "GBK";
	} 


}
