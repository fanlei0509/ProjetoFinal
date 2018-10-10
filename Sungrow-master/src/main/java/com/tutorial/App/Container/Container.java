package com.tutorial.App.Container;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;


import com.tutorial.App.EstruturaDeDados.Fila;
import com.tutorial.App.EstruturaDeDados.TipoFila;
import com.tutorial.App.mqtt.CallbackMQTT;
import com.tutorial.App.mqtt.ConnectionToMQTT;

public class Container {
	
	private CallbackMQTT cmqtt;
	private MqttClient conn;
	private Double valorEnfileirado1;
	private Fila filaDeValoresDoCallBack1;
	private Double valorRecebidoDoCallback1;
	private Double valorEnfileirado2;
	private Fila filaDeValoresDoCallBack2;
	private Double valorRecebidoDoCallback2;
	private String subscribeValue;
	
	public String getSubscribeValue() {
		return subscribeValue;
	}
	public void setSubscribeValue(String subscribeValue) {
		this.subscribeValue = subscribeValue;
	}
	public CallbackMQTT getCmqtt() {
		return cmqtt;
	}
	public void setCmqtt(CallbackMQTT cmqtt) {
		this.cmqtt = cmqtt;
	}
	public MqttClient getConn() {
		return conn;
	}
	public void setConn(MqttClient conn) {
		this.conn = conn;
	}
	
	public Double getValorEnfileirado1() {
		return valorEnfileirado1;
	}
	public void setValorEnfileirado1(Double valorEnfileirado1) {
		this.valorEnfileirado1 = valorEnfileirado1;
	}
	public Fila getFilaDeValoresDoCallBack1() {
		return filaDeValoresDoCallBack1;
	}
	public void setFilaDeValoresDoCallBack1(Fila filaDeValoresDoCallBack1) {
		this.filaDeValoresDoCallBack1 = filaDeValoresDoCallBack1;
	}
	public Double getValorRecebidoDoCallback1() {
		return valorRecebidoDoCallback1;
	}
	public void setValorRecebidoDoCallback1(Double valorRecebidoDoCallback1) {
		this.valorRecebidoDoCallback1 = valorRecebidoDoCallback1;
	}
	public Double getValorEnfileirado2() {
		return valorEnfileirado2;
	}
	public void setValorEnfileirado2(Double valorEnfileirado2) {
		this.valorEnfileirado2 = valorEnfileirado2;
	}
	public Fila getFilaDeValoresDoCallBack2() {
		return filaDeValoresDoCallBack2;
	}
	public void setFilaDeValoresDoCallBack2(Fila filaDeValoresDoCallBack2) {
		this.filaDeValoresDoCallBack2 = filaDeValoresDoCallBack2;
	}
	public Double getValorRecebidoDoCallback2() {
		return valorRecebidoDoCallback2;
	}
	public void setValorRecebidoDoCallback2(Double valorRecebidoDoCallback2) {
		this.valorRecebidoDoCallback2 = valorRecebidoDoCallback2;
	}
	public void conexao(String subscribeValue) {
		this.subscribeValue = subscribeValue;
		MqttClient conn = ConnectionToMQTT.doConnection();
		this.conn = conn;
		//CallbackMQTT cmqtt = new CallbackMQTT();
		//this.cmqtt = cmqtt;
		//conn.setCallback(cmqtt);
		
		try {
			conn.connect();
			
			Callback();
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (conn.isConnected()) {
			System.out.println("Conexao Funcionando");
		}else {
			System.out.println("Conexao Sem Funcionamento");
		}

	}
	
	public void Callback () {
		
				
		this.conn.setCallback(new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				String s = "pi/sng/tempAg";
				if (topic.equals(s)) {
					enfileirarChamadas1(new String (message.getPayload()));
					System.out.println("Estou enfileirando Ambiente " + new String (message.getPayload()));
				}else {
					enfileirarChamadas2(new String (message.getPayload()));
					System.out.println("Estou enfileirando Solucao " + new String (message.getPayload()));
					
				}
			
				
				
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				// TODO Auto-generated method stub
				
			}
		});
		
		try {
			
			conn.subscribe("pi/sng/tempAm", 0);
			conn.subscribe("pi/sng/tempAg", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Fila enfileirarChamadas1(String string) {
		
		this.valorRecebidoDoCallback1 = Double.valueOf(string).doubleValue();
				
		if (this.filaDeValoresDoCallBack1 != null) {
			
			this.valorEnfileirado1 = this.valorRecebidoDoCallback1;
			TipoFila tf1 = new TipoFila(null);
			tf1.setValor(this.valorEnfileirado1);
			this.filaDeValoresDoCallBack1.enfileirar(tf1);
			/*System.out.println("Enfileirou - O tamanho da Fila eh " + filaDeValoresDoCallBack.mostrarTamanhoDaFila());
			//System.out.println("O Ultimo da Fila " + filaDeValoresDoCallBack.mostrarUltimoDaLista());*/
			System.out.println("Fila1 " + filaDeValoresDoCallBack1.mostrarFila());

		}else {
			
			criarFila1();
			this.valorEnfileirado1 = this.valorRecebidoDoCallback1;
			TipoFila tf1 = new TipoFila(null);
			tf1.setValor(this.valorEnfileirado1);
			this.filaDeValoresDoCallBack1.enfileirar(tf1);
			/*System.out.println("Enfileirou C - O tamanho da Fila eh " + filaDeValoresDoCallBack.mostrarTamanhoDaFila());
			//System.out.println("O Ultimo da Fila " + filaDeValoresDoCallBack.mostrarUltimoDaLista());*/
			System.out.println("Fila1 " + filaDeValoresDoCallBack1.mostrarFila());
		}
		
		
		return filaDeValoresDoCallBack1;
		
	}
	
	public Fila criarFila1() {
		Fila f1 = new Fila();
		this.filaDeValoresDoCallBack1 =f1;
		return f1;
	}
	
public Fila enfileirarChamadas2(String string) {
		
		this.valorRecebidoDoCallback2 = Double.valueOf(string).doubleValue();
				
		if (this.filaDeValoresDoCallBack2 != null) {
			
			this.valorEnfileirado2 = this.valorRecebidoDoCallback2;
			TipoFila tf2 = new TipoFila(null);
			tf2.setValor(this.valorEnfileirado2);
			this.filaDeValoresDoCallBack2.enfileirar(tf2);
			/*System.out.println("Enfileirou - O tamanho da Fila eh " + filaDeValoresDoCallBack.mostrarTamanhoDaFila());
			//System.out.println("O Ultimo da Fila " + filaDeValoresDoCallBack.mostrarUltimoDaLista());*/
			System.out.println("fila2 " + filaDeValoresDoCallBack2.mostrarFila());

		}else {
			
			criarFila2();
			this.valorEnfileirado2 = this.valorRecebidoDoCallback2;
			TipoFila tf2 = new TipoFila(null);
			tf2.setValor(this.valorEnfileirado2);
			this.filaDeValoresDoCallBack2.enfileirar(tf2);
			/*System.out.println("Enfileirou C - O tamanho da Fila eh " + filaDeValoresDoCallBack.mostrarTamanhoDaFila());
			//System.out.println("O Ultimo da Fila " + filaDeValoresDoCallBack.mostrarUltimoDaLista());*/
			System.out.println("Fila 2 " + filaDeValoresDoCallBack2.mostrarFila());
		}
		
		
		return filaDeValoresDoCallBack2;
		
	}
	
	public Fila criarFila2() {
		Fila f2 = new Fila();
		this.filaDeValoresDoCallBack2 =f2;
		return f2;
	}
	
	
	
	
	

}
