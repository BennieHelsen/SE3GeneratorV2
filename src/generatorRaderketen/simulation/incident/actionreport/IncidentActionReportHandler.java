package generatorRaderketen.simulation.incident.actionreport;

import generatorRaderketen.messageService.IncidentActionReportReceiver;
import generatorRaderketen.randomLoad.RandomLoad;
import generatorRaderketen.simulation.incident.IncidentReporter;
import generatorRaderketen.simulation.incident.IncidentSimulator;
import generatorRaderketen.simulation.incident.actionreport.IncidentActionReport;
import generatorRaderketen.simulation.positionMessage.PositionMessageSimulator;

/**
 * Created by Thomas on 14/11/2015.
 */
public class IncidentActionReportHandler {


    private final long delayFrequentie = 10000;

    private IncidentActionReportReceiver reportReceiver;
    private PositionMessageSimulator messageSimulator;
    boolean incidentHandlingInProgress;


    public IncidentActionReportHandler(PositionMessageSimulator positionMessageSimulator) {
        reportReceiver = new IncidentActionReportReceiver(this);
        this.messageSimulator = positionMessageSimulator;
        new Thread(reportReceiver).start();
    }

    public void handle(IncidentActionReport message) throws InterruptedException {

        //aanpassen: message controleren op type
        if (true) {
            incidentHandlingInProgress = true;
        }
        if (message.getIncidentType().equalsIgnoreCase("Schade")){
            messageSimulator.setSafetyZone(message.getCentraleId());
        }

        boolean firstRun = true;
        while (incidentHandlingInProgress) {

            if (firstRun) {
                stopRouteProcessing();
                firstRun = false;
            }


            simulateDeviatingShip();
            handleShipStatus(message.getShipId());
            Thread.sleep(delayFrequentie);
        }

        resumeRouteProcessing();


    }

    private void resumeRouteProcessing() {

        messageSimulator.resumeProcessing();

    }

    private void simulateDeviatingShip() {
        RandomLoad.sendRandomPositionMessage();
    }

    private void stopRouteProcessing() {

        messageSimulator.haltProcessing((int) delayFrequentie);

    }

    private void handleShipStatus(String shipId) {
        boolean status = getShipStatus(shipId);

        if (!status) {
            incidentHandlingInProgress = false;
        }
    }

    private boolean getShipStatus(String shipId) {
        return IncidentReporter.getStatus(shipId);
    }


}
