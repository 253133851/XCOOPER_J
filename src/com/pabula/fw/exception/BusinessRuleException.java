/*
 * ´´½¨ÓÚ 2004-10-18 21:06:38
 * JCMS
 */
package com.pabula.fw.exception;

/**
 * @author Dekn
 *
 * JCMS ( Content Manager System for java )
 */
public class BusinessRuleException extends UniApplicationException {

    /**
    *
    */
   public BusinessRuleException() {
           super();
           // TODO Auto-generated constructor stub
   }

   /**
    * @param message
    */
   public BusinessRuleException(String message) {
           super(message);
           // TODO Auto-generated constructor stub
   }

   /**
    * @param cause
    */
   public BusinessRuleException(Throwable cause) {
           super(cause);
           // TODO Auto-generated constructor stub
   }

   /**
    * @param message
    * @param cause
    */
   public BusinessRuleException(String message, Throwable cause) {
           super(message, cause);
           // TODO Auto-generated constructor stub
   }

   /* (non-Javadoc)
    * @see cn.com.netkiss.exception.UniApplicationException#getDefine()
    */
   public String getDefine() {
           // TODO Auto-generated method stub
           return null;
   }

   /* (non-Javadoc)
    * @see cn.com.netkiss.exception.UniApplicationException#getRealm()
    */
   public String getRealm() {
           // TODO Auto-generated method stub
           return null;
   }


}
