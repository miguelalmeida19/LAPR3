package lapr.project.controller;

import lapr.project.model.Message;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.structures.KDNode;

import java.time.LocalDateTime;

public class ClosestPortController {


    /**
     * Gets the closts port from a ship position
     * @param callSign callsign of a ship
     * @param time datetime
     * @return the closest port of a ship
     */
    public Port closestPort(String callSign, LocalDateTime time) {
        Ship ship = App.getInstance().getCompany().getShipStore().getShip(callSign);
        Message lastMessage;
        //this try catch is used for when ship is null.
        try{
            lastMessage = ship.findMessageByDateTime(time);

        }catch (Exception e){
            return null;
        }
        if(lastMessage == null) throw new IllegalArgumentException("There are no messages on the DateTime provided. Please check again the values and try again.");
        double latitude = lastMessage.getLatitude();
        double longitude = lastMessage.getLongitude();
        KDNode p = App.getInstance().getCompany().getPortStore().getKdt().closestPort(App.getInstance().getCompany().getPortStore().getRootNode(),latitude,longitude,App.getInstance().getCompany().getPortStore().getRootNode(),true);
        double[] cords = new double[2];
        cords[0] = p.getLat();
        cords[1] = p.getLong();
        return (Port)p.getObject();
    }

}