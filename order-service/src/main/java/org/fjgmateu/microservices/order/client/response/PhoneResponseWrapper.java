package org.fjgmateu.microservices.order.client.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.fjgmateu.microservices.order.dto.PhoneDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/***
 * FJGMATEU
 */
@XmlRootElement(name = "phone")
@JsonRootName("phone")
public class PhoneResponseWrapper {

    @JsonIgnoreProperties("phone")
    private List<PhoneDTO> phone;

    public PhoneResponseWrapper() {
    }

    public PhoneResponseWrapper(List<PhoneDTO> phone) {
        this.phone = phone;
    }

    public List<PhoneDTO> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDTO> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneResponseWrapper{" +
                "phone=" + phone +
                '}';
    }
}
