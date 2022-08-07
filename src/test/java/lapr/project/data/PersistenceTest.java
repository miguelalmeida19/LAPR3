package lapr.project.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lapr.project.auth.AuthFacade;

import lapr.project.model.Port;
import lapr.project.store.PortStore;

import lapr.project.store.ShipStore;
import lapr.project.structures.KDNode;
import lapr.project.structures.KDTree;
import org.junit.jupiter.api.Test;

class PersistenceTest {
    @Test
    void testGetShipsFromDataBase() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        Persistence.getShipsFromDataBase(statement, new ShipStore());
        verify(statement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).next();
    }

    @Test
    void testGetMessagesFromDataBase() {
        Statement statement = mock(Statement.class);
        ShipStore shipStore = new ShipStore();
        Persistence.getMessagesFromDataBase(statement, shipStore);
        assertNull(shipStore.getBstIMO().lastReturnedNode);
        assertNull(shipStore.getBstMMSI().root);
        assertNull(shipStore.getBstCallSign().root);
    }

    @Test
    void testGetPortsFromDataBase() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        PortStore portStore = new PortStore();
        Persistence.getPortsFromDataBase(statement, portStore);
        verify(statement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet, atLeast(1)).next();
        assertEquals(2, portStore.getPortHashMap().size());
        assertEquals(2, portStore.getPortList().size());
        KDNode rootNode = portStore.getRootNode();
        assertEquals(10.0, rootNode.getLong());
        assertEquals(10.0, rootNode.getLat());
        assertEquals(2, rootNode.getData().length);
        KDTree kdt = portStore.getKdt();
        assertFalse(kdt.isEmpty());
        assertEquals(0, kdt.height());
        assertEquals(0, kdt.getDepth());
        Object object = rootNode.getObject();
        assertTrue(object instanceof Port);
        assertEquals("1", ((Port) object).getCode());
        assertEquals("String", object.toString());
        assertEquals(10.0, ((Port) object).getLongitude());
        assertEquals(10.0, ((Port) object).getLatitude());
        assertEquals("String", ((Port) object).getCountry());
        assertEquals("String", ((Port) object).getContinent());
    }

    @Test
    void testGetPortsFromDataBase2() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        Persistence.getPortsFromDataBase(statement, null);
        verify(statement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).next();
    }

    @Test
    void testGetUsersFromDataBase() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        Persistence.getUsersFromDataBase(statement, new AuthFacade());
        verify(statement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet, atLeast(1)).next();
    }

    @Test
    void testGetUsersFromDataBase2() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        Persistence.getUsersFromDataBase(statement, null);
        verify(statement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).next();
    }

    @Test
    void testDoLogin() {
        assertNull(Persistence.doLogin("jane.doe@example.org", "iloveyou"));
        assertNull(Persistence.doLogin("foo", "foo"));
    }
}

