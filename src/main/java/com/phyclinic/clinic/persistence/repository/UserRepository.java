package com.phyclinic.clinic.persistence.repository;

import com.phyclinic.clinic.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String>{
}
