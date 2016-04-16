package com.pabula.fw.tag;

import com.pabula.fw.utility.VO;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Collection;
import java.util.Iterator;

/**
 * �б�TAG��������ʾһ�������б�
 */
public class ListTag extends BodyTagSupport{
	Logger log = Logger.getLogger(ListTag.class);

	/**������Զ�������*/
	//�б�ļ��ϣ�ÿһ��item����һ��VO
	Collection coll = null;

	//�б������ҳ���ı�������
	String name = "coll";

	String vo = "";
	/**����Ϊ�����*/

	private Iterator it;

	private VO voObject = null;

	public ServletRequest request = null;

	public ListTag(){
	}

	public int doStartTag() throws JspException {
		request = this.pageContext.getRequest();

		it = coll.iterator();

		if (it.hasNext()) {
			voObject = (VO) it.next();
			pageContext.setAttribute(this.getName(), voObject);
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		if (it.hasNext()) {
			voObject = (VO) it.next();
			pageContext.setAttribute(this.getName(), voObject);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	public Collection getColl() {
		return coll;
	}

	public void setColl(Collection coll) {
		this.coll = coll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVo() {
		return vo;
	}

	public void setVo(String vo) {
		this.vo = vo;
	}
}