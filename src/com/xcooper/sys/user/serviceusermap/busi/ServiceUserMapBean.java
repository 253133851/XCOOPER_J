package com.xcooper.sys.user.serviceusermap.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.serviceusermap.dao.ServiceUserMapDAO;
import com.xcooper.sys.user.serviceusermap.vo.ServiceUserMapVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 服务Bean
 * @author zdk
 * 2016-03-28 19:23:04
 */
public class ServiceUserMapBean {

	Logger log = Logger.getLogger(ServiceUserMapBean.class);
	ServiceUserMapDAO dao;

	public ServiceUserMapBean(){
		dao = new ServiceUserMapDAO();
	}

	/**
	 * 添加服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void addServiceUserMap(ServiceUserMapVO VO)throws DataAccessException{
		dao.addServiceUserMap(VO);
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void modifyServiceUserMap(ServiceUserMapVO VO)throws DataAccessException{
		dao.modifyServiceUserMap(VO);
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void delServiceUserMap(int SERVICE_ID)throws DataAccessException{
		dao.delServiceUserMap(SERVICE_ID);
	}

	/**
	 * 批量删除服务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void delServiceUserMap(Collection coll)throws DataAccessException{
		dao.delServiceUserMap(coll);
	}

	/**
	 * 根据SQL获取服务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public Collection getServiceUserMapColl(String sql)throws DataAccessException{
		return dao.getServiceUserMapColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public ServiceUserMapVO getServiceUserMapByID(int SERVICE_ID)throws DataAccessException{
		return dao.getServiceUserMapByID(SERVICE_ID);
	}
}