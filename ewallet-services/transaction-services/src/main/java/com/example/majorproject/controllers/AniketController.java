package com.example.majorproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aniket")
public class AniketController {

    @GetMapping("/name")
    public String getName(){
        return "Aniket";
    }
}
