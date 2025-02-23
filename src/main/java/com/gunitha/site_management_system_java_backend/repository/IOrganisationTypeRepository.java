package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.OrganisationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrganisationTypeRepository extends JpaRepository<OrganisationType,Long> {

    Optional<OrganisationType> findByOrganisationType(String organisationType);

}
