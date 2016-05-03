package com.xcooper.sys.log.busi;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.xcooper.sys.log.dao.LogMemberDAO;
import com.xcooper.sys.log.vo.LogMemberVO;
import com.pabula.fw.exception.DataAccessException;

/**
 * 用户通知Bean
 * @author zdk
 * 2016-05-03 11:59:41
 */
public class LogMemberBean {

	Logger log = Logger.getLogger(LogMemberBean.class);
	LogMemberDAO dao;

	public LogMemberBean(){
		dao = new LogMemberDAO();
	}

	/**
	 * 添加用户通知
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void addLogMember(LogMemberVO VO)throws DataAccessException{
		dao.addLogMember(VO);
	}

	/**
	 * 修改用户通知
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void modifyLogMember(LogMemberVO VO)throws DataAccessException{
		dao.modifyLogMember(VO);
	}

	/**
	 * 删除用户通知
	 * @param LOG_MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void delLogMember(int LOG_MEMBER_ID)throws DataAccessException{
		dao.delLogMember(LOG_MEMBER_ID);
	}

	/**
	 * 批量删除用户通知
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void delLogMember(Collection coll)throws DataAccessException{
		dao.delLogMember(coll);
	}

	/**
	 * 根据SQL获取用户通知集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public Collection getLogMemberColl(String sql)throws DataAccessException{
		return dao.getLogMemberColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public LogMemberVO getLogMemberByID(int LOG_MEMBER_ID)throws DataAccessException{
		return dao.getLogMemberByID(LOG_MEMBER_ID);
	}
}