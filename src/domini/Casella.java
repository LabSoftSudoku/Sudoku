package domini;

class Casella {

	private boolean casellaInicial;
	private int valor;

	public final static int BUIT = -1;

	Casella(){
		this.valor = BUIT;
		this.casellaInicial = false;
	}
	
	public boolean isCasellaInicial() {
		return casellaInicial;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) throws Exception {
		if (valor < 1 || valor > 9){
			throw new Exception("ERROR: El valor "+valor+" és incorrecte");
		} else if (casellaInicial){
			throw new Exception("ERROR: Casella no editable");
		}else {
			this.valor = valor;
		}
	}
	
	public void buidar() throws Exception{
		if (!casellaInicial){
			this.valor = BUIT;
		} else {
			throw new Exception ("ERROR: Casella no editable");
		}
	}

	public boolean esBuit() {
		return this.valor == BUIT;
	}
	
	public void setIsCasellaInicial(){
		this.casellaInicial = true;
	}

}
