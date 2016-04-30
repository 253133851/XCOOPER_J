package com.xcooper.comment.busi;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.xcooper.comment.dao.TopicDAO;
import com.xcooper.comment.vo.TopicVO;
import com.pabula.fw.exception.DataAccessException;

/**
 * 业务审核流程Bean
 * @author zdk
 * 2016-04-28 11:33:28
 */
public class TopicBean {

	Logger log = Logger.getLogger(TopicBean.class);
	TopicDAO dao;

	public TopicBean(){
		dao = new TopicDAO();
	}

	/**
	 * 添加业务审核流程
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public void addTopic(TopicVO VO)throws DataAccessException{
		dao.addTopic(VO);
	}

	/**
	 * 修改业务审核流程
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public void modifyTopic(TopicVO VO)throws DataAccessException{
		dao.modifyTopic(VO);
	}

	/**
	 * 删除业务审核流程
	 * @param TOPPIC_ID
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public void delTopic(int TOPPIC_ID)throws DataAccessException{
		dao.delTopic(TOPPIC_ID);
	}

	/**
	 * 批量删除业务审核流程
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public void delTopic(Collection coll)throws DataAccessException{
		dao.delTopic(coll);
	}

	/**
	 * 根据SQL获取业务审核流程集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public Collection getTopicColl(String sql)throws DataAccessException{
		return dao.getTopicColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param TOPPIC_ID
	 * @throws DataAccessException
	 * @author zdk 2016-04-28 11:33:28
	 */
	public TopicVO getTopicByID(int TOPPIC_ID)throws DataAccessException{
		return dao.getTopicByID(TOPPIC_ID);
	}
}