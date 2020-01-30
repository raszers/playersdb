package com.sport.services;

import com.sport.dto.PersonDto;
import com.sport.entities.Coach;
import com.sport.repositories.CoachRepository;
import com.sport.utils.Common;

import java.util.ArrayList;
import java.util.List;

public class CoachService {

    CoachRepository cr;

    public List<Coach> getAll(){
        List<Coach> returnValue = new ArrayList<>();
        cr = new CoachRepository();
        for(PersonDto pd : cr.getAll())
            returnValue.add(Common.mapCoach(pd));
        return returnValue;
    }

    public Coach addCoach(PersonDto personDto){
        cr = new CoachRepository();
        int newId = cr.addRecord(personDto);
        Coach returnValue;
        PersonDto pd = new PersonDto();
        if(newId!=-1)
            pd = cr.getRecordById(newId);
        pd.setTeamId(0);
        returnValue = Common.mapCoach(pd);
        return returnValue;
    }

    public Coach updateCoach(PersonDto personDto){
        Coach returnValue = new Coach();
        cr = new CoachRepository();
        if(cr.updateRecord(personDto) == 1)
            returnValue = Common.mapCoach(cr.getRecordById(personDto.getCoachId()));
        return returnValue;
    }

    public int removeFromTeam(Coach coach){
        int affected = 0;
        cr = new CoachRepository();
        PersonDto personDto = Common.mapCoach(coach);
        personDto.setTeamId(0);
        affected = cr.updateRecord(personDto);
        return affected;
    }

}
