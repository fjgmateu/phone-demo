package org.fjgmateu.microservices.phone.exception;


/**
 * Created by FJGMATEU
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -2095717599642667301L;



    public ServiceException(ServiceException e) {
        super(e);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
     }
	
}
