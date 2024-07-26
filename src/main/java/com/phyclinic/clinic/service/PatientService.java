package com.phyclinic.clinic.service;

import com.phyclinic.clinic.persistence.entity.PatientEntity;
import com.phyclinic.clinic.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService (PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public PatientEntity findByPhone(String phone){
        return this.patientRepository.findByPhoneNumber(phone);
    }
}
