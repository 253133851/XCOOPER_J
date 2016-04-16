package com.xcooper.project.projectMember.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.project.projectMember.dao.ProjectMemberDAO;
import com.xcooper.project.projectMember.vo.ProjectMemberVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 项目_成员Bean
 * @author zdk
 * 2016-03-28 19:29:42
 */
public class ProjectMemberBean {

	Logger log = Logger.getLogger(ProjectMemberBean.class);
	ProjectMemberDAO dao;

	public ProjectMemberBean(){
		dao = new ProjectMemberDAO();
	}

	/**
	 * 添加项目_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void addProjectMember(ProjectMemberVO VO)throws DataAccessException{
		dao.addProjectMember(VO);
	}

	/**
	 * 修改项目_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void modifyProjectMember(ProjectMemberVO VO)throws DataAccessException{
		dao.modifyProjectMember(VO);
	}

	/**
	 * 删除项目_成员
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void delProjectMember(int ID)throws DataAccessException{
		dao.delProjectMember(ID);
	}

	/**
	 * 批量删除项目_成员
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void delProjectMember(Collection coll)throws DataAccessException{
		dao.delProjectMember(coll);
	}

	/**
	 * 根据SQL获取项目_成员集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public Collection getProjectMemberColl(String sql)throws DataAccessException{
		return dao.getProjectMemberColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public ProjectMemberVO getProjectMemberByID(int ID)throws DataAccessException{
		return dao.getProjectMemberByID(ID);
	}
}