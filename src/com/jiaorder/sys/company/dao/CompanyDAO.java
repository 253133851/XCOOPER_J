package com.jiaorder.sys.company.dao;

import com.jiaorder.sys.company.vo.CompanyVO;
import com.pabula.common.db.SqlHelper;
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

/**
 * 公司DAO
 * @author pabula
 * 2016-03-11 00:51:49
 */
public class CompanyDAO {

	/**
	 * 添加公司
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void addCompany(CompanyVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY");

			sh.setInsertForInt("COMPANY_ID",VO.getCOMPANY_ID());//COMPANY_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForString("COMPANY_NAME",VO.getCOMPANY_NAME());//公司名称
			sh.setInsertForString("LOGO",VO.getLOGO());//公司LOGO
			sh.setInsertForString("TYPE",VO.getTYPE());//行业
			sh.setInsertForString("CONTACT_NAME",VO.getCONTACT_NAME());//联系人_姓名
			sh.setInsertForString("CONTACT_JOB",VO.getCONTACT_JOB());//联系人_职位
			sh.setInsertForString("CONTACT_SEX",VO.getCONTACT_SEX());//联系人_性别
			sh.setInsertForString("CONTACT_TEL",VO.getCONTACT_TEL());//联系人_固话
			sh.setInsertForString("CONTACT_MOBILE",VO.getCONTACT_MOBILE());//联系人_手机
			sh.setInsertForString("CONTACT_QQ",VO.getCONTACT_QQ());//联系人_QQ
			sh.setInsertForString("CONTACT_EMAIL",VO.getCONTACT_EMAIL());//联系人_EMAIL
			sh.setInsertForString("WEBSITE",VO.getWEBSITE());//公司网址
			sh.setInsertForString("INTRO",VO.getINTRO());//公司介绍
			sh.setInsertForString("REMARK",VO.getREMARK());//公司备忘
			sh.setInsertForString("TEL",VO.getTEL());//固定电话
			sh.setInsertForString("FAX",VO.getFAX());//传真
			sh.setInsertForString("SERVICE_PHONE",VO.getSERVICE_PHONE());//服务热线
			sh.setInsertForString("ADDR_GUO",VO.getADDR_GUO());//所在地区_国家
			sh.setInsertForString("ADDR_SHENG",VO.getADDR_SHENG());//所在地区_省
			sh.setInsertForString("ADDR_SHI",VO.getADDR_SHI());//所在地区_市
			sh.setInsertForString("ADDR_XIAN",VO.getADDR_XIAN());//所在地区_县
			sh.setInsertForString("ADDR_QU",VO.getADDR_QU());//所在地区_区
			sh.setInsertForString("ADDR",VO.getADDR());//地址_详细地址
			sh.setInsertForString("ADDR_ZIP_CODE",VO.getADDR_ZIP_CODE());//地址_邮编
			sh.setInsertForString("ADDR_EXPRESS_CODE",VO.getADDR_EXPRESS_CODE());//地址_物流编码
			sh.setInsertForString("FINANCE_ACCOUNT_NAME",VO.getFINANCE_ACCOUNT_NAME());//财务_开户名称
			sh.setInsertForString("FINANCE_ACCOUNT_BANK",VO.getFINANCE_ACCOUNT_BANK());//财务_开户银行
			sh.setInsertForString("FINANCE_ACCOUNT_ID",VO.getFINANCE_ACCOUNT_ID());//财务_银行帐号
			sh.setInsertForString("FINANCE_INVOICE_TITLE",VO.getFINANCE_INVOICE_TITLE());//财务_发票抬头
			sh.setInsertForString("FINANCE_INVOICE_ID",VO.getFINANCE_INVOICE_ID());//财务_纳税人识别号
			sh.setInsertForString("FINANCE_INVOICE_ALIPAY",VO.getFINANCE_INVOICE_ALIPAY());//财务_支付宝

			sh.insert(ResourceManager.getConnection(),"添加公司");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改公司
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void modifyCompany(CompanyVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForString("COMPANY_NAME",VO.getCOMPANY_NAME());//公司名称
			sh.setColumnForString("LOGO",VO.getLOGO());//公司LOGO
			sh.setColumnForString("TYPE",VO.getTYPE());//行业
			sh.setColumnForString("CONTACT_NAME",VO.getCONTACT_NAME());//联系人_姓名
			sh.setColumnForString("CONTACT_JOB",VO.getCONTACT_JOB());//联系人_职位
			sh.setColumnForString("CONTACT_SEX",VO.getCONTACT_SEX());//联系人_性别
			sh.setColumnForString("CONTACT_TEL",VO.getCONTACT_TEL());//联系人_固话
			sh.setColumnForString("CONTACT_MOBILE",VO.getCONTACT_MOBILE());//联系人_手机
			sh.setColumnForString("CONTACT_QQ",VO.getCONTACT_QQ());//联系人_QQ
			sh.setColumnForString("CONTACT_EMAIL",VO.getCONTACT_EMAIL());//联系人_EMAIL
			sh.setColumnForString("WEBSITE",VO.getWEBSITE());//公司网址
			sh.setColumnForString("INTRO",VO.getINTRO());//公司介绍
			sh.setColumnForString("REMARK",VO.getREMARK());//公司备忘
			sh.setColumnForString("TEL",VO.getTEL());//固定电话
			sh.setColumnForString("FAX",VO.getFAX());//传真
			sh.setColumnForString("SERVICE_PHONE",VO.getSERVICE_PHONE());//服务热线
			sh.setColumnForString("ADDR_GUO",VO.getADDR_GUO());//所在地区_国家
			sh.setColumnForString("ADDR_SHENG",VO.getADDR_SHENG());//所在地区_省
			sh.setColumnForString("ADDR_SHI",VO.getADDR_SHI());//所在地区_市
			sh.setColumnForString("ADDR_XIAN",VO.getADDR_XIAN());//所在地区_县
			sh.setColumnForString("ADDR_QU",VO.getADDR_QU());//所在地区_区
			sh.setColumnForString("ADDR",VO.getADDR());//地址_详细地址
			sh.setColumnForString("ADDR_ZIP_CODE",VO.getADDR_ZIP_CODE());//地址_邮编
			sh.setColumnForString("ADDR_EXPRESS_CODE",VO.getADDR_EXPRESS_CODE());//地址_物流编码
			sh.setColumnForString("FINANCE_ACCOUNT_NAME",VO.getFINANCE_ACCOUNT_NAME());//财务_开户名称
			sh.setColumnForString("FINANCE_ACCOUNT_BANK",VO.getFINANCE_ACCOUNT_BANK());//财务_开户银行
			sh.setColumnForString("FINANCE_ACCOUNT_ID",VO.getFINANCE_ACCOUNT_ID());//财务_银行帐号
			sh.setColumnForString("FINANCE_INVOICE_TITLE",VO.getFINANCE_INVOICE_TITLE());//财务_发票抬头
			sh.setColumnForString("FINANCE_INVOICE_ID",VO.getFINANCE_INVOICE_ID());//财务_纳税人识别号
			sh.setColumnForString("FINANCE_INVOICE_ALIPAY",VO.getFINANCE_INVOICE_ALIPAY());//财务_支付宝

			sh.setWhereForInt("COMPANY_ID", " = ", VO.getCOMPANY_ID());//COMPANY_ID

			sh.update(ResourceManager.getConnection(), "修改公司");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除公司
	 * @param COMPANY_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void delCompany(int COMPANY_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY");

			sh.setWhereForInt("COMPANY_ID", " = ", COMPANY_ID);//COMPANY_ID

			sh.delete(ResourceManager.getConnection(),"删除公司");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除公司
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void delCompany(Collection coll)throws DataAccessException{
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
				sh.setTable("COMPANY");

				//设置Where的条件
				sh.setWhereForInt("COMPANY_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//COMPANY_ID

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
	 * 根据SQL获取公司集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public Collection getCompanyColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				CompanyVO VO = new CompanyVO();

				VO.setCOMPANY_ID(rs.getInt("COMPANY_ID")); 	//COMPANY_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setCOMPANY_NAME(rs.getString("COMPANY_NAME")); 	//公司名称
				VO.setLOGO(rs.getString("LOGO")); 	//公司LOGO
				VO.setTYPE(rs.getString("TYPE")); 	//行业
				VO.setCONTACT_NAME(rs.getString("CONTACT_NAME")); 	//联系人_姓名
				VO.setCONTACT_JOB(rs.getString("CONTACT_JOB")); 	//联系人_职位
				VO.setCONTACT_SEX(rs.getString("CONTACT_SEX")); 	//联系人_性别
				VO.setCONTACT_TEL(rs.getString("CONTACT_TEL")); 	//联系人_固话
				VO.setCONTACT_MOBILE(rs.getString("CONTACT_MOBILE")); 	//联系人_手机
				VO.setCONTACT_QQ(rs.getString("CONTACT_QQ")); 	//联系人_QQ
				VO.setCONTACT_EMAIL(rs.getString("CONTACT_EMAIL")); 	//联系人_EMAIL
				VO.setWEBSITE(rs.getString("WEBSITE")); 	//公司网址
				VO.setINTRO(rs.getString("INTRO")); 	//公司介绍
				VO.setREMARK(rs.getString("REMARK")); 	//公司备忘
				VO.setTEL(rs.getString("TEL")); 	//固定电话
				VO.setFAX(rs.getString("FAX")); 	//传真
				VO.setSERVICE_PHONE(rs.getString("SERVICE_PHONE")); 	//服务热线
				VO.setADDR_GUO(rs.getString("ADDR_GUO")); 	//所在地区_国家
				VO.setADDR_SHENG(rs.getString("ADDR_SHENG")); 	//所在地区_省
				VO.setADDR_SHI(rs.getString("ADDR_SHI")); 	//所在地区_市
				VO.setADDR_XIAN(rs.getString("ADDR_XIAN")); 	//所在地区_县
				VO.setADDR_QU(rs.getString("ADDR_QU")); 	//所在地区_区
				VO.setADDR(rs.getString("ADDR")); 	//地址_详细地址
				VO.setADDR_ZIP_CODE(rs.getString("ADDR_ZIP_CODE")); 	//地址_邮编
				VO.setADDR_EXPRESS_CODE(rs.getString("ADDR_EXPRESS_CODE")); 	//地址_物流编码
				VO.setFINANCE_ACCOUNT_NAME(rs.getString("FINANCE_ACCOUNT_NAME")); 	//财务_开户名称
				VO.setFINANCE_ACCOUNT_BANK(rs.getString("FINANCE_ACCOUNT_BANK")); 	//财务_开户银行
				VO.setFINANCE_ACCOUNT_ID(rs.getString("FINANCE_ACCOUNT_ID")); 	//财务_银行帐号
				VO.setFINANCE_INVOICE_TITLE(rs.getString("FINANCE_INVOICE_TITLE")); 	//财务_发票抬头
				VO.setFINANCE_INVOICE_ID(rs.getString("FINANCE_INVOICE_ID")); 	//财务_纳税人识别号
				VO.setFINANCE_INVOICE_ALIPAY(rs.getString("FINANCE_INVOICE_ALIPAY")); 	//财务_支付宝

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得公司集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param COMPANY_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public CompanyVO getCompanyByID(int COMPANY_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("COMPANY");

		sh.setWhereForInt("COMPANY_ID", " = ", COMPANY_ID);//COMPANY_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getCompanyColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (CompanyVO)it.next();
		}

		return null;
	}


	/**
	 * 根据SERVICE_ID获得公司信息
	 * @param serviceID
	 * @return
	 * @throws DataAccessException
     */
	public CompanyVO getCompanyByServiceID(int serviceID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("COMPANY");

		sh.setWhereForInt("SERVICE_ID", " = ", serviceID);//serviceID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getCompanyColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (CompanyVO)it.next();
		}

		return null;
	}
}