package com.gunitha.site_management_system_java_backend.mapper.entityToRead;

import com.gunitha.site_management_system_java_backend.entity.Address;
import com.gunitha.site_management_system_java_backend.model.read.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressInfoReadMapper {

    private final static ModelMapper addresInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Address, AddressInfo> typeMap = addresInfoModelMapper.createTypeMap(Address.class, AddressInfo.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Address::getId, AddressInfo::setId);
            mapper.map(Address::getAddressType, AddressInfo::setAddressType);
            mapper.map(Address::getFloor, AddressInfo::setFloor);
            mapper.map(Address::getNumber, AddressInfo::setNumber);
            mapper.map(Address::getStreet, AddressInfo::setStreet);
            mapper.map(Address::getVillage, AddressInfo::setVillage);
            mapper.map(Address::getCity, AddressInfo::setCity);
            mapper.map(Address::getState, AddressInfo::setState);
            mapper.map(Address::getPinCode, AddressInfo::setPinCode);
        });
    }

    public AddressInfo addressInfo(Address address) {
        return addresInfoModelMapper.map(address, AddressInfo.class);
    }

}
