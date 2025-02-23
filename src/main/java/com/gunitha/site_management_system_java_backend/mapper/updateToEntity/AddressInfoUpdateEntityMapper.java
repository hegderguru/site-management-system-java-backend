package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Address;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.update.AddressInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.IAddressTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressInfoUpdateEntityMapper {

    @Autowired
    IAddressTypeRepository IAddressTypeRepository;

    public List<Address> updateOrAddNewAddressEntity(List<Change> changes, Site site, Person person) {
        Map<AddressInfoUpdate, List<Change>> addressUpdatesMap = changes.stream().filter(change -> Objects.nonNull(change.getLeftObject()) && change.getRightObject() instanceof AddressInfoUpdate)
                .collect(Collectors.groupingBy(change -> (AddressInfoUpdate) change.getRightObject()));
        addressUpdatesMap.keySet().forEach(key -> updateAddressEntity(addressUpdatesMap.get(key), getAddresses(key, site, person)));

        return changes.stream().filter(change -> Objects.isNull(change.getLeftObject()) && change.getRightObject() instanceof AddressInfoUpdate)
                .map(change -> newAddressEntity((AddressInfoUpdate) change.getRightObject())).toList();
    }

    private Address getAddresses(AddressInfoUpdate addressInfoUpdate, Site site, Person person) {
        if (Objects.nonNull(site) && site.getSiteAddress().getId().equals(addressInfoUpdate.getId())) {
            return site.getSiteAddress();
        } else if (Objects.nonNull(person)) {
            Optional<Address> addressOptional = person.getAddresses().stream().filter(address -> address.getId().equals(addressInfoUpdate.getId())).findFirst();
            if (addressOptional.isPresent()) {
                return addressOptional.get();
            }
        }
        return null;
    }

    public void updateAddressEntity(List<Change> changes, Address address) {
        changes.forEach(change -> {
            AddressInfoUpdate addressInfoUpdate = (AddressInfoUpdate) change.getRightObject();
            switch (AddressInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case addressType ->
                        address.setAddressType(IAddressTypeRepository.finByType(ChangeValueExtractUtil.extractString(change.getRightValue())).get());
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

    public Address newAddressEntity(AddressInfoUpdate addressInfoUpdate) {
        return Address.builder()
                .AddressType(IAddressTypeRepository.finByType(addressInfoUpdate.getAddressType()).get())
                .floor(addressInfoUpdate.getFloor())
                .number(addressInfoUpdate.getNumber())
                .street(addressInfoUpdate.getStreet())
                .village(addressInfoUpdate.getVillage())
                .city(addressInfoUpdate.getCity())
                .state(addressInfoUpdate.getState())
                .country(addressInfoUpdate.getCountry())
                .pinCode(addressInfoUpdate.getPinCode())
                .build();
    }
}
