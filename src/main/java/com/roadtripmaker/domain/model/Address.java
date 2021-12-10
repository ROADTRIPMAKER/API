package com.roadtripmaker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity @Data @NoArgsConstructor @AllArgsConstructor public class Address {
    @Id @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator") private UUID uuid;

    private String place;

    @NotEmpty(message = "rue obligatoire") private String street;

    private String country;

    private String additionalAddress;

    @NotEmpty(message = "Ville obligatoire") private String city;

    @NotEmpty(message = "Code postal obligatoire") private String zip;

    @Override public String toString() {
        String str[] = { this.place, this.street, this.additionalAddress, this.zip, this.city, this.country };
        String comma = "";
        StringBuilder sb = new StringBuilder();

        for (String checkNull : str) {
            if (checkNull != null) {
                sb.append(comma);
                sb.append(checkNull);
                comma = ", ";
            }
        }

        return sb.toString();
    }
}
