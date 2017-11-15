package edu.itesm.mx.proyecto;

import java.lang.reflect.*;

public class HashTable<K, D>{

	private Dato[] tabla;

	public HashTable(int size){
			tabla = (Dato[]) Array.newInstance(Dato.class, size);
	}

	private int hash(K llave){
			return Math.abs(llave.hashCode());
	}

	public void put(K llave, D dato){
			int  indice = hash(llave) % tabla.length;
			tabla[indice] = new Dato(llave, dato);
	}

	public D get(K llave){
		int indice = hash(llave) % tabla.length;
		if(tabla[indice] != null)
			return tabla[indice].dato;
		else
			return null;
	}


	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (Dato data : tabla) {
			if(data != null){
				buffer.append(data.llave +  "|" +  data.dato + " : ");
			}else{
				buffer.append(data);
			}
		}
		return buffer.toString();
	}
	
	private class Dato{
			K llave;
			D dato;
			public Dato(K k, D d){
					dato = d;
					llave = k;
			}
	}

}