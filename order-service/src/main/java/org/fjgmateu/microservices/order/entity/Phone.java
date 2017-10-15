package org.fjgmateu.microservices.order.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by FJGMATEU.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "phone")
@JsonRootName("phone")
public class Phone {

    @Field("reference")
    private String reference;

    @Field("name")
    private String name;

    @Field("price")
    private double price;
	

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
