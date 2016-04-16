package com.xcooper.project.project.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.project.project.dao.ProjectDAO;
import com.xcooper.project.project.vo.ProjectVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 项目Bean
 * @author zdk
 * 2016-03-28 19:28:58
 */
public class ProjectBean {

	Logger log = Logger.getLogger(ProjectBean.class);
	ProjectDAO dao;

	public ProjectBean(){
		dao = new ProjectDAO();
	}

	/**
	 * 添加项目
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void addProject(ProjectVO VO)throws DataAccessException{
		dao.addProject(VO);
	}

	/**
	 * 修改项目
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void modifyProject(ProjectVO VO)throws DataAccessException{
		dao.modifyProject(VO);
	}

	/**
	 * 删除项目
	 * @param PROJECT_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void delProject(int PROJECT_ID)throws DataAccessException{
		dao.delProject(PROJECT_ID);
	}

	/**
	 * 批量删除项目
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void delProject(Collection coll)throws DataAccessException{
		dao.delProject(coll);
	}

	/**
	 * 根据SQL获取项目集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public Collection getProjectColl(String sql)throws DataAccessException{
		return dao.getProjectColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param PROJECT_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public ProjectVO getProjectByID(int PROJECT_ID)throws DataAccessException{
		return dao.getProjectByID(PROJECT_ID);
	}
}