package com.pabula.fw.exception;

/**
 * �޷����������
 */
public class UnAcquiredCommandException extends Exception {

        /**
         * Constructor for UnAcquiredCommanddException
         */
        public UnAcquiredCommandException() {
                super();
        }

        /**
         * Constructor for UnAcquiredCommanddException
         */
        public UnAcquiredCommandException(String exceptionMsg) {
                super(exceptionMsg);
        }

}
