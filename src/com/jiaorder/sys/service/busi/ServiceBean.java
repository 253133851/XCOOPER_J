package com.jiaorder.sys.service.busi;

import com.jiaorder.sys.service.dao.ServiceDAO;
import com.jiaorder.sys.service.vo.ServiceVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 服务Bean
 * @author pabula
 * 2016-03-11 00:50:35
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
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void addService(ServiceVO VO)throws DataAccessException {
		dao.addService(VO);
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void modifyService(ServiceVO VO)throws DataAccessException {
		dao.modifyService(VO);
	}

	/**
	 * 修改系统参数(当为空时,则不修改)
	 * @param param
	 * @param paramRemark
	 * @param serviceID
	 * @throws DataAccessException
	 */
	public void modifyServiceParam(String param,String paramRemark,int serviceID)throws DataAccessException {
		if(serviceID <= 0){
			return;
		}

		dao.modifyServiceParam(param,paramRemark,serviceID);
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void delService(int SERVICE_ID)throws DataAccessException {
		dao.delService(SERVICE_ID);
	}

	/**
	 * 批量删除服务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void delService(Collection coll)throws DataAccessException {
		dao.delService(coll);
	}

	/**
	 * 根据SQL获取服务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public Collection getServiceColl(String sql)throws DataAccessException {
		return dao.getServiceColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public ServiceVO getServiceByID(int SERVICE_ID)throws DataAccessException {
		return dao.getServiceByID(SERVICE_ID);
	}
}