package com.pabula.fw.utility;

import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


public interface Command {

  public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException,DataAccessException,SysException;

  //Êý¾Ý¼ìÑé
  public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException;
}
