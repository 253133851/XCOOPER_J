package com.jiaorder.shop.order.busi;

import com.jiaorder.shop.order.dao.ShopOrderDetailDAO;
import com.jiaorder.shop.order.vo.ShopOrderDetailVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 订单详细Bean
 * @author ZDK
 * 2016-03-28 10:12:45
 */
public class ShopOrderDetailBean {

	Logger log = Logger.getLogger(ShopOrderDetailBean.class);
	ShopOrderDetailDAO dao;

	public ShopOrderDetailBean(){
		dao = new ShopOrderDetailDAO();
	}

	/**
	 * 添加订单详细
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void addShopOrderDetail(ShopOrderDetailVO VO)throws DataAccessException{
		dao.addShopOrderDetail(VO);
	}

	/**
	 * 修改订单详细
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void modifyShopOrderDetail(ShopOrderDetailVO VO)throws DataAccessException{
		dao.modifyShopOrderDetail(VO);
	}

	/**
	 * 删除订单详细
	 * @param ORDDER_DETAIL_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void delShopOrderDetail(int ORDDER_DETAIL_ID)throws DataAccessException{
		dao.delShopOrderDetail(ORDDER_DETAIL_ID);
	}

	/**
	 * 批量删除订单详细
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public void delShopOrderDetail(Collection coll)throws DataAccessException{
		dao.delShopOrderDetail(coll);
	}

	/**
	 * 根据SQL获取订单详细集合
	 * @param sql
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public Collection getShopOrderDetailColl(String sql)throws DataAccessException{
		return dao.getShopOrderDetailColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ORDDER_DETAIL_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:12:45
	 */
	public ShopOrderDetailVO getShopOrderDetailByID(int ORDDER_DETAIL_ID)throws DataAccessException{
		return dao.getShopOrderDetailByID(ORDDER_DETAIL_ID);
	}
}