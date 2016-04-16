package com.jiaorder.sys.log.busi;

import com.jiaorder.sys.log.dao.OperLogDAO;
import com.jiaorder.sys.log.vo.OperLogVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * 操作日志Bean
 * @author Paula
 * 2016-03-17 13:28:08
 */
public class OperLogBean {

	Logger log = Logger.getLogger(OperLogBean.class);
	OperLogDAO dao;

	public OperLogBean(){
		dao = new OperLogDAO();
	}

	/**
	 * 添加操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public void addOperLog(OperLogVO VO)throws DataAccessException {
		dao.addOperLog(VO);
	}

	/**
	 * 修改操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public void modifyOperLog(OperLogVO VO)throws DataAccessException {
		dao.modifyOperLog(VO);
	}

	/**
	 * 删除操作日志
	 * @param ID
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public void delOperLog(int ID)throws DataAccessException {
		dao.delOperLog(ID);
	}

	/**
	 * 批量删除操作日志
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public void delOperLog(Collection coll)throws DataAccessException {
		dao.delOperLog(coll);
	}

	/**
	 * 根据SQL获取操作日志集合
	 * @param sql
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public List<OperLogVO> getOperLogColl(String sql)throws DataAccessException {
		return dao.getOperLogColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:28:08
	 */
	public OperLogVO getOperLogByID(int ID)throws DataAccessException {
		return dao.getOperLogByID(ID);
	}
}