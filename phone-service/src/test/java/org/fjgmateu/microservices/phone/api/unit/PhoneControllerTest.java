package org.fjgmateu.microservices.phone.api.unit;


import org.fjgmateu.microservices.phone.api.PhoneController;
import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.repository.PhoneRepository;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




/**
 * Created by FJGMATEU
 */


@RunWith(SpringRunner.class)

public class PhoneControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PhoneController phoneController;

    @MockBean
    private PhoneRepository phoneRepository;


    @MockBean
    private PhoneService phoneService;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(phoneController)
                .build();
    }

    @Test
    public void givenPhone_whenGetPhone_thenReturnJson()
          throws Exception {

        final String reference="ABCD123418";
        PhoneDTO phone = new PhoneDTO();
        phone.setReference("ABCD123418");
        phone.setName("iphone 7");
        phone.setDescription("Móvil Apple de última generación");
        phone.setPrice(660.29);
        phone.setUrlImage("http://masmovil.es/imagenes/iphone7.jpg");

        when(phoneService.find(reference)).thenReturn(phone);

        mockMvc.perform(get("/phone/"+reference)
                .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.reference").value(reference));

    }


    @Test
    public void givenPhones_whenGetPhones_thenReturnJsonArray()
          throws Exception {

        final String reference="ABCD123418";
        PhoneDTO phone = new PhoneDTO();
        phone.setReference(reference);
        phone.setName("iphone 7");
        phone.setDescription("Móvil Apple de última generación");
        phone.setPrice(660.29);
        phone.setUrlImage("http://masmovil.es/imagenes/iphone7.jpg");

        List<PhoneDTO> allPhones = new ArrayList();
        allPhones.add(phone);
        phone = new PhoneDTO();
        phone.setReference("ABCD123419");
        phone.setName("BQ X5 ");
        phone.setDescription("Móvil BQ con muy buena calidad precio");
        phone.setPrice(330.29);
        phone.setUrlImage("http://masmovil.es/imagenes/bqX5.jpg");

        allPhones.add(phone);
        given(phoneService.findAll()).willReturn(allPhones);



        mockMvc.perform(get("/phones")
                .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.phone[0].reference").value(reference));
    }

}
