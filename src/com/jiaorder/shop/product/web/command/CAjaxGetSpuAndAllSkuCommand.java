package com.jiaorder.shop.product.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.product.vo.ProductJson;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.logger.Logger;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunsai on 2016/3/21 - 11:15.
 */
public class CAjaxGetSpuAndAllSkuCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException {

    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"),0);
    int serviceId = UserHelper.getServiceID(request);

    SqlHelper sh = new SqlHelper();

    sh.setTable("SHOP_PRODUCT");
    sh.setSelectColumn("*");
    sh.setWhereForInt("SERVICE_ID", "=", serviceId);
    sh.setWhereForInt("PRD_ID", "=", prdId);

    String sql = sh.getSQL(sh.getSelectSQL());

    List<ProductJson> resultList = ProductBean.newInstance().getProductJsonCollBySql(serviceId, sql);

    Page<ProductJson> page = new Page<>();
    page.setItems(resultList);

    return JsonResultUtil.instance().addData(page).json();
  }


  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

  }


  public static void main(String[] args) throws DataAccessException{
    int prdId = 1029;
    int serviceId = 1015;

    List<ProductSkuVO> skuVOList = ProductSkuBean.newInstance().getSkuCollByProductIDS(serviceId, String.valueOf(prdId));

    ProductVO productVO = ProductBean.newInstance().getProductVOById(serviceId, prdId);

    List<ProductJson> resultList = ProductBean.newInstance().getProductJsonBySpuAndSkus(productVO, skuVOList);

    String json = JsonResultUtil.instance().addData(resultList).json();

    Logger.d(json);
  }

}
