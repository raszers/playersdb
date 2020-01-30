package com.sport.data;

import com.sport.dto.TeamDto;
import com.sport.entities.Team;
import com.sport.services.TeamService;

import java.util.ArrayList;
import java.util.List;

public class TeamData {

    private static TeamData instance = new TeamData();
    private List<Team> teams = new ArrayList<>();

    public static TeamData getInstance() {
        return instance;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    private TeamData(){

    }

    public void loadTeams(){
        teams.clear();
        TeamService ts = new TeamService();
        List<Team> teamList = ts.getAll();
        for(Team t : teamList)
            teams.add(t);
    }

    public Team getTeam(int id){
        for(Team t : teams){
            if(t.getTeamId() == id)
                return t;
        }
        return null;
    }

}
