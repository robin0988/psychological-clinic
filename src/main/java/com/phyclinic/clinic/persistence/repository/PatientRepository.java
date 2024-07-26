package com.phyclinic.clinic.persistence.repository;

import com.phyclinic.clinic.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends ListCrudRepository<PatientEntity,Integer> {
    @Query(value = "SELECT p FROM PatientEntity p WHERE p.phoneNumber = :phone")
    PatientEntity findByPhoneNumber(@Param("phone") String phone);

}
