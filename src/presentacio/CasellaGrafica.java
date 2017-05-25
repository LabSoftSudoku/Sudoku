package presentacio;

import java.awt.Color;

import javax.swing.JTextField;

public class CasellaGrafica extends JTextField{

	private int fila, columna;
	private Color backgroundColor;
	
	public CasellaGrafica (int x, int y) {
		this.fila = x;
		this.columna = y;
		backgroundColor = Color.WHITE;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
