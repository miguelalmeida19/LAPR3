package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.store.ContainerStore;
import java.sql.SQLException;

public class ContainerStatusController {
    private final DatabaseConnection databaseConnection;
    private final ContainerStore store;

    public ContainerStatusController(DatabaseConnection databaseConnection){
        Company company = App.getInstance().getCompany();
        this.databaseConnection = databaseConnection;
        store = company.getContainerStore();
    }

    /**
     * Method that gets the situation of a specific container using its id.
     * @param containerID id of the container
     * @return the status of the container
     * @throws SQLException error related to sql queries.
     */
    public String containerStatus(String containerID) throws SQLException {

        return store.getContainerStatus(containerID, databaseConnection);

    }



}
