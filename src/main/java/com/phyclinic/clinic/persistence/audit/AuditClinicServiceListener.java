package com.phyclinic.clinic.persistence.audit;

import com.phyclinic.clinic.persistence.entity.ServiceEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditClinicServiceListener {
    private ServiceEntity currentValue;

    @PostLoad
    public void postLoad(ServiceEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(ServiceEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(ServiceEntity entity) {
        System.out.println(entity.toString());
    }
}
