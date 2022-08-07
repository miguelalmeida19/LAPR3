package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.store.ContainerStore;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContainerStatusControllerTest {
    DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
    Connection connection = mock(Connection.class);
    PreparedStatement s = mock(PreparedStatement.class);

    @Test
    void containerStatusShip() throws SQLException {
        //test ship
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(true).thenReturn(false).thenReturn(true);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE LOADED").thenReturn("SHIP");
        when(rs.getString(eq("VEHICLEID"))).thenReturn("12345");
        when(rs.getString(eq("NAME"))).thenReturn("N.R.P. CHEIRO ORGULHOSO");

        //System.out.println(c.containerStatus("1111"));
        assertTrue("SHIP, N.R.P. CHEIRO ORGULHOSO".equals(c.containerStatus("1111")));

    }
    @Test
    void containerStatusTruck() throws SQLException {
        //test Truck
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(true).thenReturn(false).thenReturn(true);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE LOADED").thenReturn("TRUCK");
        when(rs.getString(eq("VEHICLEID"))).thenReturn("12345");
        when(rs.getString(eq("ID"))).thenReturn("112314");

        //System.out.println(c.containerStatus("1111"));
        assertTrue("TRUCK, 112314".equals(c.containerStatus("1111")));

    }
    @Test
    void containerStatusPort() throws SQLException {
        //test Port
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(true).thenReturn(false).thenReturn(true);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE OFFLOADED");
        when(rs.getString(eq("PORTID"))).thenReturn("12345");
        when(rs.getString(eq("NAME"))).thenReturn("Cheiro do atlântico");

        //System.out.println(c.containerStatus("1111"));
        assertTrue("PORT, Cheiro do atlântico".equals(c.containerStatus("1111")));
    }

    @Test
    void containerStatusWarehouse() throws SQLException {
        //test Port
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(true).thenReturn(false).thenReturn(true);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE OFFLOADED");
        when(rs.getString(eq("WAREHOUSEID"))).thenReturn("12345");
        when(rs.getString(eq("ID"))).thenReturn("113253");

        assertTrue("WAREHOUSE, 113253".equals(c.containerStatus("1111")));
    }

    @Test
    void containerStatusMultipleValues() throws SQLException {
        //test ship
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        //validation container
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32").thenReturn("1984-01-18 18:32");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE LOADED").thenReturn("SHIP");
        when(rs.getString(eq("VEHICLEID"))).thenReturn("12345");
        when(rs.getString(eq("NAME"))).thenReturn("N.R.P. CHEIRO ORGULHOSO");

        //System.out.println(c.containerStatus("1111"));
        assertTrue("SHIP, N.R.P. CHEIRO ORGULHOSO".equals(c.containerStatus("1111")));
    }

    @Test
    void containerStatusNull() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        //when(rs.next()).thenReturn(true).thenReturn(false);
        //when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("NAME"))).thenReturn("N.R.P. CHEIRO ORGULHOSO");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE UNLOADED");
        //System.out.println(c.containerStatus("1111"));
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> c.containerStatus("1111")
        );
    }
    @Test
    void containerStatusNull2() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(1);
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareStatement(anyString())).thenReturn(s);
        when(rs.next()).thenReturn(false);
        when(s.executeQuery(anyString())).thenReturn(rs);
        when(rs.getString(eq("cargo_manifestid"))).thenReturn("123456");
        when(rs.getString(eq("BASE_DATE_TIME"))).thenReturn("2020-01-18 18:32");
        when(rs.getString(eq("NAME"))).thenReturn("N.R.P. CHEIRO ORGULHOSO");
        when(rs.getString(eq("TYPE"))).thenReturn("TO BE UNLOADED");
        //System.out.println(c.containerStatus("1111"));
        SQLException thrown = assertThrows(
                SQLException.class,
                () -> c.containerStatus("1111")
        );
    }

    @Test
    void containerStatusInvalidClient() throws SQLException {

        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(11);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> c.containerStatus("1111")
        );
        assertTrue(thrown.getMessage().equals("11 – container is not leased by client"));

    }
    @Test
    void containerStatusInvalidContainer() throws SQLException {

        ContainerStatusController c = new ContainerStatusController(databaseConnection);
        CallableStatement cs = mock(CallableStatement.class);

        when(databaseConnection.getConnection()).thenReturn(connection);
        when(databaseConnection.getConnection().prepareCall(anyString())).thenReturn(cs);
        when(cs.getInt(anyInt())).thenReturn(10);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> c.containerStatus("1111")
        );
        assertTrue(thrown.getMessage().equals("10 – invalid container id"));

    }
}