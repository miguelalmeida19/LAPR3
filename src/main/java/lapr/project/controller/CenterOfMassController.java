package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;

public class CenterOfMassController {
    public static final double CONTAINER_WEIGHT = 0.5;
    public static final double CONTAINER_SIZE_X = 6;
    public static final double CONTAINER_SIZE_Y = 2;
    public static final double CONTAINER_SIZE_Z = 2;
    public static final List<double[]> POSITIONS = new ArrayList<>();
    public static double towerCmPosX;
    public static double towerCmPosY;
    public static double towerCmPosZ;

    /**
     * This method calculates the center of mass of vessel
     * @param shipSizeX
     * @param shipSizeY
     * @param shipSizeZ
     * @param shipWeight
     * @param towerSizeX
     * @param towerSizeY
     * @param towerSizeZ
     * @param towerWeight
     * @param position
     * @return
     */
    public static double[] centerOfMassOfVessel(double shipSizeX, double shipSizeY, double shipSizeZ, double shipWeight,
                                                double towerSizeX, double towerSizeY, double towerSizeZ, double towerWeight,
                                                String position)
    {

        towerCmPosZ=towerSizeZ/2 + shipSizeZ;

        if (position.equals("stern")){
            towerCmPosX=towerSizeX/2;
            towerCmPosY=towerSizeY/2;
        }
        else if (position.equals("bow")){
            towerCmPosX=shipSizeX-(towerSizeX/2);
            towerCmPosY=shipSizeY-(towerSizeY/2);
        }
        else {
            towerCmPosX=shipSizeX/2;
            towerCmPosY=shipSizeY/2;
        }

        double shipCmPosX = shipSizeX/2;
        double shipCmPosY = shipSizeY/2;
        double shipCmPosZ = shipSizeZ/2;

        double totalCmX = ((shipWeight*shipCmPosX) + (towerWeight*towerCmPosX))/(shipWeight + towerWeight);
        double totalCmY = ((shipWeight*shipCmPosY) + (towerWeight*towerCmPosY))/(shipWeight + towerWeight);
        double totalCmZ = ((shipWeight*shipCmPosZ) + (towerWeight*towerCmPosZ))/(shipWeight + towerWeight);

        return new double[]{totalCmX, totalCmY, totalCmZ};
    }

    /**
     * This method calculates de center of mass for a vessel with containers
     * @param shipSizeX
     * @param shipSizeY
     * @param shipSizeZ
     * @param shipWeight
     * @param towerSizeX
     * @param towerSizeY
     * @param towerSizeZ
     * @param towerWeight
     * @param position
     * @param numOfContainers
     * @return
     */
    public static double[] centerOfMassOfVesselWithContainers(double shipSizeX, double shipSizeY, double shipSizeZ, double shipWeight,
                                                              double towerSizeX, double towerSizeY, double towerSizeZ, double towerWeight,
                                                              String position, int numOfContainers)
    {
        double towerCmPosX;
        double towerCmPosY;
        double towerCmPosZ=towerSizeZ/2 + shipSizeZ;

        if (position.equals("stern")){
            towerCmPosX=towerSizeX/2;
            towerCmPosY=towerSizeY/2;
        }
        else if (position.equals("bow")){
            towerCmPosX=shipSizeX-(towerSizeX/2);
            towerCmPosY=shipSizeY-(towerSizeY/2);
        }
        else {
            towerCmPosX=shipSizeX/2;
            towerCmPosY=shipSizeY/2;
        }

        double shipCmPosX = shipSizeX/2;
        double shipCmPosY = shipSizeY/2;
        double shipCmPosZ = shipSizeZ/2;

        double[] centerOfMassWithoutContainer = centerOfMassOfVessel(shipSizeX,shipSizeY,shipSizeZ,shipWeight,towerSizeX,towerSizeY,towerSizeZ,towerWeight, position);
        //primeiro sacamos as posições
        double start;
        double start1;
        double end1;
        double end;
        if (position.equals("middle")){
            start = centerOfMassWithoutContainer[0] - towerSizeX/2 - CONTAINER_SIZE_X /2;
            start1 = centerOfMassWithoutContainer[0] + towerSizeX/2 + CONTAINER_SIZE_X /2;
            end = 0;
            end1 = shipSizeX;
        }
        else if (position.equals("stern")){
            start = centerOfMassWithoutContainer[0] - CONTAINER_SIZE_X /2;
            start1 = centerOfMassWithoutContainer[0] + CONTAINER_SIZE_X /2;
            end = towerSizeX;
            end1 = 2 * centerOfMassWithoutContainer[0] - towerSizeX;
        }
        else {
            start = centerOfMassWithoutContainer[0] - CONTAINER_SIZE_X /2;
            start1 = centerOfMassWithoutContainer[0] + CONTAINER_SIZE_X /2;
            end = shipSizeX - towerSizeX;
            end1 = 2*centerOfMassWithoutContainer[0] - end;
        }

        double actualX = start;
        double actual1X = start1;
        double actualY = towerSizeY- CONTAINER_SIZE_Y /2;
        double actual1Y = CONTAINER_SIZE_Y /2;
        double actualZ = CONTAINER_SIZE_Z /2;
        double actual1Z = CONTAINER_SIZE_Z /2;

        double totalMassContainers = CONTAINER_WEIGHT *numOfContainers;

        double cmContainerX = 0;
        double cmContainerY = 0;
        double cmContainerZ = 0;

        for (int i=0; i<numOfContainers; i++){

            if (i%2==0){
                POSITIONS.add(new double[]{actualX, actualY, actualZ});

                cmContainerX+= actualX;
                cmContainerY+= actualY;
                cmContainerZ+= actualZ;

                if (actualX>=end && actualY>=0){
                    actualX= actualX - CONTAINER_SIZE_X;
                }
                else if (actualX<=end && actualY>=0){
                    actualX = start;
                    actualY = actualY - CONTAINER_SIZE_Y;
                }
                else if  (actualY<=0){
                    actualX = start;
                    actualY = towerSizeY - CONTAINER_SIZE_Y /2;
                    actualZ = actualZ + CONTAINER_SIZE_Z;
                }
            }
            else {
                POSITIONS.add(new double[]{actual1X, actual1Y, actual1Z});

                cmContainerX+= actual1X;
                cmContainerY+= actual1Y;
                cmContainerZ+= actual1Z;

                if (actual1X<=end1 && actual1Y<=towerSizeY){
                    actual1X = actual1X + CONTAINER_SIZE_X;
                }
                else if (actual1X>=end1 && actual1Y<=towerSizeY){
                    actual1X = start1;
                    actual1Y = actual1Y + CONTAINER_SIZE_Y;
                }
                else {
                    actual1X = start1;
                    actual1Y = CONTAINER_SIZE_Y /2;
                    actual1Z = actual1Z + CONTAINER_SIZE_Z;
                }
            }
        }

        double totalCmX = ((CONTAINER_WEIGHT *cmContainerX) + ((shipWeight*shipCmPosX) + (towerWeight*towerCmPosX)))/(totalMassContainers + shipWeight + towerWeight) ;
        double totalCmY = ((CONTAINER_WEIGHT *cmContainerY) + ((shipWeight*shipCmPosY) + (towerWeight*towerCmPosY)))/(totalMassContainers + shipWeight + towerWeight) ;
        double totalCmZ = ((CONTAINER_WEIGHT *cmContainerZ) + ((shipWeight*shipCmPosZ) + (towerWeight*towerCmPosZ)))/(totalMassContainers + shipWeight + towerWeight) ;

        return new double[]{totalCmX, totalCmY, totalCmZ};
    }
}