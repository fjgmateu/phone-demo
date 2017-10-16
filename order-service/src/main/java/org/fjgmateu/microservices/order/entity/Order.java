package org.fjgmateu.microservices.order.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * Created by FJGMATEU.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="order")
@XmlRootElement(name = "order")
@JsonRootName("order")
public class Order {

    @Id
    private String reference;

    @Field("name")
    private String name;

    @Field("surname")
    private String surname;

    @Field("email")
    private String email;


    @Field("price")
    private double price;

    @Field("phone")
    private List<Phone> phone;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", phone=" + phone +
                '}';
    }
}
