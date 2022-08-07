package lapr.project.model;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.domain.shared.Constants;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.Persistence;
import lapr.project.store.ContainerStore;
import lapr.project.store.PortStore;
import lapr.project.store.ShipStore;

import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
public class Company {

    private final ShipStore shipStore;
    private final PortStore portStore;
    private final AuthFacade authFacade;
    private final ContainerStore containerStore;
    private DatabaseConnection databaseConnection;
    private Statement statement;
    private Properties p;
    public Company(){
        this.shipStore = new ShipStore();
        this.portStore = new PortStore();
        this.authFacade = new AuthFacade();
        try {
            FileInputStream fis = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("application.properties").getFile());
            p = new Properties();
            p.load(fis);
            fis.close();
        }catch (Exception e){
            p = new Properties();
            p.put("sound.enabled", "false");
        }

        bootstrap();
        this.containerStore = new ContainerStore();

        //Connection Database
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            statement = databaseConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            statement = null;
        }
        //Collecting DATA from Database
        Persistence.getShipsFromDataBase(statement, shipStore);
        Persistence.getMessagesFromDataBase(statement, shipStore);
        Persistence.getPortsFromDataBase(statement, portStore);
        //Persistence.getUsersFromDataBase(statement, authFacade);
    }
    private void bootstrap(){
        this.authFacade.addUserRole(Constants.ROLE_CLIENT_ID,Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.ROLE_FLEET_MANAGER_ID,Constants.ROLE_FLEET_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER_ID,Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_WAREHOUSE_STAFF_ID,Constants.ROLE_WAREHOUSE_STAFF);
        this.authFacade.addUserRole(Constants.ROLE_WAREHOUSE_MANAGER_ID,Constants.ROLE_WAREHOUSE_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_PORT_STAFF_ID,Constants.ROLE_PORT_STAFF);
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER_ID,Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER_ID,Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER);
        this.authFacade.addUserRole(Constants.ROLE_TRUCK_DRIVER_ID,Constants.ROLE_TRUCK_DRIVER);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN_ID,Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserRole(Constants.ROLE_CREW_ACCOUNT_ID,Constants.ROLE_CREW_ACCOUNT);

        //Default Users
        this.authFacade.addUserWithRole("Client", "paulo@isep.ipp.pt", "paulo123", Constants.ROLE_CLIENT_ID);
        this.authFacade.addUserWithRole("Fleet Manager", "tiago@isep.ipp.pt", "tiago123", Constants.ROLE_FLEET_MANAGER_ID);
        this.authFacade.addUserWithRole("Traffic Manager", "ricardo@isep.ipp.pt", "ricardo123", Constants.ROLE_TRAFFIC_MANAGER_ID);
        this.authFacade.addUserWithRole("Warehouse Staff", "pedro@isep.ipp.pt", "pedrito", Constants.ROLE_WAREHOUSE_STAFF_ID);
        this.authFacade.addUserWithRole("Warehouse Manager ", "rosa@isep.ipp.pt", "rosa1234", Constants.ROLE_WAREHOUSE_MANAGER_ID);
        this.authFacade.addUserWithRole("Port Staff", "miguel@isep.ipp.pt", "123456",Constants.ROLE_PORT_STAFF_ID);
        this.authFacade.addUserWithRole("Port Manager", "andre@isep.ipp.pt", "andre123",Constants.ROLE_PORT_MANAGER_ID);
        this.authFacade.addUserWithRole("Chief Electrical Engineer", "jose@isep.ipp.pt", "jose123",Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER_ID);
        this.authFacade.addUserWithRole("Truck Driver", "rui@isep.ipp.pt", "rui123",Constants.ROLE_TRUCK_DRIVER_ID);
        this.authFacade.addUserWithRole("Ship Captain", "iglo@isep.ipp.pt", "iglo123",Constants.ROLE_SHIP_CAPTAIN_ID);
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public ShipStore getShipStore() {
        return shipStore;
    }
    public AuthFacade getAuthFacade() {
        return authFacade;
    }
    public PortStore getPortStore() {
        return portStore;
    }
    public ContainerStore getContainerStore() {
        return containerStore;
    }
    public Statement getStatement() {
        return statement;
    }

    public Properties getProperties() {
        return p;
    }
}