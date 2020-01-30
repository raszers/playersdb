package com.sport.entities;

import java.util.List;

public class Team {
    private int teamId;
    private String teamName;
    private Coach coach;
    private List<Player> players;

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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
        notifyPlayers(this.coach);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer (Player player) {
        player.setTeamId(this.teamId);
        player.getTeamHistory().add(this.teamId);
        this.players.add(player);
    }

    public void removePlayer(Player player)  {
        player.setTeamId(0);
        this.players.remove(player);
    }

    public void removeCoach(){
        coach.setTeamId(0);
        setCoach(null);
    }

    public void notifyPlayers(Coach coach){
        for(Player p : players)
            if(coach == null)
                p.update(0);
            else
            p.update(coach.getCoachId());
    }

    @Override
    public String toString() {
        return teamName;
    }
}
