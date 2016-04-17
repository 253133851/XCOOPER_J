package com.xcooper.list.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.list.vo.ListVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 项目清单DAO
 * @author zdk
 * 2016-03-28 19:33:03
 */
public class ListDAO {

	/**
	 * 添加项目清单
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void addList(ListVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LIST");

			sh.setInsertForInt("LIST_ID",VO.getLIST_ID());//LIST_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setInsertForString("LIST_NAME",VO.getLIST_NAME());//LIST_NAME
			sh.setInsertForInt("ORDER_NUM",VO.getORDER_NUM());//ORDER_NUM
			sh.setInsertForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME
			sh.setInsertForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//DEL_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加项目清单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改项目清单
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void modifyList(ListVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LIST");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setColumnForString("LIST_NAME",VO.getLIST_NAME());//LIST_NAME
			sh.setColumnForInt("ORDER_NUM",VO.getORDER_NUM());//ORDER_NUM
			sh.setColumnForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME
			sh.setColumnForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//DEL_DATETIME

			sh.setWhereForInt("LIST_ID", " = ", VO.getLIST_ID());//LIST_ID

			sh.update(ResourceManager.getConnection(),"修改项目清单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除项目清单
	 * @param LIST_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void delList(int LIST_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LIST");

			sh.setWhereForInt("LIST_ID", " = ", LIST_ID);//LIST_ID

			sh.delete(ResourceManager.getConnection(),"删除项目清单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除项目清单
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void delList(Collection coll)throws DataAccessException{
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
				sh.setTable("LIST");

				//设置Where的条件
				sh.setWhereForInt("LIST_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//LIST_ID

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
	 * 根据SQL获取项目清单集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public Collection getListColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ListVO VO = new ListVO();

				VO.setLIST_ID(rs.getInt("LIST_ID")); 	//LIST_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setPROJECT_ID(rs.getInt("PROJECT_ID")); 	//PROJECT_ID
				VO.setLIST_NAME(rs.getString("LIST_NAME")); 	//LIST_NAME
				VO.setORDER_NUM(rs.getInt("ORDER_NUM")); 	//ORDER_NUM
				VO.setIS_DEL(rs.getInt("IS_DEL")); 	//IS_DEL
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME
				VO.setDEL_DATETIME(rs.getTimestamp("DEL_DATETIME")); 	//DEL_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得项目清单集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param LIST_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public ListVO getListByID(int LIST_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("LIST");

		sh.setWhereForInt("LIST_ID", " = ", LIST_ID);//LIST_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getListColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ListVO)it.next();
		}

		return null;
	}
}