package lapr.project.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class CargoManifestsTransportedTest {
    @Test
    void testConstructor() {
        CargoManifestsTransported actualCargoManifestsTransported = new CargoManifestsTransported(
                new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou"));
        assertEquals(0.0, actualCargoManifestsTransported.getAverage());
        assertEquals(0, actualCargoManifestsTransported.getContManifests());
    }

    @Test
    void testGetID() {
        assertEquals(0,
                (new CargoManifestsTransported(new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou")))
                        .getID("jane.doe@example.org"));
    }

    @Test
    void testGetCargoManifestTransported() {
        (new CargoManifestsTransported(new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou")))
                .getCargoManifestTransported("Ano");
        assertEquals("Ano", CargoManifestsTransported.ano);
    }

    @Test
    void testExecuteSQLQuery() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery(any())).thenReturn(mock(ResultSet.class));
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        CargoManifestsTransported.executeSQLQuery("Sql", databaseConnection);
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement(any());
        verify(preparedStatement).executeQuery(any());
    }
}

