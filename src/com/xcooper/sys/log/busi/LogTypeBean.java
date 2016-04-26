package com.xcooper.sys.log.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.log.dao.LogTypeDAO;
import com.xcooper.sys.log.vo.LogTypeVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 日志类型Bean
 * @author zdk
 * 2016-03-28 19:40:50
 */
public class LogTypeBean {

	Logger log = Logger.getLogger(LogTypeBean.class);
	LogTypeDAO dao;

	public LogTypeBean(){
		dao = new LogTypeDAO();
	}

	/**
	 * 添加日志类型
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void addLogType(LogTypeVO VO)throws DataAccessException{
		dao.addLogType(VO);
	}

	/**
	 * 修改日志类型
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void modifyLogType(LogTypeVO VO)throws DataAccessException{
		dao.modifyLogType(VO);
	}

	/**
	 * 删除日志类型
	 * @param LOG_TYPE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void delLogType(int LOG_TYPE_ID)throws DataAccessException{
		dao.delLogType(LOG_TYPE_ID);
	}

	/**
	 * 批量删除日志类型
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void delLogType(Collection coll)throws DataAccessException{
		dao.delLogType(coll);
	}

	/**
	 * 根据SQL获取日志类型集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public Collection getLogTypeColl(String sql)throws DataAccessException{
		return dao.getLogTypeColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_TYPE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public LogTypeVO getLogTypeByID(int LOG_TYPE_ID)throws DataAccessException{
		return dao.getLogTypeByID(LOG_TYPE_ID);
	}
}