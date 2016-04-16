package com.pabula.fw.tag;

import com.pabula.fw.utility.VO;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * VO显示TAG，负责显示一个VO对象
 */
public class VOTag extends BodyTagSupport{
	Logger log = Logger.getLogger(VOTag.class);

	/**请把属性定义在这*/
	//列表的集合，每一个item就是一个VO
	VO vo = null;

	//列表放入至页面后的变量名称
	String name = "coll";

	Collection coll = null;

	/**以下为类变量*/

	private Iterator it;

	public ServletRequest request = null;

	public VOTag(){
	}

	public int doStartTag() throws JspException {
		request = this.pageContext.getRequest();

		Collection coll = new ArrayList();
		if(null != this.getVo()){
			coll.add(this.getVo());
		}

		it = coll.iterator();

		if (it.hasNext()) {
			vo = (VO) it.next();
			pageContext.setAttribute(this.getName(), vo);
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		if (it.hasNext()) {
			vo = (VO) it.next();
			pageContext.setAttribute(this.getName(), vo);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	public VO getVo() {
		return vo;
	}

	public void setVo(VO vo) {
		this.vo = vo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}