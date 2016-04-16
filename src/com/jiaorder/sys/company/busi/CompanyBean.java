package com.jiaorder.sys.company.busi;

import com.jiaorder.sys.company.dao.CompanyDAO;
import com.jiaorder.sys.company.vo.CompanyVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 公司Bean
 * @author pabula
 * 2016-03-11 00:51:49
 */
public class CompanyBean {

	Logger log = Logger.getLogger(CompanyBean.class);
	CompanyDAO dao;

	public CompanyBean(){
		dao = new CompanyDAO();
	}

	/**
	 * 添加公司
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void addCompany(CompanyVO VO)throws DataAccessException {
		dao.addCompany(VO);
	}

	/**
	 * 修改公司
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void modifyCompany(CompanyVO VO)throws DataAccessException{
		dao.modifyCompany(VO);
	}

	/**
	 * 删除公司
	 * @param COMPANY_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void delCompany(int COMPANY_ID)throws DataAccessException{
		dao.delCompany(COMPANY_ID);
	}

	/**
	 * 批量删除公司
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public void delCompany(Collection coll)throws DataAccessException{
		dao.delCompany(coll);
	}

	/**
	 * 根据SQL获取公司集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public Collection getCompanyColl(String sql)throws DataAccessException{
		return dao.getCompanyColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param COMPANY_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:51:49
	 */
	public CompanyVO getCompanyByID(int COMPANY_ID)throws DataAccessException{
		return dao.getCompanyByID(COMPANY_ID);
	}

	/**
	 * 根据SERVICE_ID获得公司信息
	 * @param serviceID
	 * @return
	 * @throws DataAccessException
	 */
	public CompanyVO getCompanyByServiceID(int serviceID)throws DataAccessException{
		return dao.getCompanyByServiceID(serviceID);
	}
}