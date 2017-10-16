package org.fjgmateu.microservices.order.service.unit;

import org.fjgmateu.microservices.order.client.impl.PhoneServiceClient;
import org.fjgmateu.microservices.order.dto.OrderDTO;
import org.fjgmateu.microservices.order.entity.Order;
import org.fjgmateu.microservices.order.entity.Phone;
import org.fjgmateu.microservices.order.repository.OrderRepository;
import org.fjgmateu.microservices.order.service.impl.OrderService;
import org.fjgmateu.microservices.order.validator.ValidatorBean;
import org.fjgmateu.microservices.order.validator.impl.OrderValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  FJGMATEU
 */
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private ModelMapper modelMapper;


    @TestConfiguration
    static class OrderServiceTestContextConfiguration {

        @Bean
        public OrderService orderService() {
            return new OrderService();
        }
        @Bean
        public OrderValidator orderValidator(){return new OrderValidator();}
        @Bean
        ValidatorBean validatorBean() {
            return new ValidatorBean();
        }

        @Bean
        RestTemplate restTemplate(){ return new RestTemplate();}
        @Bean
        public PhoneServiceClient phoneServiceClient() {
            return new PhoneServiceClient();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;


    @Before
    public void setUp() {
        final String reference="ABCD123418";
        Order order = new Order();
        order.setReference(reference);
        order.setName("Nombre cliente 1");
        order.setSurname("Apellido cliente 1");
        order.setEmail("Email cliente 1");


        Phone phone = new Phone();
        phone.setReference("referencia telefono 1");
        phone.setName("nombre telefono 1");
        phone.setPrice(345.66);

        List<Phone> phones=new ArrayList<Phone>();
        phones.add(phone);

        order.setPhone(phones);
        Mockito.when(orderRepository.findByReference(order.getReference()))
                .thenReturn(order);
    }

    @Test
    public void whenConvertOrderEntityToOrderDTO_thenCorrect() {

        Order order = new Order();
        order.setReference(randomAlphabetic(10));
        order.setName("Nombre cliente 1");
        order.setSurname("Apellido cliente 1");
        order.setEmail("Email cliente 1");


        Phone phone = new Phone();
        phone.setReference(randomAlphabetic(10));
        phone.setName(randomAlphabetic(6));
        phone.setPrice(345.66);

        List<Phone> phones=new ArrayList<Phone>();
        phones.add(phone);

        order.setPhone(phones);

        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        assertEquals(order.getReference(), orderDTO.getReference());
        assertEquals(order.getName(), orderDTO.getName());
        assertEquals(order.getSurname(), orderDTO.getSurname());
        assertEquals(order.getEmail(), orderDTO.getEmail());
    }

    @Test
    public void whenValidName_thenPhoneShouldBeFound() {
        final String reference="ABCD123418";
        OrderDTO phone = orderService.find(reference);
        assertTrue(phone.getReference().equals(reference));
    }

        // write test cases here
}