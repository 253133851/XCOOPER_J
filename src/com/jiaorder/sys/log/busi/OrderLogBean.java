package com.jiaorder.sys.log.busi;

import com.jiaorder.sys.log.dao.OrderLogDAO;
import com.jiaorder.sys.log.vo.OrderLogVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 订单操作日志Bean
 * @author Paula
 * 2016-03-17 13:31:58
 */
public class OrderLogBean {

	Logger log = Logger.getLogger(OrderLogBean.class);
	OrderLogDAO dao;

	public OrderLogBean(){
		dao = new OrderLogDAO();
	}

	/**
	 * 添加订单操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public void addOrderLog(OrderLogVO VO)throws DataAccessException{
		dao.addOrderLog(VO);
	}

	/**
	 * 修改订单操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public void modifyOrderLog(OrderLogVO VO)throws DataAccessException{
		dao.modifyOrderLog(VO);
	}

	/**
	 * 删除订单操作日志
	 * @param ID
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public void delOrderLog(int ID)throws DataAccessException{
		dao.delOrderLog(ID);
	}

	/**
	 * 批量删除订单操作日志
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public void delOrderLog(Collection coll)throws DataAccessException{
		dao.delOrderLog(coll);
	}

	/**
	 * 根据SQL获取订单操作日志集合
	 * @param sql
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public Collection getOrderLogColl(String sql)throws DataAccessException{
		return dao.getOrderLogColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author Paula 2016-03-17 13:31:58
	 */
	public OrderLogVO getOrderLogByID(int ID)throws DataAccessException{
		return dao.getOrderLogByID(ID);
	}
}