package com.sport.controllers;

import com.sport.data.PlayerData;
import com.sport.data.TeamData;
import com.sport.dto.PersonDto;
import com.sport.entities.Player;
import com.sport.entities.Team;
import com.sport.services.PlayerService;
import com.sport.utils.Common;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class UpdateController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> positionField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private ComboBox<Team> teamField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private Label message;

    public void initialize(){
        teamField.setItems(FXCollections.observableList(TeamData.getInstance().getTeams()));
        String[] positions = {"QB", "RB", "OL", "WR", "TE", "K", "DL", "LB", "S", "CB"};
        positionField.setItems(FXCollections.observableArrayList(positions));
    }

    public void fillFields(Player p){
        firstNameField.setText(p.getFirstName());
        lastNameField.setText(p.getLastName());
        positionField.setValue(p.getPosition());
        heightField.setText(Integer.toString(p.getHeight()));
        weightField.setText(Integer.toString(p.getWeight()));
        teamField.setValue(TeamData.getInstance().getTeam(p.getTeamId()));
        dateOfBirthField.setValue(p.getDateOfBirth().toLocalDate());
    }

    @FXML
    public void processAdd() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String position = positionField.getValue();
        int height = Integer.parseInt(heightField.getText().trim());
        int weight = Integer.parseInt(weightField.getText().trim());
        int teamId = teamField.getValue().getTeamId();
        java.sql.Date dateOfBirth = Common.toSqlDate(Date.from(dateOfBirthField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        PersonDto pd = new PersonDto(firstName,lastName,position,height,weight,teamId,dateOfBirth);
        PlayerService ps = new PlayerService();
        PlayerData.getInstance().newPlayer(ps.addRecord(pd));
    }

    public void processUpdate(Player player){
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String position = positionField.getValue();
        int height = Integer.parseInt(heightField.getText().trim());
        int weight = Integer.parseInt(weightField.getText().trim());
        int teamId = teamField.getValue().getTeamId();
        java.sql.Date dateOfBirth = Common.toSqlDate(Date.from(dateOfBirthField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        PersonDto pd = new PersonDto(player.getPlayerId(),firstName,lastName,position,height,weight,teamId,dateOfBirth);
        PlayerService ps = new PlayerService();
        PlayerData.getInstance().updatePlayer(ps.updatePlayer(pd));

    }

    public boolean correctValues(){
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();
        String h = heightField.getText().trim();
        String w = weightField.getText().trim();
        if(Pattern.matches("[a-zA-z]+",fName) &&
                Pattern.matches("[a-zA-z]+",lName) &&
                Pattern.matches("[0-9]+",h) &&
                Pattern.matches("[0-9]+",w))
            return true;
        else {
            return false;
        }

    }
}
