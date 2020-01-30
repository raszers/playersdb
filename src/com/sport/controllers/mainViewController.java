package com.sport.controllers;

import com.sport.data.CoachData;
import com.sport.data.PlayerData;
import com.sport.data.TeamData;
import com.sport.entities.Coach;
import com.sport.entities.Player;
import com.sport.entities.Team;
import com.sport.models.PlayerModel;
import com.sport.models.TeamModel;
import com.sport.services.CoachService;
import com.sport.services.PlayerService;
import com.sport.services.TeamService;
import com.sport.utils.Common;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class mainViewController {

    private TeamService teamService;
    private PlayerService playerService;
    private CoachService coachService;

    @FXML private TableView<PlayerModel> personTableView;
    @FXML private TableView<TeamModel> teamsTableView;
    @FXML private TableColumn<TeamModel, String> teamColumn;
    @FXML private TableColumn<PlayerModel, String> firstColumn;
    @FXML private TableColumn<PlayerModel, String> secondColumn;
    @FXML private TableColumn<PlayerModel, String> thirdColumn;
    @FXML private TableColumn<PlayerModel, String> fourthColumn;
    @FXML private BorderPane mainBorderPane;
    @FXML private TextArea detailsArea;


    public void initialize(){
        TeamData.getInstance().loadTeams();
        CoachData.getInstance().loadCoaches();
        PlayerData.getInstance().loadPlayers();

        firstColumn = new TableColumn<>("Nazwisko");
        secondColumn = new TableColumn<>("Imie");
        thirdColumn = new TableColumn<>("Poz");
        fourthColumn = new TableColumn<>("Druzyna");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstColumn.prefWidthProperty().bind(personTableView.widthProperty().multiply(0.27));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondColumn.prefWidthProperty().bind(personTableView.widthProperty().multiply(0.27));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        thirdColumn.prefWidthProperty().bind(personTableView.widthProperty().multiply(0.1));
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        fourthColumn.prefWidthProperty().bind(personTableView.widthProperty().multiply(0.36));

        teamColumn = new TableColumn<>("Nazwa druzyny");
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamColumn.prefWidthProperty().bind(teamsTableView.widthProperty());
        teamsTableView.getColumns().add(teamColumn);

        personTableView.getColumns().addAll(firstColumn,secondColumn,thirdColumn,fourthColumn);

        List<TeamModel> teamModels = new ArrayList<>();
        for(Team t : TeamData.getInstance().getTeams())
            teamModels.add(Common.createTeamModel(t));
        teamsTableView.getItems().setAll(teamModels);

    }
    @FXML
    public void viewPlayerDetails(){
        if(personTableView.getSelectionModel().getSelectedItem() != null){
            Player player = PlayerData.getInstance().getPlayer(personTableView.getSelectionModel().getSelectedItem().getPlayer_id());
            StringBuilder sb = new StringBuilder();
            sb.append("Imie: " + player.getFirstName() + "\n");
            sb.append("Nazwisko: " + player.getLastName() + "\n");
            sb.append("Pozycja: " + player.getPosition() + "\n");
            sb.append("Wzrost: " + player.getHeight() + " cm\n");
            sb.append("Waga: " + player.getWeight() + " kg\n");
            if(player.getTeamId() != 0){
                sb.append("Druzyna: " + TeamData.getInstance().getTeam(player.getTeamId()) + "\n");
                if(player.getCoachId()!=0)
                    sb.append("Obecny trener: " + CoachData.getInstance().getCoach(player.getCoachId()) + "\n");
                else
                    sb.append("Obecny trener: -\n");
            } else {
                sb.append("Druzyna: -\n");
                sb.append("Obecny trener: -\n");
            }
            sb.append("Historia zawodnika: ");
            for(int team : player.getTeamHistory()){
                sb.append(TeamData.getInstance().getTeam(team).getTeamName() + "\n");
            }
            detailsArea.setText(sb.toString());
        }
    }

    @FXML
    public void openAddPlayerDialog(){
        openUpdatePlayerDialog("add");
    }

    @FXML
    public void removeFromTeam(){
        Player player = PlayerData.getInstance().getPlayer(personTableView.getSelectionModel().getSelectedItem().getPlayer_id());
        playerService = new PlayerService();
        playerService.removeFromTeam(player);
        Team team = TeamData.getInstance().getTeam(player.getTeamId());
        team.removePlayer(player);
        refreshList();
    }

    @FXML
    public void removeCoachFromTeam(){
        Coach coach = TeamData.getInstance().getTeam(teamsTableView.getSelectionModel().getSelectedItem().getTeamId()).getCoach();
        CoachService cs = new CoachService();
        cs.removeFromTeam(coach);
        Team team = TeamData.getInstance().getTeam(coach.getTeamId());
        team.removeCoach();
    }

    @FXML
    public void updatePlayer(){
        if(personTableView.getSelectionModel().getSelectedItem()!=null)
            openUpdatePlayerDialog("update");

    }

    @FXML
    public void deletePlayerRecord(){
        Player player = PlayerData.getInstance().getPlayer(personTableView.getSelectionModel().getSelectedItem().getPlayer_id());
        playerService = new PlayerService();
        if(playerService.deleteRecord(personTableView.getSelectionModel().getSelectedItem().getPlayer_id()) == 1){
            PlayerData.getInstance().removePlayer(player);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usunieto rekord");
            alert.setHeaderText(null);
            alert.setContentText("Rekord zostal pomyslnie usuniety");
            alert.showAndWait();
            refreshList();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie usunieto rekordu");
            alert.setHeaderText(null);
            alert.setContentText("Przy usuwaniu rekordu wystapil blad");
            alert.showAndWait();

        }

    }

    @FXML
    public void deleteCoachRecord(){

    }

    @FXML
    public void handleSelectTeam(){
        refreshList();
    }

    @FXML
    public void showUnassignedPlayers(){
        List<PlayerModel> playerModels = new ArrayList<>();
        for(Player p : PlayerData.getInstance().getPlayers())
            if(p.getTeamId() == 0)
                playerModels.add(Common.createPlayerModel(p));
        personTableView.getItems().setAll(playerModels);

    }

    @FXML
    public void openAddCoachDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createCoach.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("Nie udalo sie otworzyc okna");
            e.printStackTrace();
            return;
        }
        dialog.setTitle("Dodaj trenera");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        CreateCoachController controller = fxmlLoader.getController();

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK && controller.correctValues()){
            controller.processResults();
        }
    }

    @FXML
    public void assignCoach(){
        if(TeamData.getInstance().getTeam(teamsTableView.getSelectionModel().getSelectedItem().getTeamId()).getCoach() == null){
            CoachService cs = new CoachService();
            Coach coach;
            List<Coach> list = CoachData.getInstance().getUsassigned();
            ChoiceDialog<Coach> dialog = new ChoiceDialog<>();
            dialog.getItems().setAll(list);
            dialog.setTitle("Wybierz trenera");
            Optional<Coach> result = dialog.showAndWait();
            if(result.isPresent()){
                coach = result.get();
                coach.setTeamId(teamsTableView.getSelectionModel().getSelectedItem().getTeamId());
                cs.updateCoach(Common.mapCoach(coach));
                TeamData.getInstance().getTeam(teamsTableView.getSelectionModel().getSelectedItem().getTeamId()).setCoach(result.get());
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Druzyna ma juz trenera!");
            alert.setTitle("Blad");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    public void refreshList(){
        TeamModel teamModel = teamsTableView.getSelectionModel().getSelectedItem();
        if(teamModel!=null){
            List<PlayerModel> playerModels = new ArrayList<>();

            for(Player p : PlayerData.getInstance().getPlayers()){
                if(p.getTeamId()!= 0 ){
                    Team team = TeamData.getInstance().getTeam(p.getTeamId());
                    if(team.getTeamName().equals(teamModel.getTeamName()))
                        playerModels.add(Common.createPlayerModel(p));
                }
            }
            personTableView.getItems().setAll(playerModels);
        }

    }

    public void openUpdatePlayerDialog(String action){

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("update.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("Nie udalo sie otworzyc okna");
            e.printStackTrace();
            return;
        }
        dialog.setTitle("Dodaj/zaktualizuj rekord");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        UpdateController controller = fxmlLoader.getController();
        if(action.equals("update"))
            controller.fillFields(PlayerData.getInstance().getPlayer(personTableView.getSelectionModel().getSelectedItem().getPlayer_id()));

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK && controller.correctValues()){
            if(action.equals("add")){
                controller.processAdd();
            }
            else if(personTableView.getSelectionModel().getSelectedItem()!=null && action.equals("update")){
                Player player = PlayerData.getInstance().getPlayer(personTableView.getSelectionModel().getSelectedItem().getPlayer_id());
                controller.processUpdate(player);
            }
        } else if (result.isPresent() && result.get() == ButtonType.OK) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Wprowadzono bledne dane.\nRekord nie zostal dodany/zaktualizowany");
            alert.setTitle("Blad");
            alert.setContentText(null);
            alert.showAndWait();
        }



    }
}
