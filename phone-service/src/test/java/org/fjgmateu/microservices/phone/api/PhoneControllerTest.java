package org.fjgmateu.microservices.phone.api;


import org.fjgmateu.microservices.phone.config.PhoneApplication;
import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




/**
 * Created by FJGMATEU
 */

@RunWith(SpringRunner.class)
//@WebMvcTest(PhoneController.class)
@SpringBootTest(classes = {PhoneApplication.class})
public class PhoneControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PhoneController phoneController;

    @Mock
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

        String reference="ABCD123456";
        //PhoneDTO phone = new PhoneDTO("ABCD123456",1,"pending");
        PhoneDTO phone=null;
        when(phoneService.find(reference)).thenReturn(phone);

        mockMvc.perform(get("/phone/"+reference)
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());

    }


    @Test
    public void givenPhones_whenGetPhones_thenReturnJsonArray()
          throws Exception {

        //PhoneDTO phone = new PhoneDTO("ABCD123456",1,"pending");
        PhoneDTO phone=null;
        List<PhoneDTO> allPhones = Arrays.asList(phone);

        given(phoneService.findAll()).willReturn(allPhones);



        mockMvc.perform(get("/phones")
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].reference").value(phone.getReference()));
    }

}
