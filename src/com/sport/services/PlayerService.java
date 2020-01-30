package com.sport.services;

import com.sport.dto.PersonDto;
import com.sport.entities.Player;
import com.sport.repositories.PlayerRepository;
import com.sport.repositories.Repository;
import com.sport.utils.Common;

import java.util.ArrayList;
import java.util.List;


public class PlayerService{

    private Repository pr;

    public List<Player> getAll() {
        pr = new PlayerRepository();
        List<Player> returnValue = new ArrayList<>();
        for(PersonDto pd : pr.getAll())
            returnValue.add(Common.mapPlayer(pd));

        return returnValue;
    }

    public Player getRecordById(int id) {
        pr = new PlayerRepository();
        return Common.mapPlayer(pr.getRecordById(id));
    }

    public Player addRecord(PersonDto personDto) {
        pr = new PlayerRepository();
        int newId = pr.addRecord(personDto);
        Player returnValue = new Player();
        if(newId != -1)
            returnValue = Common.mapPlayer(pr.getRecordById(newId));
        return returnValue;
    }

    public Player updatePlayer(PersonDto personDto){
        Player returnValue = new Player();
        pr = new PlayerRepository();
        if(pr.updateRecord(personDto) == 1)
            returnValue = Common.mapPlayer(pr.getRecordById(personDto.getPlayerId()));
        return returnValue;
    }

    public int deleteRecord(int id) {
        pr = new PlayerRepository();
        return pr.deleteRecord(id);
    }

    public int removeFromTeam(Player player){
        int affected = 0;
        pr = new PlayerRepository();
        PersonDto personDto = Common.mapPlayer(player);
        personDto.setTeamId(0);
        affected = pr.updateRecord(personDto);
        return affected;
    }
}
