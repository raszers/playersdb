package com.sport.repositories;

import com.sport.dto.PersonDto;
import com.sport.entities.Person;
import com.sport.exceptions.SqlExceptionHandling;
import com.sport.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository implements Repository {
    private static String query;

    public List<PersonDto> getAll(){
        query = "select * from players";
        List<PersonDto> returnValue = new ArrayList<>();

        try(    Connection c = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
                Statement s = c.createStatement()){
            try(ResultSet resultSet = s.executeQuery(query)){
                while(resultSet.next()) {
                    PersonDto pd = new PersonDto();
                    pd.setPlayerId( resultSet.getInt("player_id"));
                    pd.setFirstName(resultSet.getString("first_name"));
                    pd.setLastName(resultSet.getString("last_name"));
                    pd.setPosition(resultSet.getString("pos"));
                    pd.setHeight(resultSet.getInt("height"));
                    pd.setWeight(resultSet.getInt("weight"));
                    pd.setTeamId(resultSet.getInt("team_id"));
                    pd.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    returnValue.add(pd);

                }
            } catch (SQLException e){
                SqlExceptionHandling.printExceptionInfo(e);
            }
        }catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return returnValue;
    }
    public int addRecord(PersonDto personDto){
        int new_id = -1;
        query = "insert into players (first_name, last_name, pos, height, weight, team_id, date_of_birth) " +
                "values ('" + personDto.getFirstName() + "', '" +
                personDto.getLastName() + "', '" +
                personDto.getPosition() + "', '" +
                personDto.getHeight() + "', '" +
                personDto.getWeight() + "', '" +
                personDto.getTeamId() + "', '" +
                personDto.getDateOfBirth() + "');";
        System.out.println(query.toUpperCase());

        try(    Connection c = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
                Statement s = c.createStatement()){
            s.executeUpdate(query);
            ResultSet rs = s.executeQuery("select * from players order by player_id desc limit 1");
            rs.next();
            new_id = rs.getInt("player_id");

        } catch (SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return new_id;
    }
    public int deleteRecord(int recordId){
        int affectedRows = 0;
        query = "delete from players where player_id = '" + recordId + "';";
        System.out.println(query.toUpperCase());
        try(    Connection c = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
                Statement s = c.createStatement()){
            affectedRows = s.executeUpdate(query);
            System.out.printf("Affected %d rows \n", affectedRows);
        } catch (SQLException sqlE){
            SqlExceptionHandling.printExceptionInfo(sqlE);
        }
        return affectedRows;
    }
    public PersonDto getRecordById(int id){
        PersonDto returnValue = new PersonDto();
        query = "select * from players where player_id = '" + id + "'";
        System.out.println(query.toUpperCase());

        try(
                Connection c = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASS);
                Statement s  = c.createStatement();
            ){
            ResultSet rs  = s.executeQuery(query);
            rs.next();
            returnValue.setPlayerId(rs.getInt("player_id"));
            returnValue.setFirstName(rs.getString("first_name"));
            returnValue.setLastName(rs.getString("last_name"));
            returnValue.setPosition(rs.getString("pos"));
            returnValue.setHeight(rs.getInt("height"));
            returnValue.setWeight(rs.getInt("weight"));
            returnValue.setTeamId(rs.getInt("team_id"));
            returnValue.setDateOfBirth(rs.getDate("date_of_birth"));
        } catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return returnValue;
    }
    public int updateRecord(PersonDto pd){
        int affected = 0;
        String query = "update players set " +
                "first_name = '" + pd.getFirstName() +
                "', last_name = '" + pd.getLastName() +
                "', pos = '" + pd.getPosition() +
                "', height = '" + pd.getHeight() +
                "', weight = '" + pd.getWeight() +
                "', team_id = '" + pd.getTeamId() +
                "', date_of_birth = '" + pd.getDateOfBirth() +
                "' where player_id = " + pd.getPlayerId() + ";";
        try(Connection c  = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASS);
            Statement s = c.createStatement()
        ){
            affected = s.executeUpdate(query);
        }catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return affected;
    }
}
