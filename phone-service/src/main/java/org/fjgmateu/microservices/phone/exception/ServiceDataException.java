package org.fjgmateu.microservices.phone.exception;


/**
 * Created by FJGMATEU
 */
public class ServiceDataException extends ServiceException {


    private static final long serialVersionUID = 2460571914907416141L;

    public ServiceDataException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDataException(String message) {
        super(message);
    }

    public ServiceDataException(Throwable cause) {
        super(cause);
    }

    public ServiceDataException(ServiceException e) {
        super(e);
    }
}
