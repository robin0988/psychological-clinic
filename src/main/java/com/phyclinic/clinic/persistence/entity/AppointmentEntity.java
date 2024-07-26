package com.phyclinic.clinic.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Appointment")
@Getter
@Setter
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="AppointmentID ", nullable = false)
    private Integer appoimentId;

    @Column(name="PatientID ", nullable = false)
    private Integer patientId;
    @Column(name="PsychologistID ", nullable = false)
    private Integer psychologistId;
    @Column(name="AppointmentDate ", nullable = false)
    private Date appoimentDate;
    @Column(name="AppointmentStatus ", nullable = false, length = 20)
    private String appoimentStatus;
    @Column(name="Notes ", length = 2500)
    private String notes;
    @CreationTimestamp
    @Column(name="DateCreated ", columnDefinition = "timestamp")
    private LocalDateTime creationDate;

    /*
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (PsychologistID) REFERENCES Psychologists(PsychologistID)
    * */
}
