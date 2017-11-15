package edu.itesm.mx.proyecto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Diccionario {
	public String [] archivos={"engNeg.txt","engPos.txt","espNeg.txt","espPos.txt"};
	public DefaultListModel mostrar(DefaultListModel m, int i){
		BufferedReader lector = null;
		try{
			lector = new BufferedReader(new FileReader(archivos[i]));
			while(lector.ready()){
				m.addElement(lector.readLine());	
			}              
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return m;
	}
	public  void agregar(String palabra, int i) throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader(archivos[i]));
		CircularDoubleLinkedList<String> pa=new CircularDoubleLinkedList();
		while(lector.ready()){
			pa.addLast(lector.readLine());	
		}  
		pa.addLast(palabra);
		File f = new File(archivos[i]);
		FileWriter bw = new FileWriter(f,false);
		PrintWriter out = new PrintWriter(bw);
		out.write(pa.toString());
		bw.close();
	}
	public  void borrar(String palabra,  int i) throws IOException{

		BufferedReader lector = new BufferedReader(new FileReader(archivos[i]));
		CircularDoubleLinkedList<String> pa=new CircularDoubleLinkedList();
		while(lector.ready()){
			if(!lector.readLine().equals(palabra)){
				pa.addLast(lector.readLine());
			}	
		}
		File f = new File(archivos[i]);
		FileWriter bw = new FileWriter(f,false);
		PrintWriter out = new PrintWriter(bw);
		out.write(pa.toString());
		bw.close();
	}
	public int sentimientos(String tweet) throws IOException{
		int felicidad=0, tristeza=0;
		for(int i=0;i<archivos.length;i++){
			try {
				BufferedReader lector = new BufferedReader(new FileReader(archivos[i]));
				String line = "";
				while(lector.ready()){
					if( line != null && tweet != null){
						if(line.contains(tweet)){
							if(i%2==0){
								tristeza++;
							}else{
								felicidad++;
							}
						}
					}
					line = lector.readLine();
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return felicidad-tristeza;
	}
}