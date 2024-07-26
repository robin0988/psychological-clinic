package com.phyclinic.clinic.web.controller;

import com.phyclinic.clinic.persistence.entity.PatientEntity;
import com.phyclinic.clinic.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<PatientEntity> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.patientService.findByPhone(phone));
    }
}
