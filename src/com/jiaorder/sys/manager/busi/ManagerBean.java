package com.jiaorder.sys.manager.busi;

import com.jiaorder.sys.manager.dao.ManagerDAO;
import com.jiaorder.sys.manager.vo.ManagerVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 管理员Bean
 * @author pabula
 * 2016-03-11 00:55:08
 */
public class ManagerBean {

	Logger log = Logger.getLogger(ManagerBean.class);
	ManagerDAO dao;

	public ManagerBean(){
		dao = new ManagerDAO();
	}

	/**
	 * 添加管理员
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void addManager(ManagerVO VO)throws DataAccessException {
		dao.addManager(VO);
	}

	/**
	 * 修改管理员
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void modifyManager(ManagerVO VO)throws DataAccessException{
		dao.modifyManager(VO);
	}

	/**
	 * 删除管理员
	 * @param MANAGER_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void delManager(int MANAGER_ID)throws DataAccessException{
		dao.delManager(MANAGER_ID);
	}

	/**
	 * 批量删除管理员
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void delManager(Collection coll)throws DataAccessException{
		dao.delManager(coll);
	}

	/**
	 * 根据SQL获取管理员集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public Collection getManagerColl(String sql)throws DataAccessException{
		return dao.getManagerColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param MANAGER_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public ManagerVO getManagerByID(int MANAGER_ID)throws DataAccessException{
		return dao.getManagerByID(MANAGER_ID);
	}
}