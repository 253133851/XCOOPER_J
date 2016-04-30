package com.xcooper.task.taskcheckitem.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.task.taskcheckitem.dao.TaskCheckItemDAO;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 任务检查项Bean
 * @author zdk
 * 2016-03-28 19:38:42
 */
public class TaskCheckItemBean {

	Logger log = Logger.getLogger(TaskCheckItemBean.class);
	TaskCheckItemDAO dao;

	public TaskCheckItemBean(){
		dao = new TaskCheckItemDAO();
	}

	/**
	 * 添加任务检查项
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void addTaskCheckItem(TaskCheckItemVO VO)throws DataAccessException{
		dao.addTaskCheckItem(VO);
	}

	/**
	 * 修改任务检查项
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void modifyTaskCheckItem(TaskCheckItemVO VO)throws DataAccessException{
		dao.modifyTaskCheckItem(VO);
	}

	/**
	 * 删除任务检查项
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void delTaskCheckItem(int ID)throws DataAccessException{
		dao.delTaskCheckItem(ID);
	}

	/**
	 * 根据 TASK_ID 删除任务检查项
	 * @param TASK_ID
	 * @throws DataAccessException
	 * @authuor zdk 2016-04-30
     */
	public void  delTaskCheckItemByTaskId(int TASK_ID) throws DataAccessException{
		dao.delTaskCheckItemByTaskId(TASK_ID);
	}
	/**
	 * 批量删除任务检查项
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void delTaskCheckItem(Collection coll)throws DataAccessException{
		dao.delTaskCheckItem(coll);
	}

	/**
	 * 根据SQL获取任务检查项集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public Collection getTaskCheckItemColl(String sql)throws DataAccessException{
		return dao.getTaskCheckItemColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public TaskCheckItemVO getTaskCheckItemByID(int ID)throws DataAccessException{
		return dao.getTaskCheckItemByID(ID);
	}
}