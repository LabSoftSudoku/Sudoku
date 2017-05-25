package domini;

import java.sql.Timestamp;

public class Partida {
	
	private int id;
	private Timestamp timestamp;
	private Taulell taulell;
	
	public Partida(int id){
		this.id = id;
		timestamp = new Timestamp(System.currentTimeMillis());
	}
	

	public int getId() {
		return id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}


	public Taulell getTaulell() {
		return taulell;
	}
	
	/*
	public void setId(int id) {
		this.id = id;
	}
	*/
	

}
