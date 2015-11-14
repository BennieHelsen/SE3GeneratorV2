package generatorRaderketen.simulation.positionMessage;

import generatorRaderketen.simulation.Simulation;
import generatorRaderketen.simulation.route.Route;
import generatorRaderketen.simulation.route.RouteSimulator;
import generatorRaderketen.simulation.route.RoutesReader;

import java.io.IOException;
import java.util.*;

/**
 * Created by Thomas on 14/11/2015.
 */
public class PositionMessageSimulator {


    private final Collection<PositionMessageLoop> loops = new ArrayList<>();
    private long waitTime;
    private boolean halting = false, zoneCheck = false;
    private String safetyZone = "";
    private HashMap allroutes;


    public void start(RouteSimulator simulator) {



        processRouteFiles(allroutes);

    }

    public void processRouteFiles(HashMap allRoutes) {


        Iterator it = allRoutes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            //Send the messages from one .csv file
            processRouteFile((String) pair.getKey(), (ArrayList) pair.getValue());


            it.remove(); // avoids a ConcurrentModificationException
        }


    }


    private void processRouteFile(String shipId, ArrayList<Route> routes) {

        for (Route route : routes) {
            PositionMessageLoop loop = new PositionMessageLoop(new PositionMessage(route.getDelay(), route.getDistanceToLoadingDock(), shipId, route.getCentralId()));
            loops.add(loop);

            if (zoneCheck) {
                if (safetyZone.equals(loop.getSafetyZone())) {
                    halt(loop);
                }
            }

            if (halting) {
                halt(loop);
            }


            Thread thread = new Thread(loop);
            thread.start();


        }


    }

    public void haltProcessing(int delayFrequentie) {
        halting = true;
        waitTime = delayFrequentie;
    }

    public void resumeProcessing() {
        halting = false;
        zoneCheck = false;
    }

    public void setSafetyZone(String zone) {
        zoneCheck = true;
        safetyZone = zone;
    }

    private void halt(PositionMessageLoop loop) {
        while (halting) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(loop).start();
        }
    }

    public HashMap getAllroutes() {
        return allroutes;
    }

    public void setAllroutes(HashMap allroutes) {
        this.allroutes = allroutes;
    }
}
