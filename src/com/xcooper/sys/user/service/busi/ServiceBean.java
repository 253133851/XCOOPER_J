package com.xcooper.sys.user.service.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.service.dao.ServiceDAO;
import com.xcooper.sys.user.service.vo.ServiceVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 服务Bean
 * @author zdk
 * 2016-03-28 19:22:33
 */
public class ServiceBean {

	Logger log = Logger.getLogger(ServiceBean.class);
	ServiceDAO dao;

	public ServiceBean(){
		dao = new ServiceDAO();
	}

	/**
	 * 添加服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void addService(ServiceVO VO)throws DataAccessException{
		dao.addService(VO);
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void modifyService(ServiceVO VO)throws DataAccessException{
		dao.modifyService(VO);
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void delService(int SERVICE_ID)throws DataAccessException{
		dao.delService(SERVICE_ID);
	}

	/**
	 * 批量删除服务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void delService(Collection coll)throws DataAccessException{
		dao.delService(coll);
	}

	/**
	 * 根据SQL获取服务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public Collection getServiceColl(String sql)throws DataAccessException{
		return dao.getServiceColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public ServiceVO getServiceByID(int SERVICE_ID)throws DataAccessException{
		return dao.getServiceByID(SERVICE_ID);
	}
}