package org.fjgmateu.microservices.phone.service.unit;

import com.netflix.discovery.converters.Auto;
import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.entity.Phone;
import org.fjgmateu.microservices.phone.repository.PhoneRepository;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;

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
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  FJGMATEU
 */
@RunWith(SpringRunner.class)
public class PhoneServiceTest {

    @Autowired
    private ModelMapper modelMapper;


    @TestConfiguration
    static class PhoneServiceTestContextConfiguration {

        @Bean
        public PhoneService phoneService() {
            return new PhoneService();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }
    @Autowired
    private PhoneService phoneService;

    @MockBean
    private PhoneRepository phoneRepository;


    @Before
    public void setUp() {
        final String reference="ABCD123418";
        Phone phone = new Phone();
        phone.setReference(reference);
        phone.setName("iphone 7");
        phone.setDescription("Móvil Apple de última generación");
        phone.setPrice(660.29);
        phone.setUrlImage("http://masmovil.es/imagenes/iphone7.jpg");

        Mockito.when(phoneRepository.findByReference(phone.getReference()))
                .thenReturn(phone);
    }

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
        assertEquals(phone.getPrice(), phoneDTO.getPrice(),0.001);
        assertEquals(phone.getUrlImage(), phoneDTO.getUrlImage());
    }

    @Test
    public void whenValidName_thenPhoneShouldBeFound() {
        final String reference="ABCD123418";
        PhoneDTO phone = phoneService.find(reference);
        assertTrue(phone.getReference().equals(reference));
    }

        // write test cases here
}