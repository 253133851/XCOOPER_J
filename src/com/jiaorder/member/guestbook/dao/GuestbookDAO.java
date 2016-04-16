package com.jiaorder.member.guestbook.dao;

import com.jiaorder.member.guestbook.vo.GuestbookVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 客户反馈DAO
 * @author zdk
 * 2016-03-21 15:15:28
 */
public class GuestbookDAO {

	/**
	 * 添加客户反馈
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public void addGuestbook(GuestbookVO VO)throws DataAccessException{
		try {
			SqlHelper sh = new SqlHelper();
			sh.setTable("GUESTBOOK");
			sh.setInsertForInt("GUESTBOOK_ID",VO.getGUESTBOOK_ID());//GUESTBOOK_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setInsertForString("NOTIFY_BACK",VO.getNOTIFY_BACK());//反馈内容
			sh.setInsertForInt("STATE",VO.getSTATE());//状态
			sh.setInsertForInt("IS_DEL",VO.getIS_DEL());//是否删除
			sh.setInsertForInt("IS_DONE",VO.getIS_DONE());//是否完结
			sh.setInsertForString("DONE",VO.getDONE());//完结内容
			sh.setInsertForString("IP",VO.getIP());//ip
			sh.setInsertForDatetime("DONE_DATETIME", DateUtil.format(VO.getDONE_TIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);
			sh.setInsertForInt("USER_ID",VO.getUSER_ID());
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//添加时间
			sh.insert(ResourceManager.getConnection(),"添加客户反馈");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改客户反馈
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public void modifyGuestbook(GuestbookVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();
			sh.setTable("GUESTBOOK");
			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("USER_ID",VO.getUSER_ID());//SERVICE_ID
			sh.setColumnForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setColumnForString("NOTIFY_BACK",VO.getNOTIFY_BACK());//反馈内容
			sh.setColumnForInt("STATE",VO.getSTATE());//状态
			sh.setColumnForInt("IS_DEL",VO.getIS_DEL());//是否删除
			sh.setColumnForInt("IS_DONE",VO.getIS_DONE());//是否完结
			sh.setColumnForString("DONE",VO.getDONE());//完结内容
			sh.setColumnForString("IP",VO.getIP());//ip
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//添加时间
			sh.setColumnForDatetime("DONE_DATETIME", DateUtil.format(VO.getDONE_TIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//添加时间
			sh.setWhereForInt("GUESTBOOK_ID", " = ", VO.getGUESTBOOK_ID());//GUESTBOOK_ID
			sh.update(ResourceManager.getConnection(),"修改客户反馈");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除客户反馈
	 * @param GUESTBOOK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public void delGuestbook(int GUESTBOOK_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("GUESTBOOK");

			sh.setWhereForInt("GUESTBOOK_ID", " = ", GUESTBOOK_ID);//GUESTBOOK_ID

			sh.delete(ResourceManager.getConnection(),"删除客户反馈");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除客户反馈
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public void delGuestbook(Collection coll)throws DataAccessException{
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
				sh.setTable("GUESTBOOK");

				//设置Where的条件
				sh.setWhereForInt("GUESTBOOK_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//GUESTBOOK_ID

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
	 * 查询所有通知
	 */
	public List<GuestbookVO> getGuestbook(int serviceId, GuestbookVO filter) {
		List<GuestbookVO> guestbookVOs = new ArrayList<>();
		try {
			SqlHelper sqlHelper = new SqlHelper();
			sqlHelper.setTable("GUESTBOOK");
			sqlHelper.setSelectColumn("*");
			sqlHelper.setLeftJoin("member", "GUESTBOOK.MEMBER_ID =member.MEMBER_ID and member.SERVICE_ID=GUESTBOOK.SERVICE_ID", true);
			sqlHelper.setWhereForInt("GUESTBOOK.SERVICE_ID", " = ", serviceId);
			if (filter != null) {
				sqlHelper = filterSqlHelper(sqlHelper, filter, true);
			}
			String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
			guestbookVOs = getGuestbookColl(sql);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return guestbookVOs;
	}


	/**
	 * 过滤条件
	 *
	 * @param sqlHelper
	 * @param filterMember
	 * @return
	 * @throws DataAccessException
	 */
	public SqlHelper filterSqlHelper(SqlHelper sqlHelper, GuestbookVO filterMember, boolean withLimit) throws DataAccessException {

		if(filterMember.getSTATE()== GuestbookVO.WAIT_ANSWER) {
			sqlHelper.setWhereForInt("GUESTBOOK.STATE", " = ", GuestbookVO.WAIT_ANSWER);
			sqlHelper.setWhereForInt("GUESTBOOK.IS_DONE", " = ", GuestbookVO.NOT_DONE);
		}else if(filterMember.getSTATE()== GuestbookVO.HAS_ANSWER){
			sqlHelper.setWhereForInt("GUESTBOOK.STATE", " = ", GuestbookVO.HAS_ANSWER);
			sqlHelper.setWhereForInt("GUESTBOOK.IS_DONE", " = ", GuestbookVO.NOT_DONE);
		}
		if (withLimit) {
			sqlHelper.setLIMIT((filterMember.getPageIndex() - 1) * filterMember.getPageSize(), filterMember.getPageSize());
		}
		return sqlHelper;
	}

	/**
	 * 查询符合目标条件下的总条数
	 */
	public int getGuestbookCount(int serviceId, GuestbookVO filter) {
		int size = 0;
		try {
			SqlHelper sqlHelper = new SqlHelper();
			sqlHelper.setTable("GUESTBOOK");
			sqlHelper.setSelectColumn("count(GUESTBOOK_ID) as SIZE");
			sqlHelper.setLeftJoin("member", "GUESTBOOK.MEMBER_ID =member.MEMBER_ID and member.SERVICE_ID=GUESTBOOK.SERVICE_ID", true);
			sqlHelper.setWhereForInt("GUESTBOOK.SERVICE_ID", " = ", serviceId);
			if (filter != null) {
				sqlHelper = filterSqlHelper(sqlHelper, filter, false);
			}
			String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ResourceManager.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					size = rs.getInt("SIZE");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				ResourceManager.close(conn);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 根据SQL获取客户反馈集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public List<GuestbookVO> getGuestbookColl(String sql)throws DataAccessException{

		List<GuestbookVO> resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				GuestbookVO VO = new GuestbookVO();
				VO.setGUESTBOOK_ID(rs.getInt("GUESTBOOK_ID")); 	//GUESTBOOK_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//MEMBER_ID
				VO.setUSER_ID(rs.getInt("USER_ID")); 	//MEMBER_ID
				VO.setNOTIFY_BACK(rs.getString("NOTIFY_BACK")); 	//反馈内容
				VO.setSTATE(rs.getInt("STATE")); 	//状态
				VO.setIS_DEL(rs.getInt("IS_DEL")); 	//是否删除
				VO.setIS_DONE(rs.getInt("IS_DONE")); 	//是否完结
				VO.setDONE(rs.getString("DONE")); 	//完结内容
				VO.setIP(rs.getString("IP")); 	//ip
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//添加时间
				VO.setDONE_TIME(rs.getTimestamp("DONE_DATETIME")); 	//添加时间
				VO.setMEMBER_NAME(rs.getString("USER_NAME"));
				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得客户反馈集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param GUESTBOOK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:15:28
	 */
	public GuestbookVO getGuestbookByID(int serviceId, int GUESTBOOK_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("GUESTBOOK");
		sh.setLeftJoin("member", "GUESTBOOK.MEMBER_ID =member.MEMBER_ID and member.SERVICE_ID=GUESTBOOK.SERVICE_ID", true);
		sh.setWhereForInt("GUESTBOOK_ID", " = ", GUESTBOOK_ID);//GUESTBOOK_ID
		sh.setWhereForInt("GUESTBOOK.SERVICE_ID", " = ", serviceId);

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getGuestbookColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (GuestbookVO)it.next();
		}

		return null;
	}
}