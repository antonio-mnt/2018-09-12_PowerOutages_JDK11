package it.polito.tdp.poweroutages.model;

public class Vicino implements Comparable<Vicino>{
	
	private Nerc n;
	private int peso;
	public Vicino(Nerc n, int peso) {
		super();
		this.n = n;
		this.peso = peso;
	}
	public Nerc getN() {
		return n;
	}
	public void setN(Nerc n) {
		this.n = n;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Vicino [n=" + n + ", peso=" + peso + "]";
	}
	@Override
	public int compareTo(Vicino o) {
		return -(this.peso-o.peso);
	}
	
	

}
