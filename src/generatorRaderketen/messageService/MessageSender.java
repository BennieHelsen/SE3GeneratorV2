package generatorRaderketen.messageService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Thomas on 2/11/2015.
 */
public class MessageSender {
    private final static String QUEUE_NAME = "messagebroker";



    public static void sendMessage(byte[] xml) throws IOException {
         ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicPublish("", QUEUE_NAME, null, xml);

        System.out.println(Arrays.toString(xml));
        channel.close();
        connection.close();

    }

}
