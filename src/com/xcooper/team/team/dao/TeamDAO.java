package com.xcooper.team.team.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.team.team.vo.TeamVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 团队DAO
 * @author zdk
 * 2016-03-28 19:25:36
 */
public class TeamDAO {

	/**
	 * 添加团队
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void addTeam(TeamVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TEAM");

			sh.setInsertForInt("TEAM_ID",VO.getTEAM_ID());//TEAM_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("CREATE_ID",VO.getCREATE_ID());//创建人
			sh.setInsertForString("TEAM_NAME",VO.getTEAM_NAME());//TEAM_NAME
			sh.setInsertForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加团队");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改团队
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void modifyTeam(TeamVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TEAM");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("CREATE_ID",VO.getCREATE_ID());//创建人
			sh.setColumnForString("TEAM_NAME",VO.getTEAM_NAME());//TEAM_NAME
			sh.setColumnForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME

			sh.setWhereForInt("TEAM_ID", " = ", VO.getTEAM_ID());//TEAM_ID

			sh.update(ResourceManager.getConnection(),"修改团队");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除团队
	 * @param TEAM_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void delTeam(int TEAM_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TEAM");

			sh.setWhereForInt("TEAM_ID", " = ", TEAM_ID);//TEAM_ID

			sh.delete(ResourceManager.getConnection(),"删除团队");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除团队
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public void delTeam(Collection coll)throws DataAccessException{
		Iterator it=coll.iterator();
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();

			while(it.hasNext()){
				String ids = it.next().toString();
				String[] id = ids.split(",");

				SqlHelper sh = new SqlHelper();

				//设置表名
				sh.setTable("TEAM");

				//设置Where的条件
				sh.setWhereForInt("TEAM_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//TEAM_ID

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
	 * 根据SQL获取团队集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public Collection getTeamColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				TeamVO VO = new TeamVO();

				VO.setTEAM_ID(rs.getInt("TEAM_ID")); 	//TEAM_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setCREATE_ID(rs.getInt("CREATE_ID")); 	//创建人
				VO.setTEAM_NAME(rs.getString("TEAM_NAME")); 	//TEAM_NAME
				VO.setIS_DEL(rs.getInt("IS_DEL")); 	//IS_DEL
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得团队集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param TEAM_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:25:36
	 */
	public TeamVO getTeamByID(int TEAM_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("TEAM");

		sh.setWhereForInt("TEAM_ID", " = ", TEAM_ID);//TEAM_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getTeamColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (TeamVO)it.next();
		}

		return null;
	}
}