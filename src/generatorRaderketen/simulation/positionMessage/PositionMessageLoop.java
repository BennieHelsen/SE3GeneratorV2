package generatorRaderketen.simulation.positionMessage;

import generatorRaderketen.messageService.MessageSender;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * This class implements Runnable. It is used for the parallel processing of the messages. Each PositionMessage will have each own loop.
 */
public class PositionMessageLoop implements Runnable {

    private PositionMessage positionMessage;


    public PositionMessageLoop(PositionMessage positionMessage) {
        this.positionMessage = positionMessage;
    }



    @Override
    public void run() {
                try {
            Thread.sleep(Long.parseLong(positionMessage.getDelay()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendXml(positionMessage);

    }


    private void sendXml(PositionMessage positionMessage) {

        try {

            Writer writer = new StringWriter();
            Marshaller marshaller = new Marshaller(writer);

           marshaller.marshal(positionMessage);

            MessageSender.sendMessage(writer.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

public String getSafetyZone(){
    return positionMessage.getCentralId();
}

}
