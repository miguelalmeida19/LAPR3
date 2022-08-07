package lapr.project.data;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ShipsOnMondayTest {
    @Test
    void testConstructor() {
        DatabaseConnection databaseConnection = new DatabaseConnection("https://example.org/example", "janedoe",
                "iloveyou");

        new ShipsOnMonday(databaseConnection);
        assertNull(databaseConnection.getLastError());
    }


    @Test
    void testGetIMO2() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery((String) any())).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        (new ShipsOnMonday(databaseConnection)).getIMO();
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet, atLeast(1)).next();
    }

    @Test
    void testGetIMO3() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString((String) any())).thenThrow(new IllegalArgumentException("foo"));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery((String) any())).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        (new ShipsOnMonday(databaseConnection)).getIMO();
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery((String) any());
        verify(resultSet).getString((String) any());
        verify(resultSet).next();
    }

    @Test
    void testGetIMO4() throws SQLException {
        SQLException sqlException = mock(SQLException.class);
        when(sqlException.getMessage()).thenReturn("Not all who wander are lost");
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString((String) any())).thenThrow(sqlException);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery((String) any())).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        (new ShipsOnMonday(databaseConnection)).getIMO();
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery((String) any());
        verify(resultSet).getString((String) any());
        verify(resultSet).next();
        verify(sqlException).getMessage();
    }

    @Test
    void testGetBoats() {
        assertThrows(IllegalArgumentException.class,
                () -> (new ShipsOnMonday(new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou")))
                        .getBoats());
    }

    @Test
    void testExecuteSQLQuery() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery((String) any())).thenReturn(mock(ResultSet.class));
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        ShipsOnMonday.executeSQLQuery("Sql", databaseConnection);
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery((String) any());
    }
}

