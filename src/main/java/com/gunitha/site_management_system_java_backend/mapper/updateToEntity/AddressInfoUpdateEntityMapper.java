package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Address;
import com.gunitha.site_management_system_java_backend.model.update.AddressInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.AddressTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressInfoUpdateEntityMapper {

    @Autowired
    AddressTypeRepository addressTypeRepository;

    public void updateAddressEntity(List<Change> changes, Address address){
        changes.forEach(change -> {
            AddressInfoUpdate addressInfoUpdate = (AddressInfoUpdate) change.getRightObject();
            switch (AddressInfoUpdate.Fields.valueOf(change.getFieldName())){
                case addressType -> address.setAddressType(addressTypeRepository.finByType(ChangeValueExtractUtil.extractString(change.getRightValue())));
                case number -> address.setNumber(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case floor -> address.setFloor(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case street -> address.setStreet(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case village -> address.setVillage(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case city -> address.setCity(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case state -> address.setState(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case country -> address.setCountry(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case pinCode -> address.setPinCode(ChangeValueExtractUtil.extractString(change.getRightValue()));
            }
        });
    }
}
