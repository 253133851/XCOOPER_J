package com.xcooper.member.member.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.member.member.vo.MemberVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 用户DAO
 * @author zdk
 * 2016-03-28 19:24:31
 */
public class MemberDAO {

	/**
	 * 添加用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void addMember(MemberVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER");

			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setInsertForInt("UID",VO.getUID());//用户ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForString("NAME",VO.getNAME());//姓名
			sh.setInsertForString("JOB",VO.getJOB());//职位
			sh.setInsertForString("MOBILE",VO.getMOBILE());//手机
			sh.setInsertForString("EMAIL",VO.getEMAIL());//邮箱
			sh.setInsertForString("QQ",VO.getQQ());//QQ

			sh.insert(ResourceManager.getConnection(),"添加用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void modifyMember(MemberVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER");

			sh.setColumnForInt("UID",VO.getUID());//用户ID
			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForString("NAME",VO.getNAME());//姓名
			sh.setColumnForString("JOB",VO.getJOB());//职位
			sh.setColumnForString("MOBILE",VO.getMOBILE());//手机
			sh.setColumnForString("EMAIL",VO.getEMAIL());//邮箱
			sh.setColumnForString("QQ",VO.getQQ());//QQ

			sh.setWhereForInt("MEMBER_ID", " = ", VO.getMEMBER_ID());//MEMBER_ID

			sh.update(ResourceManager.getConnection(),"修改用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除用户
	 * @param MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void delMember(int MEMBER_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER");

			sh.setWhereForInt("MEMBER_ID", " = ", MEMBER_ID);//MEMBER_ID

			sh.delete(ResourceManager.getConnection(),"删除用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除用户byUserId
	 * @param USER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-04-22
	 */
	public void delMemberByUserId(int USER_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER");

			sh.setWhereForInt("USER_ID", " = ", USER_ID);//USER_ID

			sh.delete(ResourceManager.getConnection(),"删除用户byUserId");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	/**
	 * 批量删除用户
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public void delMember(Collection coll)throws DataAccessException {
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
				sh.setTable("MEMBER");

				//设置Where的条件
				sh.setWhereForInt("MEMBER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//MEMBER_ID

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
	 * 根据SQL获取用户集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public Collection getMemberColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				MemberVO VO = new MemberVO();

				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//MEMBER_ID
				VO.setUID(rs.getInt("UID")); 	//用户ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setNAME(rs.getString("NAME")); 	//姓名
				VO.setJOB(rs.getString("JOB")); 	//职位
				VO.setMOBILE(rs.getString("MOBILE")); 	//手机
				VO.setEMAIL(rs.getString("EMAIL")); 	//邮箱
				VO.setQQ(rs.getString("QQ")); 	//QQ

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得用户集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:24:31
	 */
	public MemberVO getMemberByID(int MEMBER_ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("MEMBER");

		sh.setWhereForInt("MEMBER_ID", " = ", MEMBER_ID);//MEMBER_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getMemberColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (MemberVO)it.next();
		}

		return null;
	}
}