package generatorRaderketen.messageService;

import com.rabbitmq.client.*;
import generatorRaderketen.simulation.incident.actionreport.IncidentActionReport;
import generatorRaderketen.simulation.incident.actionreport.IncidentActionReportDTO;
import generatorRaderketen.simulation.incident.actionreport.IncidentActionReportHandler;
import generatorRaderketen.utilities.DTOMapper;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 * Created by Thomas on 14/11/2015.
 */
public class IncidentActionReportReceiver implements Runnable{

    private static final String EXCHANGE_NAME = "actionreports";
    private IncidentActionReportHandler incidentActionReportHandler;

    public IncidentActionReportReceiver(IncidentActionReportHandler incidentActionReportHandler) {
        this.incidentActionReportHandler =  incidentActionReportHandler;
    }

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String xml = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + xml + "'");

                    Reader reader = new StringReader(xml);

                    IncidentActionReportDTO messageDTO = null;
                    try {
                        messageDTO = (IncidentActionReportDTO) Unmarshaller.unmarshal(IncidentActionReportDTO.class, reader);
                    } catch (MarshalException | ValidationException e) {
                        e.printStackTrace();
                    }

                    IncidentActionReport message = DTOMapper.mapIncidentActionReport(messageDTO);
                    try {
                        incidentActionReportHandler.handle(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };





            channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
