package com.phyclinic.clinic.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
public class PatientEntity {

    @Id
    @Column(name = "PatientID")
    private Integer patientId;

    @Column(name ="FirstName",nullable = false, length = 50)
    private String firstName;

    @Column(name ="LastName",nullable = false, length = 50)
    private String lastName;

    @Column(name = "DateOfBirth")
    private LocalDate birthday;

    @Column(name = "Gender" , length = 10)
    private String gender;

    @Column(name ="PhoneNumber",nullable = false, length = 20)
    private String phoneNumber;

    @Column(name ="Email",nullable = false, length = 100)
    private String email;

    @Column(name ="OfficeAddress",nullable = false, length = 255)
    private String officeAddress;

    @Column(name ="City",nullable = false, length = 50)
    private String city;

    @Column(name ="State",nullable = false, length = 50)
    private String State;

    @Column(name ="ZipCode",nullable = false, length = 10)
    private String zipCode;

    @CreationTimestamp
    @Column(name="DateCreated ", columnDefinition = "timestamp")
    private LocalDateTime creationDate;

}
