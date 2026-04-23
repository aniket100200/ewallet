package com.example.majorproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //Define all these function in such manner

    User findByUserName(String userName); //Define in

    List<User> findAllByUserNameAndAge(String userName,int age);
    boolean existsByUserName(String userName);
}
