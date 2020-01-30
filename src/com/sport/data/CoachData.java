package com.sport.data;

import com.sport.entities.Coach;
import com.sport.services.CoachService;

import java.util.ArrayList;
import java.util.List;

public class CoachData {
    public static CoachData instance = new CoachData();
    private List<Coach> coaches = new ArrayList<>();

    public static CoachData getInstance(){
        return instance;
    }

    private CoachData(){

    }

    public void loadCoaches(){
        coaches.clear();
        CoachService cs = new CoachService();
        List<Coach> cList = cs.getAll();
        for(Coach c:cList)
            newCoach(c);
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public void newCoach(Coach coach){
        if(coach.getTeamId() > 0)
            TeamData.getInstance().getTeam(coach.getTeamId()).setCoach(coach);
        coaches.add(coach);
    }

    public Coach getCoach(int id){
        for(Coach c: coaches){
            if(c.getCoachId() == id)
                return c;
        }
        return null;
    }

    public List<Coach> getUsassigned(){
        List<Coach> returnValue = new ArrayList<>();
        for(Coach c: coaches){
            if(c.getTeamId() == 0)
                returnValue.add(c);
        }
        return returnValue;
    }
}
