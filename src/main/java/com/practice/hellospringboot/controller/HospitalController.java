package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.dao.HospitalDao;
import com.practice.hellospringboot.domain.Hospital;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1/hospital")
@RestController
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalDao hospitalDao;

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> get(@PathVariable Integer id) {
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital);

        if (!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }

}
