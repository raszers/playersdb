module playersdb {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    exports com.sport.controllers;
    opens com.sport;
    opens com.sport.controllers;
    opens com.sport.models;
}