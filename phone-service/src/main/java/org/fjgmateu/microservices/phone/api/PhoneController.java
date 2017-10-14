package org.fjgmateu.microservices.api.controller;

import org.fjgmateu.microservices.data.domain.Phone;
import org.fjgmateu.microservices.service.exception.ServiceDataException;
import org.fjgmateu.microservices.service.impl.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by FJGMATEU
 */
@RestController
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @CrossOrigin
    @RequestMapping(value = "/phone/{reference}", method = RequestMethod.GET)
    public PhoneDTO find(@PathVariable("reference") String reference) {
        PhoneDTO phone=PhoneService.find(reference);
        if (phone==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return Phone;
    }


    @CrossOrigin
    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    public List<PhoneDTO> findAll() {
        List<PhoneDTO>  phones= phoneService.findAll();
        if (CollectionUtils.isEmpty(phones)){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return phones;
    }


}
