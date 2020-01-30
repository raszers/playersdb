package com.sport.repositories;

import com.sport.dto.PersonDto;
import com.sport.exceptions.SqlExceptionHandling;
import com.sport.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachRepository implements Repository{
    private static String query;
    @Override
    public List<PersonDto> getAll() {
        String query = "select * from coaches";
        List<PersonDto> returnValue = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASS);
            Statement s = c.createStatement()){
            try(ResultSet resultSet = s.executeQuery(query)){
                while(resultSet.next()){
                    PersonDto pd = new PersonDto();
                    pd.setCoachId(resultSet.getInt("coach_id"));
                    pd.setFirstName(resultSet.getString("first_name"));
                    pd.setLastName(resultSet.getString("last_name"));
                    pd.setTeamId(resultSet.getInt("team_id"));
                    pd.setCoachStyle(resultSet.getString("coach_style"));
                    returnValue.add(pd);
                }
            }catch(SQLException e){
                SqlExceptionHandling.printExceptionInfo(e);
            }
        }catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return returnValue;
    }

    @Override
    public PersonDto getRecordById(int id) {
        PersonDto returnValue = new PersonDto();
        query = "select * from coaches where coach_id = '" + id + "'";
        System.out.println(query.toUpperCase());

        try(
                Connection c = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASS);
                Statement s  = c.createStatement();
        ){
            ResultSet rs  = s.executeQuery(query);
            rs.next();
            returnValue.setCoachId(rs.getInt("coach_id"));
            returnValue.setFirstName(rs.getString("first_name"));
            returnValue.setLastName(rs.getString("last_name"));
            returnValue.setCoachStyle(rs.getString("coach_style"));
            returnValue.setTeamId(rs.getInt("team_id"));
        } catch(SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return returnValue;
    }

    @Override
    public int addRecord(PersonDto personDto) {
        int new_id = -1;
        query = "insert into coaches (first_name, last_name, coach_style) " +
                "values ('" + personDto.getFirstName() + "', '" +
                personDto.getLastName() + "', '" +
                personDto.getCoachStyle() + "');";
        System.out.println(query.toUpperCase());

        try(    Connection c = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
                Statement s = c.createStatement()){
            s.executeUpdate(query);
            ResultSet rs = s.executeQuery("select * from coaches order by coach_id desc limit 1");
            rs.next();
            new_id = rs.getInt("coach_id");

        } catch (SQLException e){
            SqlExceptionHandling.printExceptionInfo(e);
        }
        return new_id;
    }

    @Override
    public int deleteRecord(int id) {
        return 0;
    }

    @Override
    public int updateRecord(PersonDto pd) {
        int affected = 0;
        String query = "update coaches set " +
                "first_name = '" + pd.getFirstName() +
                "', last_name = '" + pd.getLastName() +
                "', coach_style = '" + pd.getCoachStyle() +
                "', team_id = '" + pd.getTeamId() +
                "' where coach_id = " + pd.getCoachId() + ";";
        System.out.println(query.toUpperCase());
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
