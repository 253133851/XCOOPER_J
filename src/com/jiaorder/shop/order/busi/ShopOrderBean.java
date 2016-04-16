package com.jiaorder.shop.order.busi;

import com.jiaorder.shop.order.dao.ShopOrderDAO;
import com.jiaorder.shop.order.vo.ShopOrderVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 订单Bean
 * @author ZDK
 * 2016-03-28 10:11:10
 */
public class ShopOrderBean {

	Logger log = Logger.getLogger(ShopOrderBean.class);
	ShopOrderDAO dao;

	public ShopOrderBean(){
		dao = new ShopOrderDAO();
	}

	/**
	 * 添加订单
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void addShopOrder(ShopOrderVO VO)throws DataAccessException {
		dao.addShopOrder(VO);
	}

	/**
	 * 修改订单
	 * @param VO
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void modifyShopOrder(ShopOrderVO VO)throws DataAccessException {
		dao.modifyShopOrder(VO);
	}

	/**
	 * 删除订单
	 * @param ORDER_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void delShopOrder(int ORDER_ID)throws DataAccessException {
		dao.delShopOrder(ORDER_ID);
	}

	/**
	 * 批量删除订单
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public void delShopOrder(Collection coll)throws DataAccessException {
		dao.delShopOrder(coll);
	}

	/**
	 * 根据SQL获取订单集合
	 * @param sql
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public Collection getShopOrderColl(String sql)throws DataAccessException {
		return dao.getShopOrderColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ORDER_ID
	 * @throws DataAccessException
	 * @author ZDK 2016-03-28 10:11:10
	 */
	public ShopOrderVO getShopOrderByID(int ORDER_ID)throws DataAccessException {
		return dao.getShopOrderByID(ORDER_ID);
	}
}