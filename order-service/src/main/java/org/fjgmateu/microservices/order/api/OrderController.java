package org.fjgmateu.microservices.order.api;

import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.exception.ServiceDataException;
import org.fjgmateu.microservices.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by FJGMATEU
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @CrossOrigin
    @RequestMapping(value = "/order/{reference}", method = RequestMethod.GET)
    public OrderDTO find(@PathVariable("reference") String reference) {
        OrderDTO order=orderService.find(reference);
        if (order==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return order;
    }


    @CrossOrigin
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderDTO> findAll() {
        List<OrderDTO>  orders= orderService.findAll();
        if (CollectionUtils.isEmpty(orders)){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return orders;
    }

    @CrossOrigin
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody OrderDTO input) {
        String reference=orderService.add(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/order/{id}")
                .buildAndExpand(reference).toUri();

        return ResponseEntity.created(location).build();
    }


}
