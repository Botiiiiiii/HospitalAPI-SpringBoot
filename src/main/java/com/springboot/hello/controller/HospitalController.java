package com.springboot.hello.controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    HospitalDao hospitalDao;
    public HospitalController(HospitalDao hospitalDao){
        this.hospitalDao=hospitalDao;
    }

    @GetMapping(value="/search/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        Hospital hospital=hospitalDao.findById(id);
        Optional<Hospital> opt=Optional.of(hospital);

        if(!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        }else{
            return ResponseEntity.badRequest().body(new Hospital());
        }
    }

    @GetMapping(value="/search/name/{name}")
    public ResponseEntity<List<Hospital>> getByName(@PathVariable("name") String name){
        List<Hospital> hospitalList=hospitalDao.findByName(name);

        if(!hospitalList.isEmpty()){
            return ResponseEntity.ok().body(hospitalList);
        }else{
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @DeleteMapping(value="/delete/all")
    public ResponseEntity<Integer> deleteAll(){
        return ResponseEntity
                .ok()
                .body(hospitalDao.DeleteAll());
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") String id){
        return ResponseEntity
                .ok()
                .body(hospitalDao.deleteById(id));
    }

}
