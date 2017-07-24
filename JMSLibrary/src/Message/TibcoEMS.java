package Message;

/**
 * Created by HUANGJA2 on 7/7/2017.
 */
import com.tibco.tibjms.TibjmsConnection;
import com.tibco.tibjms.TibjmsConnectionFactory;
import com.tibco.tibjms.TibjmsMessageProducer;
import com.tibco.tibjms.TibjmsSession;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
public class TibcoEMS {
    private TibjmsConnection connection;
    private TibjmsSession session;
    private TibjmsMessageProducer msgProducer;
    private Destination destination;
    private TibjmsConnectionFactory factory;
    private TextMessage message;
    /**
     * Connect tibco EMS server.
     *
     * Examples:
     * | Connect Server | EMSServerURL | UserName | Password |
     */
    public void connectServer(String serverURL,String userName, String password) throws JMSException{
        factory = new com.tibco.tibjms.TibjmsConnectionFactory(serverURL);
        connection =(TibjmsConnection) factory.createConnection(userName,password);
        session = (TibjmsSession) connection.createSession(false, javax.jms.Session.CLIENT_ACKNOWLEDGE);
        message = session.createTextMessage();
        msgProducer = (TibjmsMessageProducer) session.createProducer(null);
        System.out.println("Connect to server successfully: "+serverURL);
    }
    /**
     * Set properties of message.
     *
     * Examples:
     * | Set Property | OLP_SENDER_ID | CargoSmart |
     * | Set Property | OLP_RECIPIENT_ID | POCS |
     */
    public void setProperty(String key,String value) throws JMSException {
        message.setStringProperty(key, value);
    }
    /**
     * Send message to topic or queue.
     *
     * Examples:
     * | ${Message}= | This is a message |
     * | Send Message | ${true} | topicName  | ${Message} |
     * | Send Message | ${false} | queueName  | ${Message} |
     */
    public void sendMessage(boolean useTopic, String destinationName, String msg) throws Exception{
        if(useTopic)
            destination = session.createTopic(destinationName);
        else
            destination = session.createQueue(destinationName);
        message.setText(msg);
        msgProducer.send(destination,message);
        System.out.println("Send message to server successfully");
    }
    /**
     * Disconnect from EMS server
     */
    public void closeConnection()throws JMSException{
        connection.close();
        System.out.println("Disconnect from server successfully");
    }

    public static void main(String []args){
        System.out.print("s");
    }
}
