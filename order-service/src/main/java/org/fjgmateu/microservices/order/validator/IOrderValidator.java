package org.fjgmateu.microservices.order.validator;

import org.fjgmateu.microservices.order.dto.OrderDTO;


/**
 * Created by FJGMATEU
 */
public interface IOrderValidator {

    public void checkOrder(final OrderDTO input);
}
