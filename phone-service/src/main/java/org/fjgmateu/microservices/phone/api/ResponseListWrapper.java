package org.fjgmateu.microservices.phone.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.fjgmateu.microservices.phone.dto.PhoneDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/***
 * FJGMATEU
 */
@XmlRootElement(name = "phones")
@JsonRootName("phones")
public class ResponseListWrapper {

    @JsonIgnoreProperties("phones")
    private List<PhoneDTO> phone;

    public ResponseListWrapper() {
    }

    public ResponseListWrapper(List<PhoneDTO> phone) {
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
        return "ResponseListWrapper{" +
                "phone=" + phone +
                '}';
    }
}
