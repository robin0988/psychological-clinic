package com.phyclinic.clinic.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "InvoiceItem")
@IdClass(InvoiceItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItemEntity {

    @Id
    @Column(name = "ItemId",nullable = false)
    private Integer itemId;

    @Id
    @Column(name = "InvoiceId",nullable = false)
    private Integer invoiceId;

    @Column(name = "ServiceId",nullable = false)
    private Integer serviceId;

    @Column(columnDefinition = "decimal(10,2)")
    private Double quantity;

    @Column(columnDefinition = "decimal(10,2)")
    private Double price;

    @OneToOne
    @JoinColumn(name = "ServiceId",referencedColumnName = "ServiceId", insertable = false, updatable = false)
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "InvoiceId",referencedColumnName = "InvoiceId", insertable = false, updatable = false)
    @JsonIgnore
    private InvoiceEntity invoice;
}
