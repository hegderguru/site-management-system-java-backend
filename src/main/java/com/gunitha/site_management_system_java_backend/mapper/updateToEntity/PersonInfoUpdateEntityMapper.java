package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.GenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonInfoUpdateEntityMapper {

    @Autowired
    GenderRepository genderRepository;

    public void updatePersonEntity(List<Change> changes, Person person){
        changes.forEach(change -> {
            PersonInfoUpdate personInfoUpdate = (PersonInfoUpdate) change.getRightObject();
            switch (PersonInfoUpdate.Fields.valueOf(change.getFieldName())){
                case firstName -> person.setFirstName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case middleName -> person.setMiddleName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case lastName -> person.setLastName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case dateOfBirth -> person.setDateOfBirth(ChangeValueExtractUtil.extractLocalDateTime(change.getRightValue()));
                case gender -> person.setGender(genderRepository.findByType(ChangeValueExtractUtil.extractString(change.getRightValue())).get());
            }
        });
    }

}
