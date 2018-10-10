package com.tutorial.App.Controller;


import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.App.Container.Container;
import com.tutorial.App.EstruturaDeDados.Fila;

@RestController
@RequestMapping ("json")
public class JsonTemperaturaController {
	
Container c1;
	
	//@Autowired
	Double tamanhoDaFila1;
	
	Fila filaVindoDoMqttDaConexao1;
	
	public Fila getFilaVindoDoMqttDaConexao1() {
		return filaVindoDoMqttDaConexao1;
	}

	public void setFilaVindoDoMqttDaConexao1(Fila filaVindoDoMqttDaConexao1) {
		this.filaVindoDoMqttDaConexao1 = filaVindoDoMqttDaConexao1;
	}

	public Double getTamanhoDaFila1() {
		return tamanhoDaFila1;
	}

	public void setTamanhoDaFila1(Double tamanhoDaFila1) {
		this.tamanhoDaFila1 = tamanhoDaFila1;
	}

	

	public Container getC1() {
		return c1;
	}

	public void setC1(Container c1) {
		this.c1 = c1;
	}

	

	public void conectartdag(String subscribeDaConexao) {
		Container c1 = new Container();
		this.c1 = c1;
		c1.conexao(subscribeDaConexao);
		if (c1.getConn().isConnected()) {
			System.out.println("Estamos conectados no temperaturaDaSolucao");
		}
		while (c1.getValorRecebidoDoCallback1() == null) {
			
		}
		System.out.println("Nao sou mais null");
		setTamanhoDaFila1(c1.getValorRecebidoDoCallback1()); 
		
	}	
	
	@RequestMapping(value = "/temperaturadaagua", method = RequestMethod.GET)
	public List<Double> graficoTemperaturadaagua() {
			
		if (!validarConexao()) {
			conectartdag("pi/sng/tempAg");
		}
			
		//setTamanhoDaFila(c.getValorRecebidoDoCallback()); 
		System.out.println("Vamos ver se eh null " + getTamanhoDaFila1());		
		//model.addAttribute("message", getTamanhoDaFila());
		System.out.println("fila dentro do request " + c1.getFilaDeValoresDoCallBack1().mostrarFila());
		return c1.getFilaDeValoresDoCallBack1().mostrarFila();
	}
	
	public boolean validarConexao() {
		if (this.c1 != null) {
			return true;
		}
		
		return false;
	}
	
	/*@RequestMapping ("/temperaturadaagua")
	public List<Integer> arquivoJsonTemperaturaDaAgua(){
		return asList(1);
	}*/
	

}
