package org.fjgmateu.microservices.phone.api;

import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.exception.ServiceDataException;
import org.fjgmateu.microservices.phone.service.IPhoneService;
import org.fjgmateu.microservices.phone.service.impl.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseListWrapper findAll() {
        List<PhoneDTO>  phones= phoneService.findAll();
        if (CollectionUtils.isEmpty(phones)){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return new ResponseListWrapper(phones);
    }

    @CrossOrigin
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody PhoneDTO input)  {
        String reference=phoneService.add(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/phone/{id}")
                .buildAndExpand(reference).toUri();

        return ResponseEntity.created(location).build();
    }


}
