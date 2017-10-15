package org.fjgmateu.microservices.order.client;

import org.fjgmateu.microservices.order.dto.PhoneDTO;

import java.util.List;

/**
 * Created by FJGMATEU
 */
public interface IPhoneServiceClient {

    public List<PhoneDTO> findPhones();
}
