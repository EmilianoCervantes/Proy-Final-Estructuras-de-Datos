package edu.itesm.mx.proyecto;
import javax.swing.DefaultListModel;
public class AVL<T> {
	private NodoAVL<T> raiz;

	public AVL(){
		raiz = null;
	}
	public void agregar( T dato ){
		raiz = agregar( dato, raiz );
	}
	private NodoAVL agregar( T dato , NodoAVL nodo ){
		if(nodo==null){
			nodo=new NodoAVL(dato);
		}else if(((Comparable)dato).compareTo(nodo.getDato())> 0 ) {
			nodo.setDerecho(agregar( dato, nodo.getDerecho()));
			if( caltura( nodo.getDerecho() ) - caltura( nodo.getIzquierdo() ) == 2 )
				if(((Comparable)dato).compareTo( nodo.getDerecho().getDato() ) < 0 ){
					nodo = magiadoblederecha( nodo );
				}
				else{ 
					nodo = rotacionderecha( nodo ); 
				}
		}else if( ((Comparable)dato).compareTo(nodo.getDato()) < 0 ) {

			nodo.setIzquierdo(agregar( dato, nodo.getIzquierdo()));
			if( caltura( nodo.getIzquierdo() ) - caltura( nodo.getDerecho() ) == 2 ){
				if( ((Comparable)dato).compareTo( nodo.getIzquierdo().getDato() ) > 0 ){
					nodo = magiadobleizquierda( nodo );
				}
				else{ 
					nodo = rotacionizquierda( nodo ); 
				}
			}
		}
		nodo.setAltura( max( caltura( nodo.getIzquierdo() ), caltura( nodo.getDerecho() ) ) + 1);
		return nodo;
	}
	private static NodoAVL rotacionderecha( NodoAVL nodo ){
		NodoAVL actual = nodo.getDerecho();
		nodo.setDerecho(actual.getIzquierdo());
		actual.setIzquierdo(nodo);
		nodo.setAltura(max( caltura(nodo.getIzquierdo()), caltura(nodo.getDerecho()) ) + 1) ;
		actual.setAltura(max( caltura( actual.getDerecho() ), nodo.getAltura() ) + 1) ;
		return actual;
	}
	private static NodoAVL rotacionizquierda( NodoAVL nodo ){
		NodoAVL actual = nodo.getIzquierdo();
		nodo.setIzquierdo(actual.getDerecho());
		actual.setDerecho(nodo);
		nodo.setAltura(max( caltura(nodo.getIzquierdo()), caltura(nodo.getDerecho())) + 1);
		actual.setAltura(max( caltura( actual.getIzquierdo() ), nodo.getAltura() ) + 1);
		return actual;
	}


	private static NodoAVL magiadoblederecha( NodoAVL nodo ){
		nodo.setDerecho(rotacionizquierda( nodo.getDerecho() ));
		return rotacionderecha( nodo );
	}
	private static NodoAVL magiadobleizquierda( NodoAVL nodo ){
		nodo.setIzquierdo( rotacionderecha( nodo.getIzquierdo() ));
		return rotacionizquierda( nodo );
	}


	private static int caltura( NodoAVL nodo ){
		if(nodo==null){
			return -1;
		}else{return max(1+caltura(nodo.getIzquierdo()),1+caltura(nodo.getDerecho()));
		}

	}
	public void inOrden(){
		inOrden(raiz);
	}

	private void inOrden(NodoAVL<T> nodo){
		if(nodo != null){
			inOrden(nodo.getIzquierdo());
			System.out.println(nodo.getDato());
			inOrden(nodo.getDerecho());
		}
	}

	public void preOrden(){
		preOrden(raiz);
	}

	private void preOrden(NodoAVL<T> nodo){
		if(nodo != null){
			System.out.println(nodo.getDato());
			preOrden(nodo.getIzquierdo());
			preOrden(nodo.getDerecho());
		}

	}
	public DefaultListModel inOrden(DefaultListModel m){
		return inOrden(raiz,m);
	}

	private DefaultListModel inOrden(NodoAVL<T> nodo,DefaultListModel m){
		if(nodo != null){
			m.addElement(nodo.getDato());
		}
		return m;
	}
	private static int max( int a, int b ){
		if(a>=b){
			return a;
		}else{
			return b;
		}
	}

	public void postOrden(){
		postOrden(raiz);
	}

	private void postOrden(NodoAVL<T> nodo){
		if(nodo != null){
			postOrden(nodo.getIzquierdo());
			postOrden(nodo.getDerecho());
			System.out.println(nodo.getDato());
		}
	}

	public int cuentaNodos(){
		return cuentaNodos(raiz);
	}

	private int cuentaNodos(NodoAVL<T> nodo){
		return nodo == null ? 0 : cuentaNodos(nodo.getIzquierdo()) + cuentaNodos(nodo.getDerecho()) + 1;
	}

	public boolean isEmpty(){
		return raiz == null;
	}


	private String toString( NodoAVL<T> nodo ){
		if(nodo != null){
			return  nodo.getDato().toString() + " : "  +  
					toString(nodo.getIzquierdo()) + " : " + 
					toString(nodo.getDerecho());
		}
		return "";
	}

	public String toString(){

		return toString(raiz);
	}

	public boolean esCompleto(){
		return esCompleto(raiz);	
	}

	private boolean esCompleto(NodoAVL<T> nodo){
		if(nodo != null){
			if((nodo.getIzquierdo() == null && nodo.getDerecho() == null)){
				return true;
			}else{
				return nodo.getIzquierdo() != null && nodo.getDerecho() != null ? esCompleto(nodo.getDerecho()) && esCompleto(nodo.getIzquierdo()) : false;
			}
		}
		return false;
	}
}