package com.xcooper.member.member.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.member.member.dao.MemberDAO;
import com.xcooper.member.member.vo.MemberVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 用户Bean
 * @author zdk
 * 2016-03-28 19:24:31
 */
public class MemberBean {

	Logger log = Logger.getLogger(MemberBean.class);
	MemberDAO dao;

	public MemberBean(){
		dao = new MemberDAO();
	}

	/**
	 * 添加用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void addMember(MemberVO VO)throws DataAccessException {
		dao.addMember(VO);
	}

	/**
	 * 修改用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void modifyMember(MemberVO VO)throws DataAccessException {
		dao.modifyMember(VO);
	}

	/**
	 * 删除用户
	 * @param MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void delMember(int MEMBER_ID)throws DataAccessException {
		dao.delMember(MEMBER_ID);
	}

	/**
	 * 批量删除用户
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void delMember(Collection coll)throws DataAccessException {
		dao.delMember(coll);
	}

	/**
	 * 根据SQL获取用户集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public Collection getMemberColl(String sql)throws DataAccessException {
		return dao.getMemberColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public MemberVO getMemberByID(int MEMBER_ID)throws DataAccessException {
		return dao.getMemberByID(MEMBER_ID);
	}
}