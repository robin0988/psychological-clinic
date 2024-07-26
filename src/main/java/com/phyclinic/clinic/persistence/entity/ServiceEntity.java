package com.phyclinic.clinic.persistence.entity;

import com.phyclinic.clinic.persistence.audit.AuditClinicServiceListener;
import com.phyclinic.clinic.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "Service")
@EntityListeners({AuditingEntityListener.class, AuditClinicServiceListener.class})
@Getter
@Setter
@NoArgsConstructor

public class ServiceEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceId")
    private Integer serviceId;

    @Column(name = "ServiceName" , nullable = false, length = 100)
    private String serviceName;

    @Column(name = "ServiceDesc", nullable = false, length = 250)
    private String serviceDesc;

    @Column(name = "ServicePrice", nullable = false, columnDefinition = "decimal(10,2)")
    private Double servicePrice;

    @Column(name = "ServiceActive",nullable = false,columnDefinition = "Char(1)")
    private String serviceActive;

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", servicePrice=" + servicePrice +
                ", serviceActive='" + serviceActive + '\'' +
                '}';
    }
}
