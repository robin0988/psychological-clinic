package com.phyclinic.clinic.web.controller;

import com.phyclinic.clinic.persistence.entity.ServiceEntity;
import com.phyclinic.clinic.service.ClinicService;
import com.phyclinic.clinic.service.dto.RandomClinicServiceDTO;
import com.phyclinic.clinic.service.dto.UpdateServicePriceDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicservices") //attend request under
public class ClinicServiceController {
    private final ClinicService clinicService;

    @Autowired
    public ClinicServiceController(ClinicService clinicService){
        this.clinicService=clinicService;
    }
    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAll(){
        return ResponseEntity.ok(this.clinicService.getAll());
    }

    @GetMapping("/getPaginated")
    public ResponseEntity<Page<ServiceEntity>> getAllPaginated(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.clinicService.getAllPaginated(page,elements));
    }

    @GetMapping("/getPaginatedAndSort")
    public ResponseEntity<Page<ServiceEntity>> getAllPaginatedAndSort(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "8") int elements,
                                                                      @RequestParam(defaultValue = "servicePrice") String sortBy,
                                                                      @RequestParam(defaultValue = "ASC") String sortDirection){//entityName not database name
        return ResponseEntity.ok(this.clinicService.getAllPaginatedAndSort (page,elements,sortBy, sortDirection));
    }
    @GetMapping("/{serviceId}")//Pathvariable help to link the mapping id to the parameter in the method
    public ResponseEntity<ServiceEntity> get(@PathVariable int serviceId){
        return ResponseEntity.ok(this.clinicService.get(serviceId));
    }

    @GetMapping("/available")
    //@CrossOrigin("localhost:4040")
    public ResponseEntity<List<ServiceEntity>> getAvailable(){
        return ResponseEntity.ok(this.clinicService.getAvailable());
    }

    @GetMapping("/servicename/{serviceName}")
    public ResponseEntity<ServiceEntity> getByName(@PathVariable String serviceName){
        return ResponseEntity.ok(this.clinicService.getByName("Y",serviceName));
    }

    @GetMapping("/servicenamev2/{serviceName}")
    public ResponseEntity<ServiceEntity> getByNameEnhanced(@PathVariable String serviceName){
        return ResponseEntity.ok(this.clinicService.getByNameEnhanced("Y",serviceName));
    }

    @GetMapping("/servicedesc/{serviceDesc}")
    public ResponseEntity<List<ServiceEntity>> getByDesc(@PathVariable String serviceDesc){
        return ResponseEntity.ok(this.clinicService.getByDesc("Y",serviceDesc));
    }

    @GetMapping("/servicedescnot/{serviceDesc}")
    public ResponseEntity<List<ServiceEntity>> getByDescNot(@PathVariable String serviceDesc){
        return ResponseEntity.ok(this.clinicService.getByDescNot("Y",serviceDesc));
    }

    @GetMapping("/cheapest/{servicePrice}")
    public ResponseEntity<List<ServiceEntity>> getBycheapest(@PathVariable double servicePrice){
        return ResponseEntity.ok(this.clinicService.getCheapest("Y",servicePrice));
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> add(@RequestBody ServiceEntity service){
        if(service.getServiceId()==null || !this.clinicService.exists(service.getServiceId())){
            return ResponseEntity.ok(this.clinicService.save(service));
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<ServiceEntity> update(@RequestBody ServiceEntity service){
        if(service.getServiceId()!=null && this.clinicService.exists(service.getServiceId())){
            return ResponseEntity.ok(this.clinicService.save(service));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdateServicePriceDTO dto){
        if(this.clinicService.exists(dto.getServiceId())){
            this.clinicService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> delete(@PathVariable int serviceId){
        if(this.clinicService.exists(serviceId)){
            this.clinicService.delete(serviceId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
