package com.xcooper.sys.log.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.log.dao.LogDAO;
import com.xcooper.sys.log.vo.LogVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 日志Bean
 * @author zdk
 * 2016-03-28 19:40:04
 */
public class LogBean {

	Logger log = Logger.getLogger(LogBean.class);
	LogDAO dao;

	public LogBean(){
		dao = new LogDAO();
	}

	/**
	 * 添加日志
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void addLog(LogVO VO)throws DataAccessException{
		VO.setLOG_ID(SeqNumHelper.getNewSeqNum("log"));
		VO.setADD_DATETIME(DateUtil.getCurrTime());
		dao.addLog(VO);
	}

	/**
	 * 修改日志
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void modifyLog(LogVO VO)throws DataAccessException{
		dao.modifyLog(VO);
	}

	/**
	 * 删除日志
	 * @param LOG_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void delLog(int LOG_ID)throws DataAccessException{
		dao.delLog(LOG_ID);
	}

	/**
	 * 批量删除日志
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void delLog(Collection coll)throws DataAccessException{
		dao.delLog(coll);
	}

	/**
	 * 根据SQL获取日志集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public Collection getLogColl(String sql)throws DataAccessException{
		return dao.getLogColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public LogVO getLogByID(int LOG_ID)throws DataAccessException{
		return dao.getLogByID(LOG_ID);
	}
}