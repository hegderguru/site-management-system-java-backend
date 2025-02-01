package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISiteRepository extends JpaRepository<Site,Long> {

}
