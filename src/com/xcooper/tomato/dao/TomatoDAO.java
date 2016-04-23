package com.xcooper.tomato.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.tomato.vo.TomatoVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 番茄钟DAO
 * @author zdk
 * 2016-03-28 19:42:34
 */
public class TomatoDAO {

	/**
	 * 添加番茄钟
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void addTomato(TomatoVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TOMATO");

			sh.setInsertForInt("TOMATO_ID",VO.getTOMATO_ID());//TOMATO_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setInsertForInt("TASK_ID",VO.getTASK_ID());//对应任务
			sh.setInsertForDatetime("BEGIN_DATETIME", DateUtil.format(VO.getBEGIN_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//BEGIN_DATETIME
			sh.setInsertForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//END_DATETIME
			sh.setInsertForString("RING",VO.getRING());//铃声
			sh.setInsertForString("REMARK",VO.getREMARK());//REMARK
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加番茄钟");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改番茄钟
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void modifyTomato(TomatoVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TOMATO");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setColumnForInt("TASK_ID",VO.getTASK_ID());//对应任务
			sh.setColumnForDatetime("BEGIN_DATETIME",DateUtil.format(VO.getBEGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//BEGIN_DATETIME
			sh.setColumnForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//END_DATETIME
			sh.setColumnForString("RING",VO.getRING());//铃声
			sh.setColumnForString("REMARK",VO.getREMARK());//REMARK
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME

			sh.setWhereForInt("TOMATO_ID", " = ", VO.getTOMATO_ID());//TOMATO_ID

			sh.update(ResourceManager.getConnection(),"修改番茄钟");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除番茄钟
	 * @param TOMATO_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void delTomato(int TOMATO_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TOMATO");

			sh.setWhereForInt("TOMATO_ID", " = ", TOMATO_ID);//TOMATO_ID

			sh.delete(ResourceManager.getConnection(),"删除番茄钟");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除番茄钟
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void delTomato(Collection coll)throws DataAccessException{
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
				sh.setTable("TOMATO");

				//设置Where的条件
				sh.setWhereForInt("TOMATO_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//TOMATO_ID

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
	 * 根据SQL获取番茄钟集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public Collection getTomatoColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				TomatoVO VO = new TomatoVO();

				VO.setTOMATO_ID(rs.getInt("TOMATO_ID")); 	//TOMATO_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setCREATE_ID(rs.getInt("CREATE_ID")); 	//CREATE_ID
				VO.setTASK_ID(rs.getInt("TASK_ID")); 	//对应任务
				VO.setBEGIN_DATETIME(rs.getTimestamp("BEGIN_DATETIME")); 	//BEGIN_DATETIME
				VO.setEND_DATETIME(rs.getTimestamp("END_DATETIME")); 	//END_DATETIME
				VO.setRING(rs.getString("RING")); 	//铃声
				VO.setREMARK(rs.getString("REMARK")); 	//REMARK
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得番茄钟集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param TOMATO_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public TomatoVO getTomatoByID(int TOMATO_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("TOMATO");

		sh.setWhereForInt("TOMATO_ID", " = ", TOMATO_ID);//TOMATO_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getTomatoColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (TomatoVO)it.next();
		}

		return null;
	}
}