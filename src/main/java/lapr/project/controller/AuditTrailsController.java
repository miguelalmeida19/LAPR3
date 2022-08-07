package lapr.project.controller;

import lapr.project.data.AuditTrails;
import lapr.project.data.DatabaseConnection;

import java.util.List;

public class AuditTrailsController {
    private final AuditTrails a;

    /**
     * Constructor
     */

    public AuditTrailsController(){
        DatabaseConnection data = App.getInstance().getCompany().getDatabaseConnection();
        a = new AuditTrails(data);
    }


    //[US304] As Ship Captain, I want to have access to audit trails for a given container of a given cargo manifest,
    // that is, I want to have access to a list of all operations performed on
    // a given container of a given manifest, in chronological order. For each operation I want to
    // know: the user/login that performed it, the date and time the operation was performed,
    // the type of operation (INSERT, UPDATE, DELETE), the container identifier and the
    // cargo manifest identifier.

    //      o Acceptance Criteria [BDDAD]:
    //              There is a table for recording audit trails, i.e., record all write-operation involving containers of a cargo manifest.
    //              Proper mechanisms for recording write-operations involving containers of a cargo manifest are implemented (INSERT, UPDATE, DELETE).
    //              A simple and effective audit trail consultation process is implemented.

    /**
     * Method that returns a list of all messages present in the audit trails table for a given CargoManifestID and ContainerID
     */
    public List<String> getAuditTrails(int cargo, int container){
        return a.getAuditTrails(cargo, container);
    }
}
