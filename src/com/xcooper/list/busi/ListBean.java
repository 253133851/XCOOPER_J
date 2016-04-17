package com.xcooper.list.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.list.dao.ListDAO;
import com.xcooper.list.vo.ListVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 项目清单Bean
 * @author zdk
 * 2016-03-28 19:33:03
 */
public class ListBean {

	Logger log = Logger.getLogger(ListBean.class);
	ListDAO dao;

	public ListBean(){
		dao = new ListDAO();
	}

	/**
	 * 添加项目清单
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void addList(ListVO VO)throws DataAccessException {
		//设置添加时间
		VO.setADD_DATETIME(DateUtil.getCurrTime());
		dao.addList(VO);
	}

	/**
	 * 修改项目清单
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void modifyList(ListVO VO)throws DataAccessException {
		//设置update_datetime
		VO.setUPDATE_DATETIME(DateUtil.getCurrTime());
		dao.modifyList(VO);
	}

	/**
	 * 删除项目清单
	 * @param LIST_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void delList(int LIST_ID)throws DataAccessException {
		dao.delList(LIST_ID);
	}

	/**
	 * 批量删除项目清单
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public void delList(Collection coll)throws DataAccessException {
		dao.delList(coll);
	}

	/**
	 * 根据SQL获取项目清单集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public Collection getListColl(String sql)throws DataAccessException {
		return dao.getListColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param LIST_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:33:03
	 */
	public ListVO getListByID(int LIST_ID)throws DataAccessException {
		return dao.getListByID(LIST_ID);
	}

}