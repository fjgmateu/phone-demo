package org.fjgmateu.microservices.order.repository;

import org.fjgmateu.microservices.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Created by FJGMATEU.
 */

public interface OrderRepository extends MongoRepository<Order, String> {

    public Order findByReference(String reference);
    public List<Order> findAll();

}