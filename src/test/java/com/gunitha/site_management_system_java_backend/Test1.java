package com.gunitha.site_management_system_java_backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObject;
import com.gunitha.site_management_system_java_backend.model.update.AddressInfoUpdate;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import com.gunitha.site_management_system_java_backend.change.ChangesUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class Test1 {

    @Test
    public void test() throws JsonProcessingException {
        SiteInfoUpdate siteInfoUpdate = getSiteInfoUpdate();
        List<ChangeTargetObject> allCustomTypeObjects1 = ChangesUtil.getAllCustomTypeObjects(siteInfoUpdate, siteInfoUpdate.getClass().getName());
        SiteInfoUpdate siteInfoUpdate1 = getSiteInfoUpdate();
        siteInfoUpdate1.getPersonInfoUpdates().get(0).setFirstName("Guru3");
        siteInfoUpdate1.getPersonInfoUpdates().get(1).setLastName("Hegde");
        List<ChangeTargetObject> allCustomTypeObjects2 = ChangesUtil.getAllCustomTypeObjects(siteInfoUpdate1,siteInfoUpdate1.getClass().getName());
        List<Change> changes = ChangesUtil.getChanges(allCustomTypeObjects1, allCustomTypeObjects2);
    }

    private static SiteInfoUpdate getSiteInfoUpdate() {
        SiteInfoUpdate siteInfoUpdate = new SiteInfoUpdate();
        siteInfoUpdate.setId(1L);
        siteInfoUpdate.setDescription("s");
        siteInfoUpdate.setAddressInfo(new AddressInfoUpdate());
        siteInfoUpdate.getAddressInfo().setId(2L);
        siteInfoUpdate.getAddressInfo().setCity("C");
        siteInfoUpdate.setPersonInfoUpdates(new ArrayList<>());
        siteInfoUpdate.getPersonInfoUpdates().add(new PersonInfoUpdate());
        siteInfoUpdate.getPersonInfoUpdates().add(new PersonInfoUpdate());
        siteInfoUpdate.getPersonInfoUpdates().get(0).setId(1L);
        siteInfoUpdate.getPersonInfoUpdates().get(1).setId(3L);
        siteInfoUpdate.getPersonInfoUpdates().get(0).setFirstName("Guru");
        siteInfoUpdate.getPersonInfoUpdates().get(1).setFirstName("Guru1");
        return siteInfoUpdate;
    }
}
