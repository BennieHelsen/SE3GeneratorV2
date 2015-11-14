package generatorRaderketen.simulation.route;

import generatorRaderketen.simulation.positionMessage.PositionMessageSimulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Thomas on 14/11/2015.
 */
public class RouteSimulator {
    private final String routesDirectory = "./routes";
    private static HashMap allRoutes;
    private RoutesReader routesReader;

    public RouteSimulator() {
        routesReader = new RoutesReader();
    }


    public ArrayList<String> getShipIds() {
        Map mp = allRoutes;
        ArrayList<String> shipIds = new ArrayList<>();
        assert mp != null;
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();


            //add shipID to a list of all shipIDs
            shipIds.add((String) pair.getKey());

            it.remove(); // avoids a ConcurrentModificationException
        }

        return shipIds;
    }


    public void init(PositionMessageSimulator positionMessageSimulator) throws IOException {
        allRoutes = routesReader.loadCSVs(routesDirectory);
        positionMessageSimulator.setAllroutes(new HashMap(allRoutes));

           }


}
