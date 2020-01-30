package com.sport.data;

import com.sport.entities.Player;
import com.sport.entities.Team;
import com.sport.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private static PlayerData instance = new PlayerData();

    private List<Player> players = new ArrayList<>();

    public static PlayerData getInstance(){
        return instance;
    }
    private PlayerData(){}

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void loadPlayers(){
        players.clear();
        PlayerService ps = new PlayerService();
        List<Player> pList = ps.getAll();
        for(Player temp : pList)
            newPlayer(temp);
    }

    public void newPlayer(Player player){
        if(player.getTeamId()!= 0){
            player.getTeamHistory().add(player.getTeamId());
            TeamData.getInstance().getTeam(player.getTeamId()).getPlayers().add(player);
            if (TeamData.getInstance().getTeam(player.getTeamId()).getCoach() != null)
                player.setCoachId(TeamData.getInstance().getTeam(player.getTeamId()).getCoach().getCoachId());
            else
                player.setCoachId(0);
        }
        players.add(player);
    }
    public void removePlayer(Player player){
        players.remove(player);
    }

    public Player getPlayer(int id){
        for(Player p : players){
            if(p.getPlayerId() == id)
                return p;
        }
        return null;
    }

    public void updatePlayer(Player uPlayer){
        for(Player p : players){
            if(p.getPlayerId() == uPlayer.getPlayerId()){
                p.setFirstName(uPlayer.getFirstName());
                p.setLastName(uPlayer.getLastName());
                p.setPosition(uPlayer.getPosition());
                p.setHeight(uPlayer.getHeight());
                p.setWeight(uPlayer.getWeight());
                p.setTeamId(uPlayer.getTeamId());
                p.setDateOfBirth(uPlayer.getDateOfBirth());
                p.setCoachId(TeamData.getInstance().getTeam(p.getTeamId()).getCoach().getCoachId());
                if(!p.getTeamHistory().get(p.getTeamHistory().size() - 1).equals(p.getTeamId()))
                    p.getTeamHistory().add(p.getTeamId());
                TeamData.getInstance().getTeam(p.getTeamId()).getPlayers().add(p);
            }
        }
    }
}
