package com.pabula.fw.tag;

import com.pabula.fw.utility.VO;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Collection;
import java.util.Iterator;

/**
 * 列表TAG，负现显示一个集合列表
 */
public class ListTag extends BodyTagSupport{
	Logger log = Logger.getLogger(ListTag.class);

	/**请把属性定义在这*/
	//列表的集合，每一个item就是一个VO
	Collection coll = null;

	//列表放入至页面后的变量名称
	String name = "coll";

	String vo = "";
	/**以下为类变量*/

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