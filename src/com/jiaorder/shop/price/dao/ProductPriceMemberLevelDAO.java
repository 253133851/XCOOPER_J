package com.jiaorder.shop.price.dao;

import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.logger.Logger;
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
 * 商品价格会员等级对应DAO
 * @author sunsai
 * 2016-03-18 14:50:50
 */
public class ProductPriceMemberLevelDAO {

	/**
	 * 添加商品价格会员等级对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void addProductPriceMemberLevel(ProductPriceMemberLevelVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("MEMBER_LEVEL_ID",VO.getMEMBER_LEVEL_ID());//MEMBER_LEVEL_ID
			sh.setInsertForInt("SKU_ID",VO.getSKU_ID());//SKU_ID
			sh.setInsertForInt("CAN_BUY",VO.getCAN_BUY());//允许订货
			sh.setInsertForInt("PRICE",VO.getPRICE());//订货价
			sh.setInsertForInt("MIN_NUM",VO.getMIN_NUM());//起定量

			Logger.d(sh.getSQL(sh.getInsertSQL()));

			sh.insert(ResourceManager.getConnection(),"添加商品价格会员等级对应");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改商品价格会员等级对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void modifyProductPriceMemberLevel(ProductPriceMemberLevelVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("MEMBER_LEVEL_ID",VO.getMEMBER_LEVEL_ID());//MEMBER_LEVEL_ID
			sh.setColumnForInt("SKU_ID",VO.getSKU_ID());//SKU_ID
			sh.setColumnForInt("CAN_BUY",VO.getCAN_BUY());//允许订货
			sh.setColumnForInt("PRICE",VO.getPRICE());//订货价
			sh.setColumnForInt("MIN_NUM",VO.getMIN_NUM());//起定量

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改商品价格会员等级对应");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除商品价格会员等级对应
	 * @param ID
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void delProductPriceMemberLevel(int ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除商品价格会员等级对应");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除商品价格会员等级对应
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void delProductPriceMemberLevel(Collection coll)throws DataAccessException{
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
				sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");

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
	 * 根据SQL获取商品价格会员等级对应集合
	 * @param sql
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public List<ProductPriceMemberLevelVO> getProductPriceMemberLevelColl(String sql)throws DataAccessException{

		List<ProductPriceMemberLevelVO> resultList = new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ProductPriceMemberLevelVO VO = new ProductPriceMemberLevelVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setMEMBER_LEVEL_ID(rs.getInt("MEMBER_LEVEL_ID")); 	//MEMBER_LEVEL_ID
				VO.setSKU_ID(rs.getInt("SKU_ID")); 	//SKU_ID
				VO.setCAN_BUY(rs.getInt("CAN_BUY")); 	//允许订货
				VO.setPRICE(rs.getInt("PRICE")); 	//订货价
				VO.setMIN_NUM(rs.getInt("MIN_NUM")); 	//起定量

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得商品价格会员等级对应集合", e);
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
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public ProductPriceMemberLevelVO getProductPriceMemberLevelByID(int ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getProductPriceMemberLevelColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ProductPriceMemberLevelVO)it.next();
		}

		return null;
	}



}