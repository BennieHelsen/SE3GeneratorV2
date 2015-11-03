package generatorRaderketen;

import generatorRaderketen.messageService.MessageSender;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Thomas on 2/11/2015.
 */
public class PositionMessageLoop implements Runnable {

    private PositionMessage positionMessage;
    private boolean stopped;

    public PositionMessageLoop(PositionMessage positionMessage) {
        this.positionMessage = positionMessage;
    }

    @Override
    public void run() {
        stopped = false;
        while (!stopped) {
            try {
                Thread.sleep(Long.parseLong(positionMessage.getDelay()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendXml(positionMessage);
            stop();
        }
    }



    private void sendXml(PositionMessage positionMessage) {

        try {


            //JAXBContext jaxbContext = JAXBContext.newInstance(PositionMessage.class);
            //Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Writer writer = new StringWriter();


            //jaxbMarshaller.marshal(positionMessage, writer);
            Marshaller.marshal(positionMessage, writer);
            MessageSender.sendMessage(writer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stopped = true;
    }

}
