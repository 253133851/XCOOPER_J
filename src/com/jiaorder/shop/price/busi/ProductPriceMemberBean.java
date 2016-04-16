package com.jiaorder.shop.price.busi;

import com.jiaorder.shop.price.dao.ProductPriceMemberDAO;
import com.jiaorder.shop.price.vo.ProductPriceMemberVO;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 商品价格会员对应Bean
 * @author sunsai
 * 2016-03-18 14:51:24
 */
public class ProductPriceMemberBean {

	Logger log = Logger.getLogger(ProductPriceMemberBean.class);
	ProductPriceMemberDAO dao;

	public ProductPriceMemberBean(){
		dao = new ProductPriceMemberDAO();
	}

	/**
	 * 添加商品价格会员对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public void addProductPriceMember(ProductPriceMemberVO VO)throws DataAccessException{
		dao.addProductPriceMember(VO);
	}

	/**
	 * 修改商品价格会员对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public void modifyProductPriceMember(ProductPriceMemberVO VO)throws DataAccessException{
		dao.modifyProductPriceMember(VO);
	}

	/**
	 * 删除商品价格会员对应
	 * @param ID
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public void delProductPriceMember(int ID)throws DataAccessException{
		dao.delProductPriceMember(ID);
	}

	/**
	 * 批量删除商品价格会员对应
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public void delProductPriceMember(Collection coll)throws DataAccessException{
		dao.delProductPriceMember(coll);
	}

	/**
	 * 根据SQL获取商品价格会员对应集合
	 * @param sql
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public Collection getProductPriceMemberColl(String sql)throws DataAccessException{
		return dao.getProductPriceMemberColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:51:24
	 */
	public ProductPriceMemberVO getProductPriceMemberByID(int ID)throws DataAccessException{
		return dao.getProductPriceMemberByID(ID);
	}
}