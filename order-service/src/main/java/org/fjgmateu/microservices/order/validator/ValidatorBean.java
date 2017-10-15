package org.fjgmateu.microservices.order.validator;


import org.apache.commons.lang3.StringUtils;
import org.fjgmateu.microservices.order.config.MessagesResourceBundleLocator;
import org.fjgmateu.microservices.order.exception.ServiceValidationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Created by FJGMATEU
 */
public class ValidatorBean {

    private Validator validator;

    public ValidatorBean() {
        this.validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(new MessagesResourceBundleLocator()))
                .buildValidatorFactory().getValidator();
    }

    public <T> void validate(final List<T> listBean)
            throws ServiceValidationException {
        for (Iterator<T> iterator = listBean.iterator(); iterator.hasNext(); ) {
            T t = iterator.next();
            validate(t);
        }
    }

    public <T> void validate(final T bean)
            throws ServiceValidationException {

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);

        String errors = buildErrorMessage(constraintViolations);

        if (StringUtils.isNotEmpty(errors)) {
            throw new ServiceValidationException(errors);
        }
    }

    private <T> String buildErrorMessage(Set<ConstraintViolation<T>> errors) {
        StringBuilder result = new StringBuilder();

        if (errors != null && !errors.isEmpty()) {

            errors.stream().forEach(objectError -> {

                String errorFieldName = ((PathImpl) ((ConstraintViolationImpl) objectError).getPropertyPath()).getLeafNode().getName();

                if (StringUtils.isNotEmpty(errorFieldName)) {
                    result.append(errorFieldName);
                    result.append(" - ");
                }

                result.append(objectError.getMessage());

                if (errors.size() > 0) {
                    result.append(" ## ");
                }
            });
        }

        return result.toString();
    }

}
