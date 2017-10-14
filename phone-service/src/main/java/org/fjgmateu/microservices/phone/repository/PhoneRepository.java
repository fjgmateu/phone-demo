package org.fjgmateu.microservices.data.repository;

import org.fjgmateu.microservices.data.domain.Phone;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Created by FJGMATEU.
 */

public interface PhoneRepository extends MongoRepository<Phone, String> {

    public Phone findByReference(String reference);
    public List<Phone> findAll();

}