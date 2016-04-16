package com.xcooper.project.project.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.project.project.vo.ProjectVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 项目DAO
 * @author zdk
 * 2016-03-28 19:28:58
 */
public class ProjectDAO {

	/**
	 * 添加项目
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void addProject(ProjectVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT");

			sh.setInsertForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForString("PROJECT_NAME",VO.getPROJECT_NAME());//PROJECT_NAME
			sh.setInsertForString("PROJECT_INFO",VO.getPROJECT_INFO());//PROJECT_INFO
			sh.setInsertForInt("TYPE",VO.getTYPE());//TYPE
			sh.setInsertForInt("IS_HIDE",VO.getIS_HIDE());//是否隐藏敏感
			sh.setInsertForInt("IS_READ_ONLY",VO.getIS_READ_ONLY());//是否只读
			sh.setInsertForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME
			sh.setInsertForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//DEL_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加项目");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改项目
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void modifyProject(ProjectVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForString("PROJECT_NAME",VO.getPROJECT_NAME());//PROJECT_NAME
			sh.setColumnForString("PROJECT_INFO",VO.getPROJECT_INFO());//PROJECT_INFO
			sh.setColumnForInt("TYPE",VO.getTYPE());//TYPE
			sh.setColumnForInt("IS_HIDE",VO.getIS_HIDE());//是否隐藏敏感
			sh.setColumnForInt("IS_READ_ONLY",VO.getIS_READ_ONLY());//是否只读
			sh.setColumnForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME
			sh.setColumnForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//DEL_DATETIME

			sh.setWhereForInt("PROJECT_ID", " = ", VO.getPROJECT_ID());//PROJECT_ID

			sh.update(ResourceManager.getConnection(),"修改项目");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除项目
	 * @param PROJECT_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void delProject(int PROJECT_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT");

			sh.setWhereForInt("PROJECT_ID", " = ", PROJECT_ID);//PROJECT_ID

			sh.delete(ResourceManager.getConnection(),"删除项目");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除项目
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public void delProject(Collection coll)throws DataAccessException{
		Iterator it=coll.iterator();
		Connection conn=null;
		Statement stmt=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();

			while(it.hasNext()){
				String ids = it.next().toString();
				String[] id = ids.split(",");

				SqlHelper sh = new SqlHelper();

				//设置表名
				sh.setTable("PROJECT");

				//设置Where的条件
				sh.setWhereForInt("PROJECT_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//PROJECT_ID

				String sql = sh.getSQL(sh.getDeleteSQL());

				//添加到批处理中
				stmt.addBatch(sql);
			}

			//执行批处理
			stmt.executeBatch();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	/**
	 * 根据SQL获取项目集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public Collection getProjectColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ProjectVO VO = new ProjectVO();

				VO.setPROJECT_ID(rs.getInt("PROJECT_ID")); 	//PROJECT_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setPROJECT_NAME(rs.getString("PROJECT_NAME")); 	//PROJECT_NAME
				VO.setPROJECT_INFO(rs.getString("PROJECT_INFO")); 	//PROJECT_INFO
				VO.setTYPE(rs.getInt("TYPE")); 	//TYPE
				VO.setIS_HIDE(rs.getInt("IS_HIDE")); 	//是否隐藏敏感
				VO.setIS_READ_ONLY(rs.getInt("IS_READ_ONLY")); 	//是否只读
				VO.setIS_DEL(rs.getInt("IS_DEL")); 	//IS_DEL
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME
				VO.setDEL_DATETIME(rs.getTimestamp("DEL_DATETIME")); 	//DEL_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得项目集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param PROJECT_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:28:58
	 */
	public ProjectVO getProjectByID(int PROJECT_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("PROJECT");

		sh.setWhereForInt("PROJECT_ID", " = ", PROJECT_ID);//PROJECT_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getProjectColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ProjectVO)it.next();
		}

		return null;
	}
}