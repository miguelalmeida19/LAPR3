package lapr.project.controller;

public class ShipBuoyancyController {


    public ShipBuoyancyController() {
        //Constructor
    }

    static final double waterdensity = 0.997;
    static final double containerweight = 0.5;
    static final double gra = 9.80665;


    public static double [] calculate(double shipsizeX, double shipsizeY, double shipsizeZ, double shipweight, double towerweight, int numOfContainers)
    {

       double pressure= (shipweight + towerweight + (numOfContainers*containerweight))*gra;
        double pressureEmpty= (shipweight + towerweight)*gra;
        double volumeDisplacedFluidEmpty= pressureEmpty/(waterdensity*gra);
        double initialHeight= volumeDisplacedFluidEmpty/(shipsizeX*shipsizeY);
        double volumeDisplacedFluidFull= pressure/(waterdensity*gra);
        double finalHeight= volumeDisplacedFluidFull/(shipsizeX*shipsizeY);
        double heightDifference= (shipsizeZ-initialHeight)-(shipsizeZ- finalHeight);
        double[]info= new double[3];
        info[0]= pressure;
        info[1]= (shipweight+towerweight+(numOfContainers*containerweight));
        info[2]= heightDifference;

        return info;
    }
}
