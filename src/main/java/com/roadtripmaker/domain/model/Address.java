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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @NotEmpty(message = "rue obligatoire")
    private String street;

    @NotEmpty(message = "Pays obligatoire")
    private String country;

    private String additionalAddress;

    @NotEmpty(message = "Ville obligatoire")
    private String city;

    @NotEmpty(message = "code postal obligatoire")
    private String zip;
}
