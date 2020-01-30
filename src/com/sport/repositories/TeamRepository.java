package com.sport.repositories;

import com.sport.dto.TeamDto;
import com.sport.exceptions.SqlExceptionHandling;
import com.sport.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    private String query;

    public List<TeamDto> getAll(){
        query = "select * from teams";
        List<TeamDto> returnValue = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(Constants.URL,Constants.USER, Constants.PASS);
            Statement s = c.createStatement()){
            try(ResultSet resultSet = s.executeQuery(query)) {
                while (resultSet.next()) {
                    TeamDto td = new TeamDto();
                    td.setTeamId(resultSet.getInt("team_id"));
                    td.setTeamName(resultSet.getString("team_name"));
                    returnValue.add(td);
                }

            } catch (SQLException e){
                SqlExceptionHandling.printExceptionInfo(e);
            }
        }catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }

        return returnValue;
    }

    public TeamDto getTeamById(int id){
        query = "select * from teams where team_id='" + id + "'";
        TeamDto returnValue = new TeamDto();
        try(
                Connection c = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASS);
                Statement s  = c.createStatement();
        ){
            ResultSet rs  = s.executeQuery(query);
            rs.next();
            returnValue.setTeamId(rs.getInt("team_id"));
            returnValue.setTeamName(rs.getString("team_name"));
        } catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return returnValue;
    }
}
