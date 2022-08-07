package lapr.project.controller;

import lapr.project.data.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateCrewAccountController {
    private static final Integer roleCrew = 11;

    public String[] createCrewAccount(DatabaseConnection databaseConnection, String captainEmail) throws SQLException {
        String crewM = "crew@dei.com";
        String crewP = "bd7wd5aF";
        String query = "SELECT EMPLOYEEID FROM USER_ACCOUNT WHERE EMAIL='"+captainEmail+"'";
        PreparedStatement pStatement =databaseConnection.getConnection().prepareStatement(query);
        ResultSet rs= pStatement.executeQuery(query);
        rs.next();
        int employeeid = rs.getInt(1);
        query = "INSERT INTO USER_ACCOUNT(EMAIL, PASSWORD, EMPLOYEEID, ROLEID) VALUES('"+crewM+"'"+
                ", '"+crewP+"', "+employeeid+", "+roleCrew+" )";
        Statement stat =databaseConnection.getConnection().createStatement();
        stat.executeUpdate(query);
        String[] returns= new String[2];
        returns[0] = crewM;
        returns[1] = crewP;
        return returns;
    }
}
