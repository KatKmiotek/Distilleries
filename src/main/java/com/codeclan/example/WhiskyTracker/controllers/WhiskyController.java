package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(value = "/whiskies")
//    public ResponseEntity<List<Whisky>> getAllWhiskies(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }
    @GetMapping(value = "/whiskies/{year}")
    public ResponseEntity<List<Whisky>> findAllAtAge(@PathVariable int year){
        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
    }
//    @GetMapping(value = "/whiskies/{distilleryName}/{age}")
//    public ResponseEntity<List<Whisky>> findAllFromDistilleryByAge(@PathVariable String distilleryName, @PathVariable Integer age){
//        return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
//    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name="region", required = false) String region,
            @RequestParam(name= "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age
    ){
        if (region != null) {
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
//        http://localhost:8080/whiskies?name=Glendronach&age=12
        if(name != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(name, age), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);

        }
    }
}
