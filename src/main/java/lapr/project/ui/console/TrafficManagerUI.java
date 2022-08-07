package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TrafficManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Import Ships ", new ImportShipsUI()));
        options.add(new MenuItem("Get Ship Details ", new ShipDetailsUI()));
        options.add(new MenuItem("Positional Ship Messages ", new ShipMessagesUI()));
        options.add(new MenuItem("Ship Summary ", new ShipSummaryUI()));
        options.add(new MenuItem("Top N Ships ", new TopNShipsUI()));
        options.add(new MenuItem("Pairs of Ships ", new PairsOfShipsUI()));
        options.add(new MenuItem("List all Ships ", new MovementsUI()));
        options.add(new MenuItem("Import Ports ", new ImportPortsUI()));
        options.add(new MenuItem("Find the closest port of a ship", new ClosestPortUI()));
        options.add(new MenuItem("Ships Available on the next Monday", new ShipsOnMondayUI()));
        options.add(new MenuItem("Closeness Places", new ClosestPlacesUI()));
        options.add(new MenuItem("Build Freight Network", new FreightNetworkUI()));
        options.add(new MenuItem("Color the map", new ColorGraphUI()));
        options.add(new MenuItem("Add new Cargo Manifest", new AddCargoManifestUI()));
        options.add(new MenuItem("Shortest Path", new ClosestPlacesInputUI()));
        options.add(new MenuItem("Ports Centrality", new PortsCentralityUI()));
        options.add(new MenuItem("Most efficient circuit", new MostEfficienteCircuitUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nTraffic Manager Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}