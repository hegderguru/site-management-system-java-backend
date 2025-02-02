package com.gunitha.site_management_system_java_backend.mapper.entityToRequest;

import com.gunitha.site_management_system_java_backend.entity.Address;
import com.gunitha.site_management_system_java_backend.model.update.AddressInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Slf4j
@Service("addressMapper")
public class AddressInfoUpdateMapper {

    private final static ModelMapper addresUpdateModelMapper = new ModelMapper();

    static {
        TypeMap<Address, AddressInfoUpdate> typeMap = addresUpdateModelMapper.createTypeMap(Address.class, AddressInfoUpdate.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Address::getId, AddressInfoUpdate::setId);
            mapper.map(Address::getAddressType, AddressInfoUpdate::setAddressType);
            mapper.map(Address::getFloor, AddressInfoUpdate::setFloor);
            mapper.map(Address::getNumber, AddressInfoUpdate::setNumber);
            mapper.map(Address::getStreet, AddressInfoUpdate::setStreet);
            mapper.map(Address::getVillage, AddressInfoUpdate::setVillage);
            mapper.map(Address::getCity, AddressInfoUpdate::setCity);
            mapper.map(Address::getState, AddressInfoUpdate::setState);
            mapper.map(Address::getPinCode, AddressInfoUpdate::setPinCode);
        });
    }

    public AddressInfoUpdate AddressInfoUpdate(Address address) {
        return addresUpdateModelMapper.map(address, AddressInfoUpdate.class);
    }

}
