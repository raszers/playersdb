package com.sport.dto;

import java.sql.Date;
import java.util.ArrayList;

public class PersonDto {
    private int playerId;
    private int coachId;
    private String firstName;
    private String lastName;
    private String position;
    private int height;
    private int weight;
    private int teamId;
    private Date dateOfBirth;
    private String coachStyle;
    private ArrayList<Integer> teamHistory;

    public PersonDto(){

    }

    public PersonDto(int playerId, String firstName, String lastName, String position, int height, int weight, int teamId, Date dateOfBirth) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.teamId = teamId;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonDto(String firstName, String lastName, String position, int height, int weight, int teamId, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.teamId = teamId;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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

    public String getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(String coachStyle) {
        this.coachStyle = coachStyle;
    }
}
