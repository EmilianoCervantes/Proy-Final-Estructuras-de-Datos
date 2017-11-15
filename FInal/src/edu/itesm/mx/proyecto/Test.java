package edu.itesm.mx.proyecto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test <T>extends JFrame{

	//JFrames
	public JFrame principal;
	public JFrame tweets;
	public JFrame grafos;
	public JFrame diccionario;
	//Principal	
	//Botones
	public JButton bTweets;
	public JButton bDiccionario;
	public JButton bCDiccionario;
	//Generales
	public Twitter t;
	public JButton bGrafos;
	public JButton bcerrar=new JButton("Cerrar");
	public JButton bregresar=new JButton("Regresar");
	public JList lista,dlista;
	public DefaultListModel modelo;
	public JPanel p;
	int r=(int) (Math.random()*255+1), g= (int) (Math.random()*255+1),b=(int)(Math.random()*255+1);
	public Color color=new Color(r,g,b);
	public Color color2=new Color(255-r,255-g ,255-b);
	public String s;
	//Diccionario
	public String [] opciones = {"Inglés", "Español"};
	public JTextField txtpa=new JTextField(10);
	public JComboBox box = new JComboBox(opciones);
	public JButton bagregar,bmostrar,bborrar;

	//Grafos
	//Tweets
	public JButton SubGrafo,felices,tristes,neutro;


	public static void main (String args[]){
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		new Test();
	}
	public Test(){

		drawPrincipal();

	}
	public void drawTweet(){
		tweets=new JFrame();
		tweets.getContentPane().setBackground(color);
		tweets.getContentPane().setForeground(color2);
		tweets.setSize(800,600);
		tweets.setTitle("Resultados de la Búsqueda");
		tweets.setLayout(new BorderLayout());
		tweets.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Twitter t=new Twitter();
		DefaultListModel<String> l=new DefaultListModel();
		s=JOptionPane.showInputDialog(principal, "Escriba el tema que desea ver: ");
		l=t.consultaTwitter(s,l);
		lista = new JList(l);
		tweets.add(new JScrollPane(lista), BorderLayout.CENTER);
		bGrafos=new JButton("Ver Grafos");
		SubGrafo=new JButton("Ver Subgrafo");
		felices=new JButton("Ver Grafo Feliz");
		tristes=new JButton("Ver Grafo Triste");
		neutro=new JButton("Ver Grafo Neutro");
		JPanel z=new JPanel();
		z.add(bGrafos);
		z.add(bregresar);	z.add(SubGrafo);
		z.add(tristes);	z.add(felices);
		z.add(neutro);
		z.setBackground(color);
		tweets.add(z, BorderLayout.SOUTH);
		bregresar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tweets.dispose();
				drawPrincipal();
				principal.setVisible(true);
			}

		});
		bGrafos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawGrafo(s);
			}

		});
		SubGrafo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String h=lista.getSelectedValue().toString();
				drawGrafo(s,h);
			}
		});
		felices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawGrafo(s,1);
			}



		});
		tristes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawGrafo(s,-1);
			}

		});
		neutro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawGrafo(s,0);
			}

		});

	}
	public void drawGrafo(String str, int gh){
		t = new Twitter();
		CircularDoubleLinkedList <String>c =  t.consultaTwitter(str);
		int tamano;
		Grafo g = new Grafo();
		//Llenar
		tamano = c.size();
		for(int w = 0; w < c.size(); w++){
			g.put(c.get(w), c);
		}
		int comp = 0;
		String uno="", dos="";
		Graph grafo = new SingleGraph("Grafo de tweets");
		for(String nodo : g.keySet()){
			int aa = 0;
			grafo.addNode(nodo);
			String[] n = nodo.split(" ");
			for(String x : g.keySet()){
				String[] e = x.split(" ");
				for(String v : n){
					for(String w : e){
						if(w.equals(v)){
							aa++;
						}
					}
				}
				if(comp<aa && x!=nodo){
					uno = nodo;
					dos = x;
					comp=aa;
				}
			}
		}
		Diccionario dic = new Diccionario();
		int sen =0;
		for(String nodo:g.keySet()){
			Node n = grafo.getNode(nodo);
			n.addAttribute("ui.label",nodo);
			int aristas = 0;
			for(String a : g.keySet()){
				if(nodo.compareTo(a)!=0 ){
					try{
						Edge arista=grafo.addEdge(nodo+a, nodo, a, false);
						arista.addAttribute("ui.label", nodo);
						aristas++;
						sen=dic.sentimientos(nodo);
						if(gh==0){
							if(sen==gh){
								n.addAttribute("ui.label",nodo);
								n.setAttribute("ui.style", "fill-color: rgb(0,255,0); size: "+(20+2*aristas)+"px;");

							}
						}else if(gh==1){
							if(sen>0){
								n.addAttribute("ui.label",nodo);
								n.setAttribute("ui.style", "fill-color: rgb(255,0,0); size: "+(20+2*aristas)+"px;");

							}
						}else{
							if(sen<0){
								n.addAttribute("ui.label",nodo);
								n.setAttribute("ui.style", "fill-color: rgb(0,0,255); size: "+(20+2*aristas)+"px;");

							}
						}
					}catch(Exception ex){
					}
				}
			}
		}
		g = new Grafo();
		c=new CircularDoubleLinkedList();
		String tweetMayor=c.get(0);
		int cont = 0, vacios=0,mayor=0;
		for(int i=0;i<c.size();i++){
			String nodo2=c.get(i)+""+i;
			//Hacer grafo en memoria
			CircularDoubleLinkedList c1=new CircularDoubleLinkedList();
			String[] t1 = nodo2.split(" ");
			for(int j=0;j<c.size();j++){
				if(i!=j){
					for(int k=0;k<t1.length;k++){
						if(c.get(j).contains(t1[k]));
						cont++;
					}
				}
				if(cont>0){
					c1.addLast(c.get(j));
				}
			}
			if(c1.size()==0){
				vacios++;
			}
			if(c1.size()>mayor){
				tweetMayor=c.get(i);
			}
			g.put(c.get(i), c1);
			
		}

		grafo.display();
		System.out.println("Número de Tweets vacio"+vacios);
		System.out.println("El tweet más relacionado"+ tweetMayor);

	}

	public void drawGrafo(String s, String h) {
		t = new Twitter();
		CircularDoubleLinkedList <String>c =  t.consultaTwitter(s);

		int tamano;
		Grafo g = new Grafo();
		//Llenar
		tamano = c.size();
		for(int w = 0; w < c.size(); w++){
			g.put(c.get(w), c);
		}
		int comp = 0;
		String uno="", dos="";
		Graph grafo = new SingleGraph("Grafo de tweets");
		for(String nodo : g.keySet()){
			int aa = 0;
			grafo.addNode(nodo);
			String[] n = nodo.split(" ");
			for(String x : g.keySet()){
				String[] e = x.split(" ");
				for(String v : n){
					for(String w : e){
						if(w.equals(v)){
							aa++;
						}
					}
				}
				if(comp<aa && x!=nodo){
					uno = nodo;
					dos = x;
					comp=aa;
				}
			}
		}
		Diccionario dic = new Diccionario();
		int sen =0;
		for(String nodo:g.keySet()){
			Node n = grafo.getNode(nodo);
			n.addAttribute("ui.label",nodo);
			int aristas = 0;
			for(String a : g.keySet()){
				if(nodo.compareTo(a)!=0 ){
					try{
						Edge arista=grafo.addEdge(nodo+a, nodo, a, false);
						arista.addAttribute("ui.label", nodo);
						aristas++;
						sen=dic.sentimientos(nodo);
						if(sen==0){
							n.setAttribute("ui.style", "fill-color: rgb(0,255,0); size: "+(10+3*aristas)+"px;");
						}else if(sen<0){
							n.setAttribute("ui.style", "fill-color: rgb(255,0,0); size: "+(10+3*aristas)+"px;");
						}else if(sen>0){
							n.setAttribute("ui.style", "fill-color: rgb(0,0,255); size: "+(10+3*aristas)+"px;");
						}

					}catch(Exception ex){

					}
				}
			}
		}
		g = new Grafo();
		c=new CircularDoubleLinkedList();
		String tweetMayor=c.get(0);
		int cont = 0, vacios=0,mayor=0;
		for(int i=0;i<c.size();i++){
			String nodo2=c.get(i)+""+i;
			//Hacer grafo en memoria
			CircularDoubleLinkedList c1=new CircularDoubleLinkedList();
			String[] t1 = nodo2.split(" ");
			for(int j=0;j<c.size();j++){
				if(i!=j){
					for(int k=0;k<t1.length;k++){
						if(c.get(j).contains(t1[k]));
						cont++;
					}
				}
				if(cont>0){
					c1.addLast(c.get(j));
				}
			}
			if(c1.size()==0){
				vacios++;
			}
			if(c1.size()>mayor){
				tweetMayor=c.get(i);
			}
			g.put(c.get(i), c1);
		
		}

		grafo.display();

	}
	public void drawGrafo(String str){
		t = new Twitter();
		CircularDoubleLinkedList <String>c =  t.consultaTwitter(str);

		int tamano;
		Grafo g = new Grafo();
		//Llenar
		tamano = c.size();
		for(int w = 0; w < c.size(); w++){
			g.put(c.get(w), c);
		}
		int comp = 0;
		String uno="", dos="";
		Graph grafo = new SingleGraph("Grafo de tweets");
		for(String nodo : g.keySet()){
			int aa = 0;
			grafo.addNode(nodo);
			String[] n = nodo.split(" ");
			for(String x : g.keySet()){
				String[] e = x.split(" ");
				for(String v : n){
					for(String w : e){
						if(w.equals(v)){
							aa++;
						}
					}
				}
				if(comp<aa && x!=nodo){
					uno = nodo;
					dos = x;
					comp=aa;
				}
			}
		}
		Diccionario dic = new Diccionario();
		int sen =0;
		for(String nodo:g.keySet()){
			Node n = grafo.getNode(nodo);
			n.addAttribute("ui.label",nodo);
			int aristas = 0;
			for(String a : g.keySet()){
				if(nodo.compareTo(a)!=0 ){
					try{
						Edge arista=grafo.addEdge(nodo+a, nodo, a, false);
						arista.addAttribute("ui.label", nodo);
						aristas++;
						sen=dic.sentimientos(nodo);
						if(sen==0){
							n.setAttribute("ui.style", "fill-color: rgb(0,255,0); size: "+(10+3*aristas)+"px;");
						}else if(sen<0){
							n.setAttribute("ui.style", "fill-color: rgb(255,0,0); size: "+(10+3*aristas)+"px;");
						}else if(sen>0){
							n.setAttribute("ui.style", "fill-color: rgb(0,0,255); size: "+(10+3*aristas)+"px;");
						}

					}catch(Exception ex){

					}
				}
			}
		}
		g = new Grafo();
		c=new CircularDoubleLinkedList();
		String tweetMayor=c.get(0);
		int cont = 0, vacios=0,mayor=0;
		for(int i=0;i<c.size();i++){
			String nodo2=c.get(i)+""+i;
			//Hacer grafo en memoria
			CircularDoubleLinkedList c1=new CircularDoubleLinkedList();
			String[] t1 = nodo2.split(" ");
			for(int j=0;j<c.size();j++){
				if(i!=j){
					for(int k=0;k<t1.length;k++){
						if(c.get(j).contains(t1[k]));
						cont++;
					}
				}
				if(cont>0){
					c1.addLast(c.get(j));
				}
			}
			if(c1.size()==0){
				vacios++;
			}
			if(c1.size()>mayor){
				tweetMayor=c.get(i);
			}
			g.put(c.get(i), c1);
		
		}

		grafo.display();

	}
	public void drawDiccionario(){
		diccionario=new JFrame();
		diccionario.getContentPane().setBackground(color);
		diccionario.getContentPane().setForeground(color2);
		diccionario.setSize(800,400);
		diccionario.setTitle("Diccionario");
		diccionario.setLayout(new BorderLayout());
		JPanel izquierda=new JPanel();
		izquierda.setLayout(new GridLayout(3,1,5,5));
		ButtonGroup bg= new ButtonGroup();
		JRadioButton [] rb=new JRadioButton[2];
		rb[0]=new JRadioButton("Palabras Positivas");
		rb[1]=new JRadioButton("Palabras Negativas");
		rb[0].setForeground(color2);
		rb[1].setForeground(color2);
		diccionario.add(new JLabel("Elija un diccionario para editar"), BorderLayout.NORTH);
		bg.add(rb[0]);
		bg.add(rb[1]);
		izquierda.add(box);
		izquierda.add(rb[0]);
		izquierda.add(rb[1]);
		izquierda.setBackground(color);
		izquierda.setForeground(color2);
		diccionario.add(izquierda, BorderLayout.WEST);
		bmostrar=new JButton("Mostrar");
		bagregar=new JButton("Agregar");
		bborrar=new JButton("Borrar");
		bregresar=new JButton("Regresar");	
		p=new JPanel();
		p.add(new JLabel("Escriba su palabra a agregar"));
		p.add(txtpa);
		p.add(bmostrar);
		p.add(bagregar);
		p.add(bborrar);
		p.add(bregresar);
		p.setBackground(color);
		diccionario.add(p,BorderLayout.SOUTH);
		dlista=new JList(new DefaultListModel());
		dlista.setBackground(Color.WHITE);
		diccionario.add(new JScrollPane(dlista));
		Diccionario d=new Diccionario();
		bregresar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				diccionario.dispose();
				drawPrincipal();
				principal.setVisible(true);
			}
		});
		bagregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb[0].isSelected()){
					if(box.getSelectedItem()=="Inglés"){
						try {
							d.agregar(txtpa.getText(), 1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							d.agregar(txtpa.getText(), 0);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}else if(rb[1].isSelected()){
					if(box.getSelectedItem()=="Español"){
						try {
							d.agregar(txtpa.getText(), 3);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							d.agregar(txtpa.getText(), 2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
					}
				}else{
					JOptionPane.showMessageDialog(diccionario, "Elija un diccionario");		
				}
				JOptionPane.showMessageDialog(diccionario, "Su palabra ha sido agregada existosamente");
			}
		});
		bmostrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				if(rb[0].isSelected()){
					if(box.getSelectedItem()=="Inglés"){
						dlista.setModel(d.mostrar(new DefaultListModel(), 1));
						dlista.setBackground(Color.WHITE);
						dlista.updateUI();
					}else{
						dlista.setModel(d.mostrar(new DefaultListModel(), 0));
						dlista.setBackground(Color.WHITE);
						dlista.updateUI();
					}
				}else if(rb[1].isSelected()){
					if(box.getSelectedItem()=="Español"){
						dlista.setModel(d.mostrar(new DefaultListModel(), 3));
						dlista.setBackground(Color.WHITE);
						dlista.updateUI();
					}else{
						dlista.setModel(d.mostrar(new DefaultListModel(), 2));
						dlista.setBackground(Color.WHITE);
						dlista.updateUI();
					}
				}else{
					JOptionPane.showMessageDialog(diccionario, "Elija un diccionario");		
				}
			}
		});
		bborrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb[0].isSelected()){
					if(box.getSelectedItem()=="Inglés"){
						try {
							d.borrar(txtpa.getText(), 1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							d.borrar(txtpa.getText(), 0);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}else if(rb[1].isSelected()){
					if(box.getSelectedItem()=="Español"){
						try {
							d.borrar(txtpa.getText(), 3);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							d.borrar(txtpa.getText(), 2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
					}
				}else{
					JOptionPane.showMessageDialog(diccionario, "Elija un diccionario");		
				}
				JOptionPane.showMessageDialog(diccionario, "Su palabra ha sido agregada existosamente");
			}
		});


	}
	public void drawPrincipal(){

		principal=new JFrame();
		principal.getContentPane().setBackground(color);
		principal.getContentPane().setForeground(color2);
		principal.setSize(150,200);
		principal.setTitle("      Swittter      ");
		principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal.setLayout(new FlowLayout(FlowLayout.CENTER));
		bTweets=new JButton("     Ver Tweets     ");
		bDiccionario=new JButton(" Diccionarios");
		principal.add(new JLabel("     Swittter     "));
		bTweets.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawTweet();
				principal.dispose();
				tweets.setVisible(true);
			}
		});
		bDiccionario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawDiccionario();
				principal.dispose();
				diccionario.setVisible(true);
			}

		});

		bcerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				principal.dispose();
				System.exit(0);	
			}

		});
		principal.add(bTweets);
		principal.add(bDiccionario);
		principal.add(bcerrar);
		principal.setVisible(true);


	}

}