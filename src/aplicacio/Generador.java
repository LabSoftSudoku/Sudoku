package aplicacio;

import domini.Partida;
import prova.GraellaInicial;

public abstract class Generador {
	
	 static GraellaInicial graellaInicial;

	public static void generarTaulell(Partida t) throws Exception {
		graellaInicial = new GraellaInicial();
		graellaInicial.graellaInicial(t);
	}

	public abstract void graellaInicial(Partida t) throws Exception;
	
}
