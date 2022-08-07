package lapr.project.data;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class AuditTrailsTest {


    @Test
    void testGetAuditTrails() {
        assertTrue((new AuditTrails(new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou")))
                .getAuditTrails(1, 1)
                .isEmpty());
        assertTrue((new AuditTrails(new DatabaseConnection("https://example.org/example", "janedoe", "iloveyou")))
                .getAuditTrails(0, 1)
                .isEmpty());
    }

    @Test
    void testExecuteSQLQuery() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery((String) any())).thenReturn(mock(ResultSet.class));
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        AuditTrails.executeSQLQuery("Sql", databaseConnection);
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery((String) any());
    }
}

