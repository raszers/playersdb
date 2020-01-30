package com.sport.exceptions;

import java.sql.SQLException;

public class SqlExceptionHandling {
    public static void printExceptionInfo(SQLException ex){
        System.err.println("Pracuje nad tym");
    }
}
