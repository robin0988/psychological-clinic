package com.phyclinic.clinic.web.controller;

import com.phyclinic.clinic.persistence.entity.InvoiceEntity;
import com.phyclinic.clinic.persistence.projection.InvoiceSummary;
import com.phyclinic.clinic.service.InvoiceService;
import com.phyclinic.clinic.service.dto.RandomClinicServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public ResponseEntity<List<InvoiceEntity>> getAll(){
        return ResponseEntity.ok(this.invoiceService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<InvoiceEntity>> getToDayInvoice(){
        return ResponseEntity.ok(this.invoiceService.getAfter());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<InvoiceEntity>> getByMethods(){
        return ResponseEntity.ok(this.invoiceService.getByMethods());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InvoiceEntity>> getPatientInvoice(@PathVariable int patientId){
        return ResponseEntity.ok(this.invoiceService.getPatientInvoice(patientId));
    }

    @GetMapping("/summary/{invoiceId}")
    public ResponseEntity<InvoiceSummary> getSummary(@PathVariable int invoiceId){
        return ResponseEntity.ok(this.invoiceService.getSummary(invoiceId));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomService(@RequestBody RandomClinicServiceDTO dto){
        return ResponseEntity.ok(this.invoiceService.saveRandomService(dto));
    }
}
