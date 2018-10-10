package com.tutorial.App.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.App.Container.Container;
import com.tutorial.App.EstruturaDeDados.Fila;

@RestController
@RequestMapping ("json1")
public class JsonTemperaturaAmbienteController {
	
Container c2;
	
	//@Autowired
	Double tamanhoDaFila2;
	
	
	Fila filaVindoDoMqttDaConexao2;
	

	public Fila getFilaVindoDoMqttDaConexao2() {
		return filaVindoDoMqttDaConexao2;
	}

	public void setFilaVindoDoMqttDaConexao2(Fila filaVindoDoMqttDaConexao2) {
		this.filaVindoDoMqttDaConexao2 = filaVindoDoMqttDaConexao2;
	}
	
		
	public Double getTamanhoDaFila2() {
		return tamanhoDaFila2;
	}

	public void setTamanhoDaFila2(Double tamanhoDaFila2) {
		this.tamanhoDaFila2 = tamanhoDaFila2;
	}

	

	public Container getC2() {
		return c2;
	}

	public void setC2(Container c2) {
		this.c2 = c2;
	}

	

	public void conectartda(String subscribeDaConexao) {
		Container c2 = new Container();
		this.c2 = c2;
		c2.conexao(subscribeDaConexao);
		if (c2.getConn().isConnected()) {
			System.out.println("Estamos conectados temperaturaAmbiente");
		}
		while (c2.getValorRecebidoDoCallback2() == null) {
			
		}
		System.out.println("Nao sou mais null");
		setTamanhoDaFila2(c2.getValorRecebidoDoCallback2()); 
		
	}	
	
	@RequestMapping(value = "/temperaturaambiente", method = RequestMethod.GET)
	public List<Double> graficoTemperaturadaagua() {
			
		if (!validarConexao()) {
			conectartda("pi/sng/tempAm");
		}
			
		//setTamanhoDaFila(c.getValorRecebidoDoCallback()); 
		System.out.println("Vamos ver se eh null " + getTamanhoDaFila2());		
		//model.addAttribute("message", getTamanhoDaFila());
		
		return c2.getFilaDeValoresDoCallBack2().mostrarFila();
		
	}
	
	@RequestMapping(value = "/temperaturaagua", method = RequestMethod.GET)
	public List<Double> graficoTemperaturambiente() {
			
		if (!validarConexao()) {
			conectartda("pi/sng/tempAm");
		}
			
		//setTamanhoDaFila(c.getValorRecebidoDoCallback()); 
		System.out.println("Vamos ver se eh null " + getTamanhoDaFila2());		
		//model.addAttribute("message", getTamanhoDaFila());
		
		return c2.getFilaDeValoresDoCallBack1().mostrarFila();
		
	}
	
	public boolean validarConexao() {
		if (this.c2 != null) {
			return true;
		}
		
		return false;
	}
	
	/*@RequestMapping ("/temperaturadaagua")
	public List<Integer> arquivoJsonTemperaturaDaAgua(){
		return asList(1);
	}*/
	

}
