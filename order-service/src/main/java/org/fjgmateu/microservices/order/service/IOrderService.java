package org.fjgmateu.microservices.order.service;

import org.fjgmateu.microservices.order.dto.OrderDTO;
import java.util.List;

/**
 * Created by FJGMATEU
 */
public interface IOrderService {

        public OrderDTO find (final String reference);

        public List<OrderDTO> findAll () ;

        public String add(OrderDTO input) ;
    }
