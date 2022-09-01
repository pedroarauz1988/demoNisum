package com.nisum.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    private Date created;
    private Date modified;
    private Date lastLogin;
    @Builder.Default
    private String token = "";
    @Builder.Default
    private Boolean active = true;


    @OneToMany(mappedBy = "user")
    private Collection<com.nisum.service.entity.Phone> phones;
}
