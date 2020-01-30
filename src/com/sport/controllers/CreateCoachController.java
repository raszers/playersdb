package com.sport.controllers;

import com.sport.data.CoachData;
import com.sport.dto.PersonDto;
import com.sport.entities.Person;
import com.sport.services.CoachService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CreateCoachController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private ComboBox styleField;

    public void initialize(){
        String[] styles = {"Defensywa","Kreatywna ofensywa","Ofensywa biegowa","Ofensywa podaniowa"};
        styleField.setItems(FXCollections.observableArrayList(styles));
    }

    public void processResults(){
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String style = styleField.getValue().toString();
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(firstName);
        personDto.setLastName(lastName);
        personDto.setCoachStyle(style);
        CoachService coachService = new CoachService();
        CoachData.getInstance().newCoach(coachService.addCoach(personDto));
    }

    public boolean correctValues(){
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();
        if(Pattern.matches("[a-zA-z]+",fName) &&
                Pattern.matches("[a-zA-z]+",lName) &&
                styleField.getValue()!=null)
            return true;
        else {
            return false;
        }
    }
}
