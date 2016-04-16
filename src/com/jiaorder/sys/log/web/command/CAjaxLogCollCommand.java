package com.jiaorder.sys.log.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.log.busi.OperLogBean;
import com.jiaorder.sys.log.vo.OperLogVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 取得日志列表
 * by pabula 2016-03-17 15:43
 */
public class CAjaxLogCollCommand implements Command {


    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }


    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        try {

            /**************************************************************************************************************
             * 构建查询条件
             *************************************************************************************************************/
            SqlHelper sh = new SqlHelper();

            sh.setSelectColumn("*");

            sh.setTable("SYS_OPER_LOG");

            //WHERE 条件区

            //TYPE过滤
            String type = request.getParameter("type");
            if (StrUtil.isNotNull(type) && !type.equalsIgnoreCase("all")) {
                sh.setWhereForString("TYPE", " = ", request.getParameter("type"), true, true);
            }

            //固定要传SERVICE_ID
            sh.setWhereForInt("SERVICE_ID", "=", UserHelper.getServiceID(request));

            //ORDER 区
            sh.setORDER("ID desc");

            //设置分页
            sh.setPAGE(request);


            /**************************************************************************************************************
             * 取得查询结果
             *************************************************************************************************************/

            String sql = sh.getSQL(sh.getSelectSQL());

            List<OperLogVO> coll = new OperLogBean().getOperLogColl(sql);


            /**************************************************************************************************************
             * 包装结果,返回
             *************************************************************************************************************/

            return new Page<>(request, coll, sh, "COUNT(*) AS COUNT").json();

        } catch (DataAccessException e) {
            e.printStackTrace();
            return JsonResultUtil.error();
        }
    }


}
