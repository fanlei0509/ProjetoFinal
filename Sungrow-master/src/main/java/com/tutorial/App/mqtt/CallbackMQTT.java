package com.tutorial.App.mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

import org.eclipse.paho.client.mqttv3.MqttMessage;



public class CallbackMQTT implements MqttCallback {
	
	private String mensagemRecebida;
	private String topic;
	
	
	public void connectionLost(Throwable cause) {
		System.out.println("Conexao perdida");
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		this.mensagemRecebida = new String(message.getPayload());
		this.topic=topic;
		
		System.out.println("Messagem Recebida:\n" +this.mensagemRecebida);
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}
	

	public String getMensagemRecebida() {
		return mensagemRecebida;
	}

	public String getTopic() {
		return topic;
	}

	

	
	

	
	
	
	

	
	
	
	
	
	

}
