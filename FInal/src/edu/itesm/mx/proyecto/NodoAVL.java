package edu.itesm.mx.proyecto;
public class NodoAVL<T> implements Comparable<T>{

	private NodoAVL<T> izquierdo;
	private NodoAVL<T> derecho;
	private T dato;
	private int altura;

	public NodoAVL( T dato){
		this.dato = dato;
		izquierdo = derecho = null;
		altura=0;
	}
	public NodoAVL<T> getIzquierdo() {
		return izquierdo;
	}
	public void setIzquierdo(NodoAVL<T> izquierdo) {
		this.izquierdo = izquierdo;
	}
	public NodoAVL<T> getDerecho() {
		return derecho;
	}
	public void setDerecho(NodoAVL<T> derecho) {
		this.derecho = derecho;
	}
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura( int altura) {
		this.altura=altura;
	}
	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return ((Comparable)o).compareTo(dato);
	}


}
