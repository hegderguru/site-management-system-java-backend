package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {

    List<Person> findPersonsByOrganisationsId(Long id);

    List<Person> findPersonBySitesId(Long siteId);
}
