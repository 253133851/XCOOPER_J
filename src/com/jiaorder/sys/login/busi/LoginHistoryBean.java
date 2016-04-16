package com.jiaorder.sys.login.busi;

import com.jiaorder.sys.login.dao.LoginHistoryDAO;
import com.jiaorder.sys.login.vo.LoginHistoryVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 登录历史Bean
 * @author pabula
 * 2015-07-12 00:57:50
 */
public class LoginHistoryBean {

	Logger log = Logger.getLogger(LoginHistoryBean.class);
	LoginHistoryDAO dao;

	public LoginHistoryBean(){
		dao = new LoginHistoryDAO();
	}

	/**
	 * 添加登录历史
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void addLoginHistory(LoginHistoryVO VO)throws DataAccessException {
		dao.addLoginHistory(VO);
	}

	/**
	 * 修改登录历史
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void modifyLoginHistory(LoginHistoryVO VO)throws DataAccessException{
		dao.modifyLoginHistory(VO);
	}

	/**
	 * 删除登录历史
	 * @param ID
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void delLoginHistory(int ID)throws DataAccessException{
		dao.delLoginHistory(ID);
	}

	/**
	 * 批量删除登录历史
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void delLoginHistory(Collection coll)throws DataAccessException{
		dao.delLoginHistory(coll);
	}

	/**
	 * 根据SQL获取登录历史集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public Collection getLoginHistoryColl(String sql)throws DataAccessException{
		return dao.getLoginHistoryColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public LoginHistoryVO getLoginHistoryByID(int ID)throws DataAccessException{
		return dao.getLoginHistoryByID(ID);
	}
}