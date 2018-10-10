package com.tutorial.App.EstruturaDeDados;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Fila {

	private TipoFila tipoFila;

	

	public TipoFila getTipoFila() {
		return tipoFila;
	}

	public void setTipoFila(TipoFila tipoFila) {
		this.tipoFila = tipoFila;
	}

	LinkedList<Double> f = new LinkedList<Double>();

	public void enfileirar(TipoFila tipoFila) {
		TipoFila tf = new TipoFila(null);
		tf = tipoFila;
		this.tipoFila = tf;
		if (f.size() == 0) {
			f.add(0, tf.getValor());
		} else {
			if (f.size() < 10) {
				f.add(tf.getValor());
				
			} else {
				f.removeFirst();
				f.addLast(tf.getValor());
				
			}

		}

	}

	public void desenfileirar() {
		f.removeFirst();

	}

	public List<Double> mostrarFila() {
		return f;
	}

	/*public Double mostrarUltimoDaLista() {
		return f.getLast().getValor();
	}*/
	
	public int mostrarTamanhoDaFila() {
		return f.size();
	}
	
	

}
