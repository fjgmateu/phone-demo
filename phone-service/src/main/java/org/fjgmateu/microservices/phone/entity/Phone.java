package org.fjgmateu.microservices.phone.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Document(collection="Phone")
@XmlRootElement(name = "Phone")
@JsonRootName("Phone")
public class Phone {

    @Id
    private String reference;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("price")
    private double price;
	
	@Field("url_image")
    private String urlImage;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Phone{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
