package org.fjgmateu.microservices.api.controller;

import org.fjgmateu.microservices.api.config.PhoneApiApplication;
import org.fjgmateu.microservices.data.domain.Phone;
import org.fjgmateu.microservices.service.impl.PhoneService;
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
@SpringBootTest(classes = {PhoneApiApplication.class})
public class PhoneControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PhoneController PhoneController;

    @Mock
    private PhoneService PhoneService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
              .standaloneSetup(PhoneController)
              .build();
    }

    @Test
    public void givenPhone_whenGetPhone_thenReturnJson()
          throws Exception {

        String reference="ABCD123456";
        Phone Phone = new Phone("ABCD123456",1,"pending");
        when(PhoneService.find(reference)).thenReturn(Phone);

        mockMvc.perform(get("/Phone/"+reference)
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());

    }


    @Test
    public void givenPhones_whenGetPhones_thenReturnJsonArray()
          throws Exception {

        Phone Phone = new Phone("ABCD123456",1,"pending");

        List<Phone> allPhones = Arrays.asList(Phone);

        given(PhoneService.findAll()).willReturn(allPhones);



        mockMvc.perform(get("/Phones")
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].reference").value(Phone.getReference()));



    }

}
