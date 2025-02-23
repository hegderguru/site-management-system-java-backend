package com.gunitha.site_management_system_java_backend.repository;

import com.gunitha.site_management_system_java_backend.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ISiteRepository extends JpaRepository<Site,Long> {

    List<Site> findSitesByOwnersId(Long ownersId);

    List<Site> findAllByIdIn(List<Long> siteIds);
}
