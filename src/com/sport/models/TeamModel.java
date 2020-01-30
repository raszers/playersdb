package com.sport.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeamModel {
    private StringProperty teamName;
    private int teamId;

    public TeamModel(String teamName, int teamId) {
        this.teamName = new SimpleStringProperty(teamName);
        this.teamId = teamId;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
