package com.sport.dto;

import com.sport.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {
    private int teamId;
    private String teamName;
    private List<Player> playerList = new ArrayList<>();

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
