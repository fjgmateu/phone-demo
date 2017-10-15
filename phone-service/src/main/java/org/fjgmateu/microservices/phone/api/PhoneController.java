package org.fjgmateu.microservices.phone.api;

import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.exception.ServiceDataException;
import org.fjgmateu.microservices.phone.service.IPhoneService;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;
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
    private IPhoneService phoneService;


    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }
    @CrossOrigin
    @RequestMapping(value = "/phone/{reference}", method = RequestMethod.GET)
    public PhoneDTO find(@PathVariable("reference") String reference) {
        PhoneDTO phone=phoneService.find(reference);
        if (phone==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return phone;
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
