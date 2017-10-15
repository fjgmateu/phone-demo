package org.fjgmateu.microservices.order.exception;


/**
 * Created by FJGMATEU
 */
public class ServiceValidationException extends ServiceException {

    private static final long serialVersionUID = -98352873324887177L;

    public ServiceValidationException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(Throwable cause) {
        super(cause);
    }

    public ServiceValidationException(ServiceException e) {
        super(e);
    }

}
