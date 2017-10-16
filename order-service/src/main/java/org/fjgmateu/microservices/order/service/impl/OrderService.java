package org.fjgmateu.microservices.order.service.impl;

import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.entity.Order;
import org.fjgmateu.microservices.order.exception.ServiceDataException;
import org.fjgmateu.microservices.order.repository.OrderRepository;
import org.fjgmateu.microservices.order.service.IOrderService;
import org.fjgmateu.microservices.order.validator.IOrderValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * Created by FJGMATEU
 */
@Service
public class OrderService implements IOrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private IOrderValidator orderValidator;

    public OrderDTO find (final String reference) {
        logger.info("OrderService.findAll, reference: " + reference);

        Order order=orderRepository.findByReference(reference);
        if (order==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> findAll () {
       logger.info("OrderService.findAll");
       List<Order> orders= orderRepository.findAll();
        if (CollectionUtils
                .isEmpty(orders)){
            throw new ServiceDataException("No se han encontrado datos");
        }

        return orders.stream()
                .map(order  -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }


    public String add(OrderDTO input) {
        logger.info("OrderService.add, input: " + input);
        input.setPhone(orderValidator.checkOrder(input));
        Order order=modelMapper.map(input, Order.class);
        order.setPrice(input.getPhone().stream().mapToDouble(p -> p.getPrice()).sum());
        orderRepository.save(order);
        logger.info("Orden creada, reference: " + order.getReference()+", precio total:"+order.getPrice());
        return order.getReference();
    }
}
