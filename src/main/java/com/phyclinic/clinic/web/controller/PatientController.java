package com.phyclinic.clinic.web.controller;

import com.phyclinic.clinic.persistence.entity.InvoiceEntity;
import com.phyclinic.clinic.persistence.entity.PatientEntity;
import com.phyclinic.clinic.service.InvoiceService;
import com.phyclinic.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final InvoiceService invoiceService;

    @Autowired
    public PatientController(PatientService patientService, InvoiceService invoiceService){
        this.patientService=patientService;
        this.invoiceService = invoiceService;
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<PatientEntity> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.patientService.findByPhone(phone));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InvoiceEntity>> getPatientInvoice(@PathVariable int patientId){
        return ResponseEntity.ok(this.invoiceService.getPatientInvoice(patientId));
    }
}
