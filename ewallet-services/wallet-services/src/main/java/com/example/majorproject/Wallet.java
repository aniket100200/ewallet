package com.example.majorproject;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private int balance;
}
