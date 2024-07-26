package com.phyclinic.clinic.persistence.repository;

import com.phyclinic.clinic.persistence.entity.ServiceEntity;
import com.phyclinic.clinic.service.dto.UpdateServicePriceDTO;
import lombok.val;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClinicServiceRepository extends ListCrudRepository<ServiceEntity,Integer> {

    List<ServiceEntity> findAllByServiceActiveOrderByServicePrice(String serviceActive);
    ServiceEntity findAllByServiceActiveAndServiceNameIgnoreCase(String active,String serviceName);
    Optional<ServiceEntity> findFirstByServiceActiveAndServiceNameIgnoreCase(String active, String serviceName);

    List<ServiceEntity> findAllByServiceActiveAndServiceDescContainingIgnoreCase(String serviceActive,String serviceDesc);
    List<ServiceEntity> findAllByServiceActiveAndServiceDescNotContainingIgnoreCase(String serviceActive,String serviceDesc);
    int countByServiceActiveIs(String serviceActive);

    List<ServiceEntity> findTop3ByServiceActiveAndServicePriceLessThanEqualOrderByServicePriceDesc(String serviceActive,double servicePrice);

    @Query(value =
            "UPDATE service " +
                    "SET service_price = :newServicePrice " +
                    "WHERE service_id = :serviceId", nativeQuery = true)
    void updatePrice(@Param("newServicePrice") double newServicePrice, @Param("serviceId") int serviceId);

    @Query(value =
            "UPDATE service " +
                    "SET service_price = :#{#newServicePrice.newPrice} " +
                    "WHERE service_id = :#{#newServicePrice.serviceId} ", nativeQuery = true)
    @Modifying
    void updatePriceModifying(@Param("newServicePrice")UpdateServicePriceDTO newServicePrice);

}
