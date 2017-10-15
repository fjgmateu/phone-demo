package org.fjgmateu.microservices.order.client.impl;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.fjgmateu.microservices.order.client.IPhoneServiceClient;
import org.fjgmateu.microservices.order.dto.PhoneDTO;
import org.fjgmateu.microservices.order.exception.ServiceClientException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 * Created by FJGMATEU
 */
@Service
public class PhoneServiceClient implements IPhoneServiceClient{

    private final Logger logger = LoggerFactory.getLogger(PhoneServiceClient.class);

    @Value("${endpoint.phone.findall}")
    private String endPointPhoneFindAll;

    @Autowired
    private RestTemplate restTemplate;

    public List<PhoneDTO> findPhones() throws ServiceClientException {

        List<PhoneDTO> phones = null;
        try {
            PhoneDTO[] response=restTemplate.getForObject(
                    endPointPhoneFindAll, PhoneDTO[].class);
            if (response==null){
                throw new ServiceClientException("No existen teléfonos en el catálogo");
            }

            phones= Arrays.asList(response);
            if (CollectionUtils.isEmpty(phones)){
                throw new ServiceClientException("No existen teléfonos en el catálogo");
            }



        } catch (Exception e) {
            String message= ExceptionUtils.getMessage(e);
            logger.error("ERROR, CAUSE: {}", message);
            throw new ServiceClientException("Error en servicio de catálogo de teléfonos");
        }
        return phones;
    }


}
