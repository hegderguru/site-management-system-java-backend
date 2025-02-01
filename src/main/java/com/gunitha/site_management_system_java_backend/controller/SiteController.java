package com.gunitha.site_management_system_java_backend.controller;

import com.gunitha.site_management_system_java_backend.read.SiteInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/site")
public class SiteController {

    public List<SiteInfo> findAllSites(){
        return null;
    }

}
