package com.sport.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerModel {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty position;
    private StringProperty teamName;
    private int player_id;

    public PlayerModel() {
    }

    public PlayerModel(String firstName, String lastName, String position, String teamName, int player_id) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.position = new SimpleStringProperty(position);
        this.teamName = new SimpleStringProperty(teamName);
        this.player_id = player_id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
}

