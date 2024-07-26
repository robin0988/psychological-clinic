package com.phyclinic.clinic.persistence.repository;

import com.phyclinic.clinic.persistence.entity.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ClinicServicePagSortRepository extends ListPagingAndSortingRepository<ServiceEntity,Integer> {

    Page<ServiceEntity> findByServiceActive(String serviceActive, Pageable pageable);
}
