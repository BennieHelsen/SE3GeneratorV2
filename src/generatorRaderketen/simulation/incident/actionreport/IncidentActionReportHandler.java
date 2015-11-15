package generatorRaderketen.simulation.incident.actionreport;

import generatorRaderketen.messageService.IncidentActionReportReceiver;
import generatorRaderketen.messageService.MessageSender;
import generatorRaderketen.randomLoad.RandomLoadSimulator;
import generatorRaderketen.simulation.incident.IncidentReporter;
import generatorRaderketen.simulation.positionMessage.PositionMessageSimulator;

import java.io.IOException;

/**
 * This class handles all incoming action reports and takes needed measures.
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



            incidentHandlingInProgress = true;

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

        sendAllesOK();
        resumeRouteProcessing();



    }

    private void resumeRouteProcessing() {

        messageSimulator.resumeProcessing();

    }

    private void simulateDeviatingShip() {
        new RandomLoadSimulator().sendRandomPositionMessage();

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

    private void sendAllesOK(){

        String message = "<incident-status>AllesNormaal</incident-status>";

        try {
            MessageSender.sendMessage(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
