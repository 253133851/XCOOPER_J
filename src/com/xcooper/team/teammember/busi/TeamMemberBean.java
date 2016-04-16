package com.xcooper.team.teammember.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.team.teammember.dao.TeamMemberDAO;
import com.xcooper.team.teammember.vo.TeamMemberVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 团队_成员Bean
 * @author zdk
 * 2016-03-28 19:26:29
 */
public class TeamMemberBean {

	Logger log = Logger.getLogger(TeamMemberBean.class);
	TeamMemberDAO dao;

	public TeamMemberBean(){
		dao = new TeamMemberDAO();
	}

	/**
	 * 添加团队_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public void addTeamMember(TeamMemberVO VO)throws DataAccessException {
		dao.addTeamMember(VO);
	}

	/**
	 * 修改团队_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public void modifyTeamMember(TeamMemberVO VO)throws DataAccessException {
		dao.modifyTeamMember(VO);
	}

	/**
	 * 删除团队_成员
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public void delTeamMember(int ID)throws DataAccessException {
		dao.delTeamMember(ID);
	}

	/**
	 * 批量删除团队_成员
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public void delTeamMember(Collection coll)throws DataAccessException {
		dao.delTeamMember(coll);
	}

	/**
	 * 根据SQL获取团队_成员集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public Collection getTeamMemberColl(String sql)throws DataAccessException {
		return dao.getTeamMemberColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:26:29
	 */
	public TeamMemberVO getTeamMemberByID(int ID)throws DataAccessException {
		return dao.getTeamMemberByID(ID);
	}
}