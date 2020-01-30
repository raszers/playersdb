package com.sport.repositories;

import com.sport.dto.PersonDto;
import com.sport.utils.Common;
import org.junit.Test;


import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PlayerRepositoryTest {

    @Test
    public void addRecord() {
        PlayerRepository pr = new PlayerRepository();
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        Date dob = new Date(Common.toSqlDate(date).getTime());
        PersonDto pd = new PersonDto( "Test", "Testownik", "K", 199, 95, 9, dob);
        int newId = pr.addRecord(pd);
        assertTrue(newId != -1);
        assertTrue(pd.getLastName().equals(pr.getRecordById(newId).getLastName()));
    }

    @Test
    public void getRecordById() {
       PlayerRepository pr = new PlayerRepository();
       PersonDto pd = pr.getRecordById(4);
       assertTrue(pd.getLastName().equals("Zamojski"));
    }
}