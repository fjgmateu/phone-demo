package org.fjgmateu.microservices.order.validator;

import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.dto.PhoneDTO;

import java.util.List;


/**
 * Created by FJGMATEU
 */
public interface IOrderValidator {

    public List<PhoneDTO> checkOrder(final OrderDTO input);
}
