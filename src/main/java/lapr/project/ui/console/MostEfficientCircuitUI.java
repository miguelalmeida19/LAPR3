package lapr.project.ui.console;

import lapr.project.controller.FreightNetworkController;
import lapr.project.controller.MostEfficientCircuitController;
import lapr.project.store.FreightNetworkStore;
import lapr.project.structures.graph.matrix.MatrixGraph;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

class MostEfficienteCircuitUI implements Runnable {
    FreightNetworkController c = new FreightNetworkController();
    MostEfficientCircuitController cs = new MostEfficientCircuitController();


    public void run(){
        // MatrixGraph<Object ,Double> a = new MatrixGraph(false, c.getVertices(), c.getMatrix());
        MatrixGraph<Object, Double> a = FreightNetworkStore.getAb();
        if(a == null){
            print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" The graph was not yet created. Building...");
            c.build(1000);
            a = FreightNetworkStore.getAb();
        }
        print(TextUtils.ANSI_CYAN+"[+] "+TextUtils.ANSI_RESET+"Please choose a start point.");
        List<Object> vert = c.getVertices();
        for(int n = 0; n < vert.size(); n++){
            print("    "+TextUtils.ANSI_YELLOW+(n+1)+"-"+TextUtils.ANSI_RESET+vert.get(n));
        }
        int choice = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>] "+TextUtils.ANSI_RESET);
        if(choice > 0 & choice <vert.size()+2){
            try {
                System.out.println(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" Path: "+cs.calculatetCircuit(a.vertices().get(choice-1),a));

            }catch (Exception e){
                print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+"The vertex choosen has no adjacent vertex.");
            }
        }else{
            print(TextUtils.ANSI_RED+"[!]"+TextUtils.ANSI_RESET+" Invalid option.");
        }
    }
}