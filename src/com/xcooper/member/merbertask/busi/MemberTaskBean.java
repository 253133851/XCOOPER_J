package com.xcooper.member.merbertask.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.member.merbertask.dao.MemberTaskDAO;
import com.xcooper.member.merbertask.vo.MemberTaskVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 成员任务Bean
 * @author zdk
 * 2016-03-28 19:36:13
 */
public class MemberTaskBean {

	Logger log = Logger.getLogger(MemberTaskBean.class);
	MemberTaskDAO dao;

	public MemberTaskBean(){
		dao = new MemberTaskDAO();
	}

	/**
	 * 添加成员任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void addMemberTask(MemberTaskVO VO)throws DataAccessException{
		dao.addMemberTask(VO);
	}

	/**
	 * 修改成员任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void modifyMemberTask(MemberTaskVO VO)throws DataAccessException{
		dao.modifyMemberTask(VO);
	}

	/**
	 * 删除成员任务
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void delMemberTask(int ID)throws DataAccessException{
		dao.delMemberTask(ID);
	}

	/**
	 * 批量删除成员任务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void delMemberTask(Collection coll)throws DataAccessException{
		dao.delMemberTask(coll);
	}

	/**
	 * 根据SQL获取成员任务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public Collection getMemberTaskColl(String sql)throws DataAccessException{
		return dao.getMemberTaskColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public MemberTaskVO getMemberTaskByID(int ID)throws DataAccessException{
		return dao.getMemberTaskByID(ID);
	}
}