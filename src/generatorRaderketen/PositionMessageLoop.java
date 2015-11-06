package generatorRaderketen;

import generatorRaderketen.messageService.MessageSender;
import org.exolab.castor.xml.Marshaller;


import javax.xml.bind.ValidationException;
import java.io.*;
import java.rmi.MarshalException;

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

         int delay = Integer.parseInt(positionMessage.getDelay());


                //Thread.sleep(Long.parseLong(positionMessage.getDelay()));
            Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NumberFormatException e){
                System.out.println(positionMessage.getDelay());
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

            Marshaller.marshal(positionMessage, writer);
            MessageSender.sendMessage(writer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.exolab.castor.xml.MarshalException e) {
            e.printStackTrace();
        } catch (org.exolab.castor.xml.ValidationException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stopped = true;
    }

}
