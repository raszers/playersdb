package com.sport.entities;

import java.sql.Date;
import java.util.ArrayList;

public class Player extends Person{
    private int playerId;
    private String position;
    private int height;
    private int weight;
    private Date dateOfBirth;
    private ArrayList<Integer> teamHistory;

    public Player() {
        this.teamHistory = new ArrayList<>();
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<Integer> getTeamHistory() {
        return teamHistory;
    }

    public void setTeamHistory(ArrayList<Integer> teamHistory) {
        this.teamHistory = teamHistory;
    }

    public void update(int coachId) {
        this.setCoachId(coachId);
    }

    @Override
    public String toString() {
        return Integer.toString(playerId) + ". " + getFirstName() + " " + getLastName() + " TeamId: " + getTeamId();
    }
}
