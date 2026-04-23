package com.example.majorproject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_users")
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String userName;
    private String email;
    private String name;
    private int age;


}