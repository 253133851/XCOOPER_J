package com.xcooper.sys.user.user.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.user.dao.UserDAO;
import com.xcooper.sys.user.user.vo.UserVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 用户Bean
 * @author zdk
 * 2016-03-28 19:20:05
 */
public class UserBean {

	Logger log = Logger.getLogger(UserBean.class);
	UserDAO dao;

	public UserBean(){
		dao = new UserDAO();
	}

	/**
	 * 添加用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void addUser(UserVO VO)throws DataAccessException {
		//设置注册时间
		VO.setREG_DATETIME(DateUtil.getCurrTime());
		dao.addUser(VO);
	}

	/**
	 * 修改用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void modifyUser(UserVO VO)throws DataAccessException {
		dao.modifyUser(VO);
	}

	/**
	 * 删除用户
	 * @param UID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void delUser(int UID)throws DataAccessException {
		dao.delUser(UID);
	}

	/**
	 * 批量删除用户
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void delUser(Collection coll)throws DataAccessException {
		dao.delUser(coll);
	}

	/**
	 * 根据SQL获取用户集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public Collection getUserColl(String sql)throws DataAccessException {
		return dao.getUserColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param UID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public UserVO getUserByID(int UID)throws DataAccessException {
		return dao.getUserByID(UID);
	}
}