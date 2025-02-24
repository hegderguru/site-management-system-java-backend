package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganisationRepository extends JpaRepository<Organisation,Long> {

    List<Organisation> findOrganisationsBySitesId(Long siteId);
}
