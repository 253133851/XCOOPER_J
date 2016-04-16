package com.jiaorder.shop.order.dao;

import com.jiaorder.shop.order.vo.ShopOrderVO;
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

/**
 * 订单DAO
 * @author ZDK
 * 2016-03-28 10:11:10
 */
public class ShopOrderDAO {

	/**
	 * 添加订单
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void addShopOrder(ShopOrderVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER");

			sh.setInsertForInt("ORDER_ID",VO.getORDER_ID());//ORDER_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//会员id
			sh.setInsertForInt("DELIVERY_ADDRESS_ID",VO.getDELIVERY_ADDRESS_ID());//收货地址id
			sh.setInsertForString("USER_NAME",VO.getUSER_NAME());//会员姓名
			sh.setInsertForString("ORDER_NUM",VO.getORDER_NUM());//订单编号
			sh.setInsertForInt("TYPE",VO.getTYPE());//订单类型
			sh.setInsertForInt("ORDER_STATE",VO.getORDER_STATE());//订单状态
			sh.setInsertForInt("STATE",VO.getSTATE());//操作状态
			sh.setInsertForInt("REPOS_STATE",VO.getREPOS_STATE());//出库状态
			sh.setInsertForInt("DELIVERY_STATE",VO.getDELIVERY_STATE());//发货状态
			sh.setInsertForInt("MONEY_STATE",VO.getMONEY_STATE());//收款状态
			sh.setInsertForString("CANCEL_REASON",VO.getCANCEL_REASON());//作废原因
			sh.setInsertForString("LEVEL_NAME",VO.getLEVEL_NAME());//会员级别名称
			sh.setInsertForInt("PRICE_OFF",VO.getPRICE_OFF());//折扣
			sh.setInsertForInt("IS_SALE",VO.getIS_SALE());//是否特价
			sh.setInsertForString("SALE_REASON",VO.getSALE_REASON());//特价申请原因
			sh.setInsertForString("SALE_PERSONAL",VO.getSALE_PERSONAL());//特价批准人
			sh.setInsertForInt("SALE_PRICE",VO.getSALE_PRICE());//特价金额
			sh.setInsertForInt("SUM_PRICE",VO.getSUM_PRICE());//合计金额
			sh.setInsertForInt("OVER_PRICE",VO.getOVER_PRICE());//应付总额
			sh.setInsertForString("PAY_TYPE",VO.getPAY_TYPE());//支付方式
			sh.setInsertForString("REMARK",VO.getREMARK());//备注
			sh.setInsertForDatetime("DELIVERY_DATETIME", DateUtil.format(VO.getDELIVERY_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//交货日期
			sh.setInsertForInt("INVOICE_TYPE",VO.getINVOICE_TYPE());//发票类型
			sh.setInsertForString("FINANCE_INVOICE_TITLE",VO.getFINANCE_INVOICE_TITLE());//发票抬头
			sh.setInsertForString("FINANCE_INVOICE_CONTENT",VO.getFINANCE_INVOICE_CONTENT());//发票内容
			sh.setInsertForString("SIGN1",VO.getSIGN1());//标记1
			sh.setInsertForString("SIGN2",VO.getSIGN2());//标记2

			sh.insert(ResourceManager.getConnection(),"添加订单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改订单
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void modifyShopOrder(ShopOrderVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("MEMBER_ID",VO.getMEMBER_ID());//会员id
			sh.setColumnForInt("DELIVERY_ADDRESS_ID",VO.getDELIVERY_ADDRESS_ID());//收货地址id
			sh.setColumnForString("USER_NAME",VO.getUSER_NAME());//会员姓名
			sh.setColumnForString("ORDER_NUM",VO.getORDER_NUM());//订单编号
			sh.setColumnForInt("TYPE",VO.getTYPE());//订单类型
			sh.setColumnForInt("ORDER_STATE",VO.getORDER_STATE());//订单状态
			sh.setColumnForInt("STATE",VO.getSTATE());//操作状态
			sh.setColumnForInt("REPOS_STATE",VO.getREPOS_STATE());//出库状态
			sh.setColumnForInt("DELIVERY_STATE",VO.getDELIVERY_STATE());//发货状态
			sh.setColumnForInt("MONEY_STATE",VO.getMONEY_STATE());//收款状态
			sh.setColumnForString("CANCEL_REASON",VO.getCANCEL_REASON());//作废原因
			sh.setColumnForString("LEVEL_NAME",VO.getLEVEL_NAME());//会员级别名称
			sh.setColumnForInt("PRICE_OFF",VO.getPRICE_OFF());//折扣
			sh.setColumnForInt("IS_SALE",VO.getIS_SALE());//是否特价
			sh.setColumnForString("SALE_REASON",VO.getSALE_REASON());//特价申请原因
			sh.setColumnForString("SALE_PERSONAL",VO.getSALE_PERSONAL());//特价批准人
			sh.setColumnForInt("SALE_PRICE",VO.getSALE_PRICE());//特价金额
			sh.setColumnForInt("SUM_PRICE",VO.getSUM_PRICE());//合计金额
			sh.setColumnForInt("OVER_PRICE",VO.getOVER_PRICE());//应付总额
			sh.setColumnForString("PAY_TYPE",VO.getPAY_TYPE());//支付方式
			sh.setColumnForString("REMARK",VO.getREMARK());//备注
			sh.setColumnForDatetime("DELIVERY_DATETIME", DateUtil.format(VO.getDELIVERY_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//交货日期
			sh.setColumnForInt("INVOICE_TYPE",VO.getINVOICE_TYPE());//发票类型
			sh.setColumnForString("FINANCE_INVOICE_TITLE",VO.getFINANCE_INVOICE_TITLE());//发票抬头
			sh.setColumnForString("FINANCE_INVOICE_CONTENT",VO.getFINANCE_INVOICE_CONTENT());//发票内容
			sh.setColumnForString("SIGN1",VO.getSIGN1());//标记1
			sh.setColumnForString("SIGN2",VO.getSIGN2());//标记2

			sh.setWhereForInt("ORDER_ID", " = ", VO.getORDER_ID());//ORDER_ID

			sh.update(ResourceManager.getConnection(),"修改订单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除订单
	 * @param ORDER_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void delShopOrder(int ORDER_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER");

			sh.setWhereForInt("ORDER_ID", " = ", ORDER_ID);//ORDER_ID

			sh.delete(ResourceManager.getConnection(),"删除订单");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除订单
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void delShopOrder(Collection coll)throws DataAccessException{
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
				sh.setTable("SHOP_ORDER");

				//设置Where的条件
				sh.setWhereForInt("ORDER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//ORDER_ID

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
	 * 根据SQL获取订单集合
	 * @param sql
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public Collection getShopOrderColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ShopOrderVO VO = new ShopOrderVO();

				VO.setORDER_ID(rs.getInt("ORDER_ID")); 	//ORDER_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//会员id
				VO.setDELIVERY_ADDRESS_ID(rs.getInt("DELIVERY_ADDRESS_ID")); 	//收货地址id
				VO.setUSER_NAME(rs.getString("USER_NAME")); 	//会员姓名
				VO.setORDER_NUM(rs.getString("ORDER_NUM")); 	//订单编号
				VO.setTYPE(rs.getInt("TYPE")); 	//订单类型
				VO.setORDER_STATE(rs.getInt("ORDER_STATE")); 	//订单状态
				VO.setSTATE(rs.getInt("STATE")); 	//操作状态
				VO.setREPOS_STATE(rs.getInt("REPOS_STATE")); 	//出库状态
				VO.setDELIVERY_STATE(rs.getInt("DELIVERY_STATE")); 	//发货状态
				VO.setMONEY_STATE(rs.getInt("MONEY_STATE")); 	//收款状态
				VO.setCANCEL_REASON(rs.getString("CANCEL_REASON")); 	//作废原因
				VO.setLEVEL_NAME(rs.getString("LEVEL_NAME")); 	//会员级别名称
				VO.setPRICE_OFF(rs.getInt("PRICE_OFF")); 	//折扣
				VO.setIS_SALE(rs.getInt("IS_SALE")); 	//是否特价
				VO.setSALE_REASON(rs.getString("SALE_REASON")); 	//特价申请原因
				VO.setSALE_PERSONAL(rs.getString("SALE_PERSONAL")); 	//特价批准人
				VO.setSALE_PRICE(rs.getInt("SALE_PRICE")); 	//特价金额
				VO.setSUM_PRICE(rs.getInt("SUM_PRICE")); 	//合计金额
				VO.setOVER_PRICE(rs.getInt("OVER_PRICE")); 	//应付总额
				VO.setPAY_TYPE(rs.getString("PAY_TYPE")); 	//支付方式
				VO.setREMARK(rs.getString("REMARK")); 	//备注
				VO.setDELIVERY_DATETIME(rs.getTimestamp("DELIVERY_DATETIME")); 	//交货日期
				VO.setINVOICE_TYPE(rs.getInt("INVOICE_TYPE")); 	//发票类型
				VO.setFINANCE_INVOICE_TITLE(rs.getString("FINANCE_INVOICE_TITLE")); 	//发票抬头
				VO.setFINANCE_INVOICE_CONTENT(rs.getString("FINANCE_INVOICE_CONTENT")); 	//发票内容
				VO.setSIGN1(rs.getString("SIGN1")); 	//标记1
				VO.setSIGN2(rs.getString("SIGN2")); 	//标记2

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得订单集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param ORDER_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public ShopOrderVO getShopOrderByID(int ORDER_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SHOP_ORDER");

		sh.setWhereForInt("ORDER_ID", " = ", ORDER_ID);//ORDER_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getShopOrderColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ShopOrderVO)it.next();
		}

		return null;
	}
}