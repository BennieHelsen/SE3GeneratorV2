package generatorRaderketen.simulation.incident;

import generatorRaderketen.simulation.incident.actionreport.IncidentActionReportHandler;
import generatorRaderketen.simulation.positionMessage.PositionMessageSimulator;
import generatorRaderketen.utilities.DelayListFiller;

import java.util.*;

/**
 * Created by Thomas on 14/11/2015.
 */
public class IncidentSimulator {

    private HashMap<String, Integer> delayList;

    private final Collection<IncidentMessageLoop> loops = new ArrayList<>();
    private IncidentActionReportHandler incidentActionReportHandler;

    public IncidentSimulator(PositionMessageSimulator positionMessageSimulator) {
        incidentActionReportHandler = new IncidentActionReportHandler(positionMessageSimulator);
    }

    public void init(ArrayList<String> shipIds) {

        this.delayList = DelayListFiller.getDelayList(shipIds);

    }

    public void start() {

        handleIncidents();

    }


    public void handleIncidents() {


        Iterator it = delayList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            IncidentMessageLoop loop = new IncidentMessageLoop((int) pair.getValue(), (String) pair.getKey());
            loops.add(loop);

            Thread thread = new Thread(loop);
            thread.start();

            it.remove(); // avoids a ConcurrentModificationException
        }


    }

}
