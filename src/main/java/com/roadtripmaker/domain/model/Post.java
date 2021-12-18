package com.roadtripmaker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @NotEmpty(message = "Titre obligatoire")
    private String title;

    @NotEmpty(message = "description obligatoire")
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedTime;

    @PrePersist
    private void onCreate() {
        creationTime = new Date();
        updatedTime = new Date();
    }

    @OneToMany
    private Collection<Photo> photos = new ArrayList<>();

    @ManyToOne
    private Address address;
}
