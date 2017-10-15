package org.fjgmateu.microservices.phone.service;

import org.fjgmateu.microservices.phone.dto.PhoneDTO;
import org.fjgmateu.microservices.phone.entity.Phone;

import java.util.List;

/**
 * Created by FJGMATEU
 */
public interface IPhoneService {

        public PhoneDTO find (final String reference);

        public List<PhoneDTO> findAll () ;

        public String add(PhoneDTO input) ;

      }
