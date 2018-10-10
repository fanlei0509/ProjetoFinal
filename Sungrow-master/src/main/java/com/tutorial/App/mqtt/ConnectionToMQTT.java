package com.tutorial.App.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ConnectionToMQTT {
	
	private static String serverURI = "tcp://broker.mqtt-dashboard.com:1883";
	private static String clientId = "brunoMartinell";
	
			
	public static MqttClient doConnection() {
			
		try {
			
			 MqttClient connection = new MqttClient(serverURI, clientId, new MemoryPersistence());
			 if (connection.isConnected()) {
				 System.out.println("Conectado Ao Servidor Mqtt");
			 }
			 return connection;
			
		} catch (MqttException e) {
			
			e.printStackTrace();
			System.out.println("Nao foi possivel Conexao com o servidor");
		}
		
		return null;
		
	}
	
	
	


	
	
	
	

}
