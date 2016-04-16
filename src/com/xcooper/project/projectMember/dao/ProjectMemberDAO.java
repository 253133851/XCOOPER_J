package com.xcooper.project.projectMember.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.project.projectMember.vo.ProjectMemberVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 项目_成员DAO
 * @author zdk
 * 2016-03-28 19:29:42
 */
public class ProjectMemberDAO {

	/**
	 * 添加项目_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void addProjectMember(ProjectMemberVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT_MEMBER");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("PEOJECT_ID",VO.getPEOJECT_ID());//PEOJECT_ID
			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setInsertForInt("LEVEL",VO.getLEVEL());//LEVEL
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加项目_成员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改项目_成员
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void modifyProjectMember(ProjectMemberVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT_MEMBER");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("PEOJECT_ID",VO.getPEOJECT_ID());//PEOJECT_ID
			sh.setColumnForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setColumnForInt("LEVEL",VO.getLEVEL());//LEVEL
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改项目_成员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除项目_成员
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void delProjectMember(int ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PROJECT_MEMBER");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除项目_成员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除项目_成员
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public void delProjectMember(Collection coll)throws DataAccessException{
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
				sh.setTable("PROJECT_MEMBER");

				//设置Where的条件
				sh.setWhereForInt("ID", " = ", StrUtil.getNotNullIntValue(id[0]));//ID

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
	 * 根据SQL获取项目_成员集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public Collection getProjectMemberColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ProjectMemberVO VO = new ProjectMemberVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setPEOJECT_ID(rs.getInt("PEOJECT_ID")); 	//PEOJECT_ID
				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//MEMBER_ID
				VO.setLEVEL(rs.getInt("LEVEL")); 	//LEVEL
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得项目_成员集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:29:42
	 */
	public ProjectMemberVO getProjectMemberByID(int ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("PROJECT_MEMBER");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getProjectMemberColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ProjectMemberVO)it.next();
		}

		return null;
	}
}