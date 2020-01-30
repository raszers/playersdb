package com.sport.services;

import com.sport.dto.TeamDto;
import com.sport.entities.Team;
import com.sport.repositories.TeamRepository;
import com.sport.utils.Common;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private TeamRepository tr;

    public List<Team> getAll(){
        tr = new TeamRepository();
        List<Team> returnValue = new ArrayList<>();
        List<TeamDto> teamDtos = tr.getAll();
        for(TeamDto td : teamDtos)
            returnValue.add(Common.mapTeam(td));
        return returnValue;
    }
    public Team getTeamById(int id){
        tr = new TeamRepository();
        return Common.mapTeam(tr.getTeamById(id));
    }
}
