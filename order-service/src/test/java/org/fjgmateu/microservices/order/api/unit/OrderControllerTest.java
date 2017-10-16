package org.fjgmateu.microservices.order.api.unit;


import org.fjgmateu.microservices.order.api.OrderController;
import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.dto.PhoneDTO;
import org.fjgmateu.microservices.order.repository.OrderRepository;
import org.fjgmateu.microservices.order.service.impl.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by FJGMATEU
 */


@RunWith(SpringRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;


    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(orderController)
                .build();
    }

    @Test
    public void givenOrder_whenGetOrder_thenReturnJson()
          throws Exception {

        final String reference="referencia 1";


        OrderDTO order = new OrderDTO();
        order.setReference(reference);
        order.setName("Nombre cliente 1");
        order.setSurname("Apellido cliente 1");
        order.setEmail("Email cliente 1");

        List<PhoneDTO> phones=new ArrayList<PhoneDTO>();
        PhoneDTO phone=new PhoneDTO();
        phone.setReference("ABCD123456");
        phones.add(phone);

        order.setPhone(phones);

        when(orderService.find(reference)).thenReturn(order);

        mockMvc.perform(get("/order/"+reference)
                          .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.reference").value(reference));

    }


    @Test
    public void givenOrders_whenGetOrders_thenReturnJsonArray()
          throws Exception {

        final String reference="referencia 1";

        List<OrderDTO> allOrders=new ArrayList<OrderDTO>();
        OrderDTO order = new OrderDTO();
        order.setReference(reference);
        order.setName("Nombre cliente 1");
        order.setSurname("Apellido cliente 1");
        order.setEmail("Email cliente 1");

        List<PhoneDTO> phones=new ArrayList<PhoneDTO>();
        PhoneDTO phone=new PhoneDTO();
        phone.setReference("ABCD123456");
        phones.add(phone);

        phone=new PhoneDTO();
        phone.setReference("ABCD123418");
        phones.add(phone);

        phone=new PhoneDTO();
        phone.setReference("ABCD123419");
        phones.add(phone);

        order.setPhone(phones);

        allOrders.add(order);


        given(orderService.findAll()).willReturn(allOrders);



        mockMvc.perform(get("/orders")
                          .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.order[0].reference").value(reference));
    }

}
