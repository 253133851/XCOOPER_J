package com.jiaorder.sys.role.busi;

import com.jiaorder.sys.role.dao.RoleDAO;
import com.jiaorder.sys.role.vo.RoleVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * ��ɫBean
 * @author pabula
 * 2016-03-11 00:57:19
 */
public class RoleBean {

	Logger log = Logger.getLogger(RoleBean.class);
	RoleDAO dao;

	public RoleBean(){
		dao = new RoleDAO();
	}

	/**
	 * ��ӽ�ɫ
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void addRole(RoleVO VO)throws DataAccessException {
		dao.addRole(VO);
	}

	/**
	 * �޸Ľ�ɫ
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void modifyRole(RoleVO VO)throws DataAccessException {
		dao.modifyRole(VO);
	}

	/**
	 * ɾ���ɫ
	 * @param ROLE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void delRole(String ROLE_ID)throws DataAccessException {
		dao.delRole(ROLE_ID);
	}

	/**
	 * ����ɾ���ɫ
	 * @param coll ����ϣ��������֮���ð�Ƕ��Ÿ���
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void delRole(Collection coll)throws DataAccessException {
		dao.delRole(coll);
	}

	/**
	 * ���SQL��ȡ��ɫ����
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public Collection getRoleColl(String sql)throws DataAccessException {
		return dao.getRoleColl(sql);
	}

	/**
	 * ���IDȡ��VO
	 * @param ROLE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public RoleVO getRoleByID(String ROLE_ID)throws DataAccessException {
		return dao.getRoleByID(ROLE_ID);
	}
}