package generatorRaderketen.simulation.incident;

import generatorRaderketen.messageService.MessageSender;

import java.io.IOException;

/**
 * This class implements Runnable. It is used for the parallel processing of the messages. Each IncidentMessage will have each own loop.
 */
public class IncidentMessageLoop implements Runnable {


    private String shipid, xml;
    private int delay;

    public IncidentMessageLoop(int delay, String shipId) {
        this.delay = delay;
        this.shipid = shipId;
    }

    @Override
    public void run() {
        stopped = false;
        while (!stopped) {
            try {
                Thread.sleep(delay);
                xml = IncidentReporter.getIncident(shipid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendXml();
            stop();
        }
    }


    private boolean stopped;

    private void sendXml() {

        try {

            MessageSender.sendMessage(xml.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stopped = true;
    }


    public boolean isStopped() {
        return stopped;
    }
}
