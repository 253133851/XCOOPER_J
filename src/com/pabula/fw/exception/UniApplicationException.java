/*
 * ������ 2004-10-18 21:03:56
 * JCMS
 */
package com.pabula.fw.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Dekn
 *
 * JCMS ( Content Manager System for java )
 */
public abstract class UniApplicationException extends Exception  {
    Throwable cause = null;

    /**
     *
     */
    public UniApplicationException() {
            super();
    }

    /**
     * @param message
     */
    public UniApplicationException(String message) {
            super(message);
    }

    /**
     * @param cause
     */
    public UniApplicationException(Throwable cause) {
            super(cause);
            this.cause = cause;
    }

    /**
     * @param message
     * @param cause
     */
    public UniApplicationException(String message, Throwable cause) {
            super(message, cause);
            this.cause = cause;
    }

    /**
     * @return
     */
    public Throwable getCause() {
            return cause;
    }

    /**
     * ��ȡ�쳣��Ϣ��������Ƕ���쳣���������Ƕ�׵�toString()ֵ
     * (non-Javadoc)
     * @see Throwable#getMessage()
     */
    public String getMessage() {
            StringBuffer sb = new StringBuffer(super.getMessage());
            if (null != cause) {
                    sb.append("; nested exception is:");
                    sb.append(System.getProperty("line.separator"));
                    sb.append(cause.toString());
            }

            return sb.toString();
    }

    /**
     * ���쳣����(�쳣���ͺ��쳣��Ϣ)ת���ɴ�
     * (non-Javadoc)
     * @see Object#toString()
     */
    public String toString() {
            return getClass().getName() + ": " + this.getMessage();
    }

    /**
     * �ݹ������ջ��������
     * (non-Javadoc)
     * @see Throwable#printStackTrace()
     */
    public void printStackTrace() {
            System.err.println(toString());
            if (null != cause) {
                    cause.printStackTrace();
            }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * *
     * getMessage(),toString(),printStackTrace
     * ÿ����Ϣ����ǰһ����Ϣ��һ���Ӽ�
     * * * * * * * * * * * * * * * * * * * * * * * * */

    /* (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    public void printStackTrace(PrintStream ps) {
            ps.println(toString());
            if (null != cause) {
                    cause.printStackTrace(ps);
            }
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    public void printStackTrace(PrintWriter pw) {
            pw.println(toString());
            if (null != cause) {
                    cause.printStackTrace(pw);
            }
    }

    public abstract String getRealm();
    public abstract String getDefine();

    /**
     * ��ȡ�쳣�׳���ջ��Ϣ������ӳ���쳣������׼ȷλ��
     * @return
     */
    public String getPosition() {
            return StackTraceUtil.getStackTrace(cause);
    }    
}
