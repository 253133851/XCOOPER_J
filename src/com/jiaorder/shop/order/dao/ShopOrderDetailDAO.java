package com.jiaorder.shop.order.dao;

import com.jiaorder.shop.order.vo.ShopOrderDetailVO;
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
 * 订单详细DAO
 * @author ZDK
 * 2016-03-28 10:12:45
 */
public class ShopOrderDetailDAO {

	/**
	 * 添加订单详细
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void addShopOrderDetail(ShopOrderDetailVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER_DETAIL");

			sh.setInsertForInt("ORDER_DETAIL_ID",VO.getORDER_DETAIL_ID());//ORDDER_DETAIL_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("ORDER_ID",VO.getORDER_ID());//ORDER_ID
			sh.setInsertForInt("PRD_ID",VO.getPRD_ID());//PRD_ID
			sh.setInsertForInt("PROMOTION_ID",VO.getPROMOTION_ID());//促销id
			sh.setInsertForString("PRD_SPU",VO.getPRD_SPU());//PRD_SPU
			sh.setInsertForString("PRD_SKU",VO.getPRD_SKU());//PRD_SKU
			sh.setInsertForString("PRD_NAME",VO.getPRD_NAME());//PRD_NAME
			sh.setInsertForString("PRD_OTHER_NAME",VO.getPRD_OTHER_NAME());//商品别名
			sh.setInsertForString("SKU_NAME1",VO.getSKU_NAME1());//规格名1
			sh.setInsertForString("SKU_CONTENT1",VO.getSKU_CONTENT1());//规格内容1
			sh.setInsertForString("SKU_NAME2",VO.getSKU_NAME2());//规格名2
			sh.setInsertForString("SKU_CONTENT2",VO.getSKU_CONTENT2());//规格内容2
			sh.setInsertForString("SKU_NAME3",VO.getSKU_NAME3());//规格名3
			sh.setInsertForString("SKU_CONTENT3",VO.getSKU_CONTENT3());//规格内容3
			sh.setInsertForString("REMARK",VO.getREMARK());//备注
			sh.setInsertForString("CLASS",VO.getCLASS());//分类
			sh.setInsertForString("UNIT",VO.getUNIT());//单位
			sh.setInsertForString("TAG",VO.getTAG());//标签
			sh.setInsertForString("BRAND",VO.getBRAND());//品牌
			sh.setInsertForString("IMG",VO.getIMG());//缩略图
			sh.setInsertForString("BAR_CODE",VO.getBAR_CODE());//条形码
			sh.setInsertForInt("PRICE",VO.getPRICE());//市场价
			sh.setInsertForInt("COST",VO.getCOST());//成本价
			sh.setInsertForInt("DISCOUNT_PRICE",VO.getDISCOUNT_PRICE());//折扣价
			sh.setInsertForInt("BUY_COUNT",VO.getBUY_COUNT());//购买数量
			sh.setInsertForInt("TYPE",VO.getTYPE());//商品类型

			sh.insert(ResourceManager.getConnection(),"添加订单详细");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改订单详细
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void modifyShopOrderDetail(ShopOrderDetailVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER_DETAIL");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("ORDER_ID",VO.getORDER_ID());//ORDER_ID
			sh.setColumnForInt("PRD_ID",VO.getPRD_ID());//PRD_ID
			sh.setColumnForInt("PROMOTION_ID",VO.getPROMOTION_ID());//促销id
			sh.setColumnForString("PRD_SPU",VO.getPRD_SPU());//PRD_SPU
			sh.setColumnForString("PRD_SKU",VO.getPRD_SKU());//PRD_SKU
			sh.setColumnForString("PRD_NAME",VO.getPRD_NAME());//PRD_NAME
			sh.setColumnForString("PRD_OTHER_NAME",VO.getPRD_OTHER_NAME());//商品别名
			sh.setColumnForString("SKU_NAME1",VO.getSKU_NAME1());//规格名1
			sh.setColumnForString("SKU_CONTENT1",VO.getSKU_CONTENT1());//规格内容1
			sh.setColumnForString("SKU_NAME2",VO.getSKU_NAME2());//规格名2
			sh.setColumnForString("SKU_CONTENT2",VO.getSKU_CONTENT2());//规格内容2
			sh.setColumnForString("SKU_NAME3",VO.getSKU_NAME3());//规格名3
			sh.setColumnForString("SKU_CONTENT3",VO.getSKU_CONTENT3());//规格内容3
			sh.setColumnForString("REMARK",VO.getREMARK());//备注
			sh.setColumnForString("CLASS",VO.getCLASS());//分类
			sh.setColumnForString("UNIT",VO.getUNIT());//单位
			sh.setColumnForString("TAG",VO.getTAG());//标签
			sh.setColumnForString("BRAND",VO.getBRAND());//品牌
			sh.setColumnForString("IMG",VO.getIMG());//缩略图
			sh.setColumnForString("BAR_CODE",VO.getBAR_CODE());//条形码
			sh.setColumnForInt("PRICE",VO.getPRICE());//市场价
			sh.setColumnForInt("COST",VO.getCOST());//成本价
			sh.setColumnForInt("DISCOUNT_PRICE",VO.getDISCOUNT_PRICE());//折扣价
			sh.setColumnForInt("BUY_COUNT",VO.getBUY_COUNT());//购买数量
			sh.setColumnForInt("TYPE",VO.getTYPE());//商品类型

			sh.setWhereForInt("ORDER_DETAIL_ID", " = ", VO.getORDER_DETAIL_ID());//ORDDER_DETAIL_ID

			sh.update(ResourceManager.getConnection(),"修改订单详细");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除订单详细
	 * @param ORDDER_DETAIL_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void delShopOrderDetail(int ORDDER_DETAIL_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SHOP_ORDER_DETAIL");

			sh.setWhereForInt("ORDDER_DETAIL_ID", " = ", ORDDER_DETAIL_ID);//ORDDER_DETAIL_ID

			sh.delete(ResourceManager.getConnection(),"删除订单详细");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除订单详细
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void delShopOrderDetail(Collection coll)throws DataAccessException{
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
				sh.setTable("SHOP_ORDER_DETAIL");

				//设置Where的条件
				sh.setWhereForInt("ORDER_DETAIL_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//ORDER_DETAIL_ID

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
	 * 根据SQL获取订单详细集合
	 * @param sql
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public Collection getShopOrderDetailColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ShopOrderDetailVO VO = new ShopOrderDetailVO();

				VO.setORDER_DETAIL_ID(rs.getInt("ORDER_DETAIL_ID")); 	//ORDDER_DETAIL_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setORDER_ID(rs.getInt("ORDER_ID")); 	//ORDER_ID
				VO.setPRD_ID(rs.getInt("PRD_ID")); 	//PRD_ID
				VO.setPROMOTION_ID(rs.getInt("PROMOTION_ID")); 	//促销id
				VO.setPRD_SPU(rs.getString("PRD_SPU")); 	//PRD_SPU
				VO.setPRD_SKU(rs.getString("PRD_SKU")); 	//PRD_SKU
				VO.setPRD_NAME(rs.getString("PRD_NAME")); 	//PRD_NAME
				VO.setPRD_OTHER_NAME(rs.getString("PRD_OTHER_NAME")); 	//商品别名
				VO.setSKU_NAME1(rs.getString("SKU_NAME1")); 	//规格名1
				VO.setSKU_CONTENT1(rs.getString("SKU_CONTENT1")); 	//规格内容1
				VO.setSKU_NAME2(rs.getString("SKU_NAME2")); 	//规格名2
				VO.setSKU_CONTENT2(rs.getString("SKU_CONTENT2")); 	//规格内容2
				VO.setSKU_NAME3(rs.getString("SKU_NAME3")); 	//规格名3
				VO.setSKU_CONTENT3(rs.getString("SKU_CONTENT3")); 	//规格内容3
				VO.setREMARK(rs.getString("REMARK")); 	//备注
				VO.setCLASS(rs.getString("CLASS")); 	//分类
				VO.setUNIT(rs.getString("UNIT")); 	//单位
				VO.setTAG(rs.getString("TAG")); 	//标签
				VO.setBRAND(rs.getString("BRAND")); 	//品牌
				VO.setIMG(rs.getString("IMG")); 	//缩略图
				VO.setBAR_CODE(rs.getString("BAR_CODE")); 	//条形码
				VO.setPRICE(rs.getInt("PRICE")); 	//市场价
				VO.setCOST(rs.getInt("COST")); 	//成本价
				VO.setDISCOUNT_PRICE(rs.getInt("DISCOUNT_PRICE")); 	//折扣价
				VO.setBUY_COUNT(rs.getInt("BUY_COUNT")); 	//购买数量
				VO.setTYPE(rs.getInt("TYPE")); 	//商品类型

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得订单详细集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param ORDER_DETAIL_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public ShopOrderDetailVO getShopOrderDetailByID(int ORDER_DETAIL_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SHOP_ORDER_DETAIL");

		sh.setWhereForInt("ORDER_DETAIL_ID", " = ", ORDER_DETAIL_ID);//ORDDER_DETAIL_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getShopOrderDetailColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ShopOrderDetailVO)it.next();
		}

		return null;
	}
}