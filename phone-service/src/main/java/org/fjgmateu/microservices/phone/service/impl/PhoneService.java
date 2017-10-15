package org.fjgmateu.microservices.phone.service.impl;

import org.fjgmateu.microservices.phone.exception.ServiceDataException;
import org.fjgmateu.microservices.phone.repository.PhoneRepository;
import org.fjgmateu.microservices.phone.service.IPhoneService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.entity.Phone;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * Created by FJGMATEU
 */
@Service
public class PhoneService implements IPhoneService {

    private final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PhoneRepository phoneRepository;

    public PhoneDTO find (final String reference) {
        logger.info("PhoneService.findAll, reference: " + reference);

        Phone phone=phoneRepository.findByReference(reference);
        if (phone==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return modelMapper.map(phone, PhoneDTO.class);
    }

    public List<PhoneDTO> findAll () {
       logger.info("PhoneService.findAll");
       List<Phone> phones= phoneRepository.findAll();
        if (CollectionUtils
                .isEmpty(phones)){
            throw new ServiceDataException("No se han encontrado datos");
        }

        return phones.stream()
                .map(phone  -> modelMapper.map(phone, PhoneDTO.class))
                .collect(Collectors.toList());
    }

    public String add(PhoneDTO input) {
        logger.info("PhoneService.add, input: " + input);
        Phone phone=modelMapper.map(input, Phone.class);
        phoneRepository.save(phone);
        logger.info("Phone creada, reference: " + phone.getReference());
        return phone.getReference();
    }


}
