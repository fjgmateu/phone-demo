package org.fjgmateu.microservices.order.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.fjgmateu.microservices.order.dto.OrderDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/***
 * FJGMATEU
 */
@XmlRootElement(name = "orders")
@JsonRootName("orders")
public class ResponseListWrapper {

    @JsonIgnoreProperties("orders")
    private List<OrderDTO> order;

    public ResponseListWrapper() {
    }

    public ResponseListWrapper(List<OrderDTO> order) {
        this.order = order;
    }

    public List<OrderDTO> getOrder() {
        return order;
    }

    public void setOrder(List<OrderDTO> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ResponseListWrapper{" +
                "order=" + order +
                '}';
    }
}
