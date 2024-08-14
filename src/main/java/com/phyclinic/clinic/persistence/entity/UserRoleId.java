package com.phyclinic.clinic.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private String username;
    private String role;
    @Override
    public  boolean equals(Object o){
        if(this == o) return  true;
        if(!(o instanceof  UserRoleId that)) return false;
        return Objects.equals(username,that.username) && Objects.equals(role,that.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(username,role);
    }
}

