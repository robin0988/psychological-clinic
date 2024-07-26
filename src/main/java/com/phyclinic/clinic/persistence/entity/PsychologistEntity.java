package com.phyclinic.clinic.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Psychologist")
@Getter
@Setter
@NoArgsConstructor
public class PsychologistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to be updated one to one
    @Column(name ="PsychologistID",nullable = false)
    private Integer idPhysicologist;
    @Column(name ="FirstName",nullable = false, length = 50)
    private String firstName;
    @Column(name ="LastName",nullable = false, length = 50)
    private String lastName;
    @Column(name ="Specialization",nullable = false, length = 100)
    private String speciality;
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
