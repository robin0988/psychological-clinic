package com.phyclinic.clinic.service;

import com.phyclinic.clinic.persistence.entity.InvoiceEntity;
import com.phyclinic.clinic.persistence.projection.InvoiceSummary;
import com.phyclinic.clinic.persistence.repository.InvoiceRepository;
import com.phyclinic.clinic.service.dto.RandomClinicServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InvoiceService {
    private final String CARD = "C";
    private final String CASH = "A";
    private final String TRANSFER = "B";
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceEntity> getAll(){
        List<InvoiceEntity> invoices = this.invoiceRepository.findAll();
        invoices.forEach(o -> System.out.println(o.getPatient().getFirstName()));
        return invoices;
    }

    public List<InvoiceEntity> getAfter(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.invoiceRepository.findAllByInvoiceDateAfter(today);

    }

    public List<InvoiceEntity> getByMethods(){
        List<String> methods = Arrays.asList(CARD,TRANSFER);
        return this.invoiceRepository.findAllByMethodIn(methods);

    }
    public List<InvoiceEntity> getPatientInvoice(int patientId){
        return  this.invoiceRepository.findPatientInvoice(patientId);
    }
    public InvoiceSummary getSummary(int invoiceId){
        return this.invoiceRepository.findSummary(invoiceId);
    }

    @Transactional
    public boolean saveRandomService(RandomClinicServiceDTO dto){
        return this.invoiceRepository.saveRandomService(dto.getPatientId(),dto.getMethod());
    }
}
