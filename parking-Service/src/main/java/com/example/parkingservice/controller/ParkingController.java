package com.example.parkingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/parking")
public class ParkingController {

    @GetMapping("parking")
    public String getParking(){
        return "pp";
    }
}
