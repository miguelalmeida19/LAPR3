package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateCrewAccountControllerTest {
    DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
    Connection connection = mock(Connection.class);
    PreparedStatement s = mock(PreparedStatement.class);
    Statement st = mock(Statement.class);


    @Test
    void createCrewAccount() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        CreateCrewAccountController c = new CreateCrewAccountController();

        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getInt(1)).thenReturn(1111);
        when(databaseConnection.getConnection().createStatement()).thenReturn(st);

        String[] result = c.createCrewAccount(databaseConnection, "cheiro@gmail.com");
        assertTrue(result[0].equals("crew@dei.com"));
        assertTrue(result[1].equals("bd7wd5aF"));
    }
}