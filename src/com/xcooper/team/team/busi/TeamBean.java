package com.xcooper.team.team.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.team.team.dao.TeamDAO;
import com.xcooper.team.team.vo.TeamVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 团队Bean
 * @author zdk
 * 2016-03-28 19:25:36
 */
public class TeamBean {

	Logger log = Logger.getLogger(TeamBean.class);
	TeamDAO dao;

	public TeamBean(){
		dao = new TeamDAO();
	}

	/**
	 * 添加团队
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void addTeam(TeamVO VO)throws DataAccessException{
		dao.addTeam(VO);
	}

	/**
	 * 修改团队
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void modifyTeam(TeamVO VO)throws DataAccessException{
		dao.modifyTeam(VO);
	}

	/**
	 * 删除团队
	 * @param TEAM_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void delTeam(int TEAM_ID)throws DataAccessException{
		dao.delTeam(TEAM_ID);
	}

	/**
	 * 批量删除团队
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void delTeam(Collection coll)throws DataAccessException{
		dao.delTeam(coll);
	}

	/**
	 * 根据SQL获取团队集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public Collection getTeamColl(String sql)throws DataAccessException{
		return dao.getTeamColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param TEAM_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public TeamVO getTeamByID(int TEAM_ID)throws DataAccessException{
		return dao.getTeamByID(TEAM_ID);
	}
}