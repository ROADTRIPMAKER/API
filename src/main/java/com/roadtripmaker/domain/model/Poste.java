package com.roadtripmaker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poste {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @NotEmpty(message = "Titre obligatoire")
    private String titre;

    @NotEmpty(message = "description obligatoire")
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedTime;

}
