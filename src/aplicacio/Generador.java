package aplicacio;

import domini.Taulell;
import prova.GraellaInicial;

public abstract class Generador {
	
	 static GraellaInicial graellaInicial;

	public static void generarTaulell(Taulell t) throws Exception {
		graellaInicial = new GraellaInicial();
		graellaInicial.graellaInicial(t);
	}

	public abstract void graellaInicial(Taulell t) throws Exception;
	
}
