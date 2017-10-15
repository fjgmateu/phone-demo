package org.fjgmateu.microservices.order.service;

import org.fjgmateu.microservices.phone.config.PhoneApplication;
import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.entity.Phone;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

/**
 *  FJGMATEU
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhoneApplication.class)
public class OrderServiceTest {

    @Autowired
    private PhoneService phoneService;


    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertPhoneEntityToPhoneDTO_thenCorrect() {
        Phone phone = new Phone();
        phone.setReference(randomAlphabetic(10));
        phone.setName(randomAlphabetic(6));
        phone.setDescription(randomAlphabetic(20));
        phone.setPrice(345.66);
        phone.setUrlImage("www.test.com");

        PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
        assertEquals(phone.getReference(), phoneDTO.getReference());
        assertEquals(phone.getName(), phoneDTO.getName());
        assertEquals(phone.getDescription(), phoneDTO.getDescription());
        assertEquals(phone.getPrice(), phoneDTO.getPrice());
        assertEquals(phone.getUrlImage(), phoneDTO.getUrlImage());
    }

        // write test cases here
}