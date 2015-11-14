package generatorRaderketen.simulation;

import generatorRaderketen.simulation.incident.IncidentSimulator;
import generatorRaderketen.simulation.positionMessage.PositionMessage;
import generatorRaderketen.simulation.positionMessage.PositionMessageLoop;
import generatorRaderketen.simulation.positionMessage.PositionMessageSimulator;
import generatorRaderketen.simulation.route.Route;
import generatorRaderketen.simulation.route.RouteSimulator;
import generatorRaderketen.simulation.route.RoutesReader;
import org.exolab.castor.types.DateTime;


import java.io.IOException;

import java.util.*;

/**
 * Created by Thomas on 1/11/2015.
 */
public class Simulation implements Thread.UncaughtExceptionHandler {

    private IncidentSimulator incidentSimulator;
    private RouteSimulator routeSimulator;
    private PositionMessageSimulator positionMessageSimulator;


    public Simulation() {

        routeSimulator = new RouteSimulator();
        positionMessageSimulator = new PositionMessageSimulator();
        incidentSimulator = new IncidentSimulator(positionMessageSimulator);

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void init() throws IOException {
        routeSimulator.init(positionMessageSimulator);


        incidentSimulator.init(routeSimulator.getShipIds());


    }

    public void start() {

        positionMessageSimulator.start(routeSimulator);

        incidentSimulator.start();


    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        throw new SimulationException("Unexpected error (potential BUG) in message processing loop", e);
    }


}
