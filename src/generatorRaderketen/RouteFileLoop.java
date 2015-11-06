package generatorRaderketen;

import java.util.ArrayList;

/**
 * Created by Admin on 6-11-2015.
 */
public class RouteFileLoop implements Runnable {


    String shipId;
    ArrayList<Route> routes;

    public RouteFileLoop(String shipId, ArrayList<Route> routes) {
        this.shipId = shipId;
        this.routes = routes;
    }

    @Override
    public void run() {
        for (Route route : routes) {
            PositionMessageLoop loop = new PositionMessageLoop(new PositionMessage(route.getDelay(), route.getDistanceToLoadingDock(), shipId, route.getCentralId()));
            //loops.add(loop);
            Thread thread = new Thread(loop);
            // register a handler that is used to transfer unexpected errors in  positionmessage threads
            // to the main thread so they can be reported to the user of the simulation
           // thread.setUncaughtExceptionHandler(this);
            thread.start();
        }
    }
}
