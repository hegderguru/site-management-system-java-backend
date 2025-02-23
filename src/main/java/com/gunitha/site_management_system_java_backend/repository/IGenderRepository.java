package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGenderRepository extends JpaRepository<Gender,Long> {

    Optional<Gender> findByType(String type);
}
