package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.IGenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonInfoUpdateEntityMapper {

    @Autowired
    IGenderRepository IGenderRepository;

    public List<Person> updateOrPersonNewLocation(List<Change> changes, Person person) {
        Map<PersonInfoUpdate, List<Change>> personInfoUpdateMap = changes.stream().filter(change -> Objects.nonNull(change.getLeftObject()) && change.getRightObject() instanceof PersonInfoUpdate)
                .collect(Collectors.groupingBy(change -> (PersonInfoUpdate) change.getRightObject()));
        personInfoUpdateMap.keySet().forEach(key -> updatePersonEntity(personInfoUpdateMap.get(key), getOrganisation(key, person)));

        return changes.stream().filter(change -> Objects.isNull(change.getLeftObject()) && change.getRightObject() instanceof PersonInfoUpdate)
                .map(change -> newPersonEntity((PersonInfoUpdate) change.getRightObject())).toList();
    }

    private Person getOrganisation(PersonInfoUpdate personInfoUpdate, Person person) {
        if (Objects.nonNull(person) && person.getId().equals(personInfoUpdate.getId())) {
            return person;
        }
        return null;
    }

    public void updatePersonEntity(List<Change> changes, Person person) {
        changes.forEach(change -> {
            PersonInfoUpdate personInfoUpdate = (PersonInfoUpdate) change.getRightObject();
            switch (PersonInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case firstName -> person.setFirstName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case middleName -> person.setMiddleName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case lastName -> person.setLastName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case dateOfBirth ->
                        person.setDateOfBirth(ChangeValueExtractUtil.extractLocalDateTime(change.getRightValue()));
                case gender ->
                        person.setGender(IGenderRepository.findByType(ChangeValueExtractUtil.extractString(change.getRightValue())).get());
            }
        });
    }

    private Person newPersonEntity(PersonInfoUpdate personInfoUpdate) {
        return Person.builder()
                .firstName(personInfoUpdate.getFirstName())
                .middleName(personInfoUpdate.getMiddleName())
                .lastName(personInfoUpdate.getLastName())
                .dateOfBirth(personInfoUpdate.getDateOfBirth())
                .gender(IGenderRepository.findByType(personInfoUpdate.getGender()).get())
                .build();
    }

}
