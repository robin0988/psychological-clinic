package com.phyclinic.clinic.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Invoice")
@Getter
@Setter
@NoArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="InvoiceId")
    private Integer invoiceId;

    @Column(name = "PatientID", nullable = false)
    private Integer patientId;

    @Column(name = "TotalInvoice",nullable = false,columnDefinition = "decimal(10,2)")
    private Double totalInvoice;

    @Column(nullable = false,columnDefinition = "Char(1)")
    private String method;

    @Column(name = "AdditionalNotes", length = 250)
    private  String additionalNotes;

    @Column(name= "InvoiceDate", columnDefinition = "timestamp")
    private LocalDateTime invoiceDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID", insertable = false, updatable = false)
    private PatientEntity patient;

    @OneToMany(mappedBy = "invoice" ,fetch = FetchType.EAGER)
    @OrderBy("price ASC")
    private List<InvoiceItemEntity> items;
}
