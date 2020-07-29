package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {
@Autowired
    DistilleryRepository distilleryRepository;
//    http://localhost:8080/distilleries?age=12
    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(@RequestParam(name="age", required = false) Integer age){
                if( age != null){
                    return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(age), HttpStatus.OK);
                }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/distilleries/{region}")
    public ResponseEntity<List<Distillery>> getDistilleriesByRegion(@PathVariable String region){
        return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
    }
//    http://localhost:8080/distilleries/12yo
    @GetMapping(value = "/distilleries/{age}yo")
    public ResponseEntity<List<Distillery>> getDistilleriesWithWhiskey12yo(@PathVariable int age){
        return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(age), HttpStatus.OK);
    }
}
