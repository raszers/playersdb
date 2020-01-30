package com.sport.repositories;

import com.sport.dto.PersonDto;

import java.util.List;

public interface Repository {
    List<PersonDto> getAll();
    PersonDto getRecordById(int id);
    int addRecord (PersonDto pd);
    int deleteRecord (int id);
    int updateRecord(PersonDto personDto);
}
