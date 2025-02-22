package com.gunitha.site_management_system_java_backend;

import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import com.gunitha.site_management_system_java_backend.util.ChangesUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Test1 {

    public void test(){
        SiteInfoUpdate siteInfoUpdate = new SiteInfoUpdate();
        ChangesUtil.getAllCustomTypeObjects(siteInfoUpdate);
    }
}
