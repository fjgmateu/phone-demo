package org.fjgmateu.microservices.order.validator.impl;

import org.fjgmateu.microservices.order.client.impl.PhoneServiceClient;
import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.dto.PhoneDTO;
import org.fjgmateu.microservices.order.exception.ServiceValidationException;
import org.fjgmateu.microservices.order.validator.IOrderValidator;
import org.fjgmateu.microservices.order.validator.ValidatorBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by FJGMATEU
 */
@Service
public class OrderValidator implements IOrderValidator {


    private final Logger logger = LoggerFactory.getLogger(OrderValidator.class);

    @Autowired
    private ValidatorBean validatorBean;

    @Autowired
    private PhoneServiceClient phoneServiceClient;


    public void checkOrder(final OrderDTO input) throws ServiceValidationException {

        validatorBean.validate(input);

        List<PhoneDTO> catalog=phoneServiceClient.findPhones();


        List<PhoneDTO> phonesNotExists= input.getPhones().stream().filter(p1 -> catalog.stream().noneMatch(
                                        p2 -> p2.getReference().equals(p1.getReference())))
                                        .collect(Collectors.toList());


        if (!CollectionUtils.isEmpty(phonesNotExists)){
            throw new ServiceValidationException("Existen teléfonos no existentes en catálogo");
        }

        List<PhoneDTO> phonesNotValidPrice = catalog.stream().filter(p1 -> input.getPhones().stream().noneMatch(
                p2 -> (p2.getReference().equals(p1.getReference())&&(p2.getPrice()!=(p1.getPrice())))))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(phonesNotValidPrice)){
            throw new ServiceValidationException("Existen teléfonos con precios no coincidentes");
        }
    }
}
