package com.tutorial.App;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class TutorialApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TutorialApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
		
		//Container container = new Container();
		//container.conexao();
		//container.getFilaDeValoresDoCallBack();
		
		
		
		
		/*MqttClient conn = new ConnectionToMQTT().doConnection();
		CallbackMQTT c = new CallbackMQTT();
		conn.setCallback(c);
		
		try {
			conn.connect();
			conn.subscribe("teste/sungrow/#", 0);
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FilaInstanciada f = new FilaInstanciada();
		f.InstanciarFila();
		
	
		
		
		
		TipoFila tf= new TipoFila(null); 
		TipoFila tf1= new TipoFila(null);
		TipoFila tf2= new TipoFila(null);
		TipoFila tf3= new TipoFila(null);
		TipoFila tf4= new TipoFila(null);
		TipoFila tf5= new TipoFila(null);
		
		Fila f = new Fila();
		
		//tf.setValor("1");
		//f.enfileirar(tf);
		//System.out.println("Primeiro numero da Lista " + f.mostrarFila().getValor());
		tf.setValor("1");
		f.enfileirar(tf);
		//System.out.println("Primeiro numero da Lista " + f.mostrarUltimoDaLista().getValor());
		tf1.setValor("2");
		f.enfileirar(tf1);
		tf2.setValor("3");
		f.enfileirar(tf2);
		tf3.setValor("4");
		f.enfileirar(tf3);
		tf4.setValor("5");
		f.enfileirar(tf4);
		//f.desenfileirar();
		
		System.out.println("Primeiro numero da Lista " + f.mostrarFila().getValor());
		System.out.println("Ultimo numero da Lista " + f.mostrarUltimoDaLista().getValor());
		System.out.println("Tamanho da Fila " + f.mostrarTamanhoDaFila());
		f.desenfileirar();
		System.out.println("des Primeiro numero da Lista " + f.mostrarFila().getValor());
		System.out.println("des Ultimo numero da Lista " + f.mostrarUltimoDaLista().getValor());
		System.out.println("des Tamanho da Fila " + f.mostrarTamanhoDaFila());
		tf5.setValor("6");
		f.enfileirar(tf5);			
		System.out.println("enf 6 Primeiro numero da Lista " + f.mostrarFila().getValor());
		System.out.println("enf 6 Ultimo numero da Lista " + f.mostrarUltimoDaLista().getValor());
		System.out.println("enf 6 Tamanho da Fila " + f.mostrarTamanhoDaFila());
		FilaInstanciada f90 = new FilaInstanciada();
		
		
		
				
		
	
		
		if (conn.isConnected()) {
			System.out.println("Estamos Conectados");
		}
		*/
		
		
		
	}
}
