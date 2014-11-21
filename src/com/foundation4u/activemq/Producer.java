package com.foundation4u.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private ConnectionFactory connectionFactory = null;
	private Connection connection = null;
	private Session session = null;
	
	private Destination destination = null;
	private MessageProducer messageProducer = null;
	
	public void produceMessage(){
		try {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myqueue");
			
			messageProducer = session.createProducer(destination);
			TextMessage textMessage = session.createTextMessage();
			
			textMessage.setText("this is the first message--");
			
			messageProducer.send(textMessage);
			System.out.println("message has been sent: "+textMessage.getText());
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			if(connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
	}
	
	public static void main(String[] args) {
		Producer producer  = new Producer();
		producer.produceMessage();
	}
}
