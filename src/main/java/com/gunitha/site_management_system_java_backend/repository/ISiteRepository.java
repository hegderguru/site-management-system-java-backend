package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISiteRepository extends JpaRepository<Site,Long> {

    List<Person> findPersonBySitesId(Long siteId);

    List<Site> findSitesByOwnersId(Long ownersId);
}
