package org.fjgmateu.microservices.order.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * Created by FJGMATEU.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "order")
@JsonRootName("order")
public class OrderDTO {

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("name")
    @NotEmpty
    private String name;

    @JsonProperty("surname")
    @NotEmpty
    private String surname;

    @JsonProperty("email")
    @NotEmpty
    private String email;

    @JsonProperty("phone")
    @NotEmpty
    private List<PhoneDTO> phones;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }
}

