package com.example.userformbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueFirstandLastName", columnNames = { "firstname", "lastname" })})
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NotEmpty
    @Column(name ="firstname")
    private String firstName;

    @NotEmpty
    @Column(name ="lastname")
    private String lastName;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String comment;


}
