package generatorRaderketen.simulation;

import generatorRaderketen.*;
import generatorRaderketen.messageService.MessageSender;


import java.io.IOException;

import java.util.*;

/**
 * Created by Thomas on 1/11/2015.
 */
public class Simulation implements Thread.UncaughtExceptionHandler {
    private final String routesDirectory = "./routes";
    private ArrayList<String> shipIds = new ArrayList<>();
    private final Collection<PositionMessageLoop> loops = new ArrayList<>();
    private Random random = new Random();

    private final int INCIDENT_FREQ = 10000;
    private boolean running_incident = true;


    public Simulation() {
    }


    public void start() {
        processAllRouteFiles();

        while (running_incident) {
            handleIncident();
        }

    }

    private void handleIncident() {


        String incidentReport = getIncident(getRandomShipId());

        try {
            MessageSender.sendMessage(incidentReport.getBytes());
            Thread.sleep(getWaitTime());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getWaitTime() {
        //return INCIDENT_FREQ;
        return random.nextInt(INCIDENT_FREQ);
    }

    private HashMap getAllRoutes() {

        try {
            return RoutesReader.loadCSVs(routesDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    private void processAllRouteFiles() {

        Map mp = getAllRoutes();
        assert mp != null;
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            //Send the messages from 1 .csv file
            processRouteFile((String) pair.getKey(), (ArrayList) pair.getValue());

            //add shipID to a list of all shipIDs
            shipIds.add((String) pair.getKey());

            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private String getIncident(String shipId) {

        return IncidentReporter.getIncident(shipId);

    }

    private String getRandomShipId() {
        int size = shipIds.size();

        return shipIds.get(random.nextInt(size));


    }

    private void processRouteFile(String shipId, ArrayList<Route> routes) {
        for (Route route : routes) {
            PositionMessageLoop loop = new PositionMessageLoop(new PositionMessage(route.getDelay(), route.getDistanceToLoadingDock(), shipId, route.getCentralId()));
            loops.add(loop);
            Thread thread = new Thread(loop);
            // register a handler that is used to transfer unexpected errors in  positionmessage threads
            // to the main thread so they can be reported to the user of the simulation
            thread.setUncaughtExceptionHandler(this);
            thread.start();
        }
    }

    public void stop() {
        loops.forEach(PositionMessageLoop::stop);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        throw new SimulationException("Unexpected error (potential BUG) in message processing loop", e);
    }
}