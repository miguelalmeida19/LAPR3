package lapr.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lapr.project.data.Persistence;
import lapr.project.model.Port;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPortControllerTest {

    final ClosestPortController closestPortController = new ClosestPortController();

    @BeforeEach
    void before(){
        Persistence.getShipsFromDataBase(App.getInstance().getCompany().getStatement(), App.getInstance().getCompany().getShipStore());
        Persistence.getMessagesFromDataBase(App.getInstance().getCompany().getStatement(), App.getInstance().getCompany().getShipStore());
        Persistence.getPortsFromDataBase(App.getInstance().getCompany().getStatement(), App.getInstance().getCompany().getPortStore());
    }
}

