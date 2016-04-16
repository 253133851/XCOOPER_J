package com.xcooper.tomato.busi;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.tomato.dao.TomatoDAO;
import com.xcooper.tomato.vo.TomatoVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 番茄钟Bean
 * @author zdk
 * 2016-03-28 19:42:34
 */
public class TomatoBean {

	Logger log = Logger.getLogger(TomatoBean.class);
	TomatoDAO dao;

	public TomatoBean(){
		dao = new TomatoDAO();
	}

	/**
	 * 添加番茄钟
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void addTomato(TomatoVO VO)throws DataAccessException {
		dao.addTomato(VO);
	}

	/**
	 * 修改番茄钟
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void modifyTomato(TomatoVO VO)throws DataAccessException {
		dao.modifyTomato(VO);
	}

	/**
	 * 删除番茄钟
	 * @param TOMATO_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void delTomato(int TOMATO_ID)throws DataAccessException {
		dao.delTomato(TOMATO_ID);
	}

	/**
	 * 批量删除番茄钟
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public void delTomato(Collection coll)throws DataAccessException {
		dao.delTomato(coll);
	}

	/**
	 * 根据SQL获取番茄钟集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public Collection getTomatoColl(String sql)throws DataAccessException {
		return dao.getTomatoColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param TOMATO_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:42:34
	 */
	public TomatoVO getTomatoByID(int TOMATO_ID)throws DataAccessException {
		return dao.getTomatoByID(TOMATO_ID);
	}
}