package com.sport.utils;

import com.sport.dto.PersonDto;
import com.sport.dto.TeamDto;
import com.sport.entities.Coach;
import com.sport.entities.Player;
import com.sport.entities.Team;
import com.sport.models.PlayerModel;
import com.sport.models.TeamModel;
import com.sport.services.TeamService;

public class Common {
    public static java.sql.Date toSqlDate(java.util.Date uD){
        return new java.sql.Date(uD.getTime());
    }

    public static java.util.Date toUtilDate(java.sql.Date sD){
        return new java.util.Date(sD.getTime());
    }

    public static Player mapPlayer(PersonDto pd){
        Player returnValue = new Player();
        returnValue.setPlayerId(pd.getPlayerId());
        returnValue.setFirstName(pd.getFirstName());
        returnValue.setLastName(pd.getLastName());
        returnValue.setPosition(pd.getPosition());
        returnValue.setWeight(pd.getWeight());
        returnValue.setHeight(pd.getHeight());
        returnValue.setTeamId(pd.getTeamId());
        returnValue.setDateOfBirth(pd.getDateOfBirth());
        return returnValue;
    }

    public static PersonDto mapPlayer(Player player){
        PersonDto pd = new PersonDto();
        pd.setPlayerId(player.getPlayerId());
        pd.setFirstName(player.getFirstName());
        pd.setLastName(player.getLastName());
        pd.setPosition(player.getPosition());
        pd.setHeight(player.getHeight());
        pd.setWeight(player.getWeight());
        pd.setTeamId(player.getTeamId());
        pd.setTeamHistory(player.getTeamHistory());
        pd.setDateOfBirth(player.getDateOfBirth());
        return pd;
    }

    public static PersonDto mapCoach(Coach coach){
        PersonDto pd = new PersonDto();
        pd.setCoachId(coach.getCoachId());
        pd.setFirstName(coach.getFirstName());
        pd.setLastName(coach.getLastName());
        pd.setTeamId(coach.getTeamId());
        pd.setCoachStyle(coach.getCoachStyle());
        return pd;
    }

    public static Coach mapCoach(PersonDto pd){
        Coach returnValue = new Coach();
        returnValue.setCoachId(pd.getCoachId());
        returnValue.setFirstName(pd.getFirstName());
        returnValue.setLastName(pd.getLastName());
        returnValue.setTeamId(pd.getTeamId());
        returnValue.setCoachStyle(pd.getCoachStyle());
        return returnValue;
    }

    public static Team mapTeam(TeamDto teamDto){
        Team returnValue = new Team();
        returnValue.setTeamId(teamDto.getTeamId());
        returnValue.setPlayers(teamDto.getPlayerList());
        returnValue.setTeamName(teamDto.getTeamName());
        return returnValue;
    }

    public static PlayerModel createPlayerModel(Player player){
        TeamService ts = new TeamService();
        String teamName = "-";
        Team team = new Team();
        if(player.getTeamId() != 0)
            team = ts.getTeamById(player.getTeamId());
        teamName = team.getTeamName();
        return new PlayerModel(player.getFirstName(),player.getLastName(),player.getPosition(),teamName,player.getPlayerId());
    }

    public static TeamModel createTeamModel(Team team){
        return new TeamModel(team.getTeamName(), team.getTeamId());
    }
}
