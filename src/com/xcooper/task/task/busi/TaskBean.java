package com.xcooper.task.task.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.task.task.dao.TaskDAO;
import com.xcooper.task.task.vo.TaskVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 任务Bean
 * @author zdk
 * 2016-03-28 19:34:51
 */
public class TaskBean {

	Logger log = Logger.getLogger(TaskBean.class);
	TaskDAO dao;

	public TaskBean(){
		dao = new TaskDAO();
	}

	/**
	 * 添加任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void addTask(TaskVO VO)throws DataAccessException{
		VO.setADD_DATETIME(DateUtil.getCurrTime());
		dao.addTask(VO);
	}

	/**
	 * 修改任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void modifyTask(TaskVO VO)throws DataAccessException{
		VO.setUPDATE_DATETIME(DateUtil.getCurrTime());
		dao.modifyTask(VO);
	}

	/**
	 * 删除任务
	 * @param TASK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void delTask(int TASK_ID)throws DataAccessException{
		dao.delTask(TASK_ID);
	}

	/**
	 * 批量删除任务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void delTask(Collection coll)throws DataAccessException{
		dao.delTask(coll);
	}

	/**
	 * 根据SQL获取任务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public Collection getTaskColl(String sql)throws DataAccessException{
		return dao.getTaskColl(sql,false);
	}

	/**
	 * 根据SQL删除
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public Collection deleteT(String sql)throws DataAccessException{
		return dao.getTaskColl(sql,false);
	}

	/**
	 * 根据SQL获取任务集合,关联了member_task表之后
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-04-17
	 */
	public Collection getTaskCollWithLeftJoin(String sql)throws DataAccessException{
		return dao.getTaskColl(sql,true);
	}

	/**
	 * 根据ID取其VO
	 * @param TASK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public TaskVO getTaskByID(int TASK_ID)throws DataAccessException{
		return dao.getTaskByID(TASK_ID);
	}
}