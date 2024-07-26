package com.phyclinic.clinic.service;

import com.phyclinic.clinic.persistence.entity.ServiceEntity;
import com.phyclinic.clinic.persistence.repository.ClinicServicePagSortRepository;
import com.phyclinic.clinic.persistence.repository.ClinicServiceRepository;
import com.phyclinic.clinic.service.Exception.EmailApiException;
import com.phyclinic.clinic.service.dto.RandomClinicServiceDTO;
import com.phyclinic.clinic.service.dto.UpdateServicePriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicService {

    //To have queries in our service
    //private final JdbcTemplate;
    private ClinicServiceRepository clinicServiceRepository;
    private ClinicServicePagSortRepository clinicServicePagSortRepository;

    //@Autowired
   /* public ClinicService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }*/
    @Autowired
    public ClinicService(ClinicServiceRepository clinicServiceRepository, ClinicServicePagSortRepository clinicServicePagSortRepository){
        this.clinicServiceRepository = clinicServiceRepository;
        this.clinicServicePagSortRepository = clinicServicePagSortRepository;
    }

    /*public List<ServiceEntity> getAll(){
        return this.jdbcTemplate.query("select * from Service", new BeanPropertyRowMapper<>(ServiceEntity.class));
    }*/

    public List<ServiceEntity> getAll(){
        return this.clinicServiceRepository.findAll();
    }

    public Page<ServiceEntity> getAllPaginated(int page , int elements){
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.clinicServicePagSortRepository.findAll(pageRequest);
    }

    public Page<ServiceEntity> getAllPaginatedAndSort(int page , int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page,elements, sort);
        return this.clinicServicePagSortRepository.findByServiceActive("Y",pageRequest);
    }

    public  List<ServiceEntity> getAvailable(){
        System.out.println(this.clinicServiceRepository.countByServiceActiveIs("N"));
        return this.clinicServiceRepository.findAllByServiceActiveOrderByServicePrice("Y");
    }

    public ServiceEntity getByName(String serviceActive,String serviceName){
        return this.clinicServiceRepository.findAllByServiceActiveAndServiceNameIgnoreCase(serviceActive,serviceName);
    }

    public ServiceEntity getByNameEnhanced(String serviceActive,String serviceName){
        return this.clinicServiceRepository.findFirstByServiceActiveAndServiceNameIgnoreCase(serviceActive,serviceName)
                .orElseThrow(() ->new RuntimeException("Service does not exist"));
    }

    public List<ServiceEntity> getByDesc(String serviceActive,String serviceName){
        return this.clinicServiceRepository.findAllByServiceActiveAndServiceDescContainingIgnoreCase(serviceActive,serviceName);
    }

    public List<ServiceEntity> getByDescNot(String serviceActive,String serviceName){
        return this.clinicServiceRepository.findAllByServiceActiveAndServiceDescNotContainingIgnoreCase(serviceActive,serviceName);
    }
    public ServiceEntity get(int serviceId){
        return this.clinicServiceRepository.findById(serviceId).orElse(null);
    }

    public ServiceEntity save(ServiceEntity service){
        return this.clinicServiceRepository.save(service);
    }

    public void delete(int serviceId){
        this.clinicServiceRepository.deleteById(serviceId);
    }
    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePrice(UpdateServicePriceDTO updateServicePriceDTO){

        this.clinicServiceRepository.updatePriceModifying(updateServicePriceDTO);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }
    public boolean exists(int serviceId){
        return this.clinicServiceRepository.existsById(serviceId);
    }

    public List<ServiceEntity> getCheapest(String serviceActive,double servicePrice){
        return this.clinicServiceRepository.findTop3ByServiceActiveAndServicePriceLessThanEqualOrderByServicePriceDesc(serviceActive,servicePrice);
    }
}
