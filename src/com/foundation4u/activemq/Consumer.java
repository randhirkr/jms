package com.foundation4u.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.sound.midi.Receiver;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private ConnectionFactory connectionFactory = null;
	private Connection connection = null;
	private Session session = null;
	
	private Destination destination = null;
	private MessageConsumer messageConsumer = null;
	
	public void receiveMessage(){
		try{
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myqueue");
			
			messageConsumer = session.createConsumer(destination);
			Message	 message = messageConsumer.receive();
			
			if(message instanceof TextMessage){
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Textmessage: "+textMessage);
				System.out.println("message has been received: "+textMessage.getText());
			}
			
		}catch(JMSException je){
			System.out.println("error occurred: "+je);
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
		Consumer consumer = new Consumer();
		consumer.receiveMessage();
	}
}
