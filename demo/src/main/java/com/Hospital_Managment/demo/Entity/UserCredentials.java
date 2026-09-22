package com.Hospital_Managment.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;


    @Column( nullable = false)
    private String password;

    private String role;

}

