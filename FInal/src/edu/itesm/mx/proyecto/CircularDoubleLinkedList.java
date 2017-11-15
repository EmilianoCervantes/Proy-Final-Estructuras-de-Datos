package edu.itesm.mx.proyecto;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
 
 
public class CircularDoubleLinkedList<T> implements List<T>, Deque<T> {
 
        private Nodo<T> inicio;
        private Nodo<T> fin;

        public CircularDoubleLinkedList(){
            fin = inicio = null;
        }

/*********************************************************/


/*
Completa el método
*/
public CircularDoubleLinkedList<T> subLista( int inicio, int fin){
    CircularDoubleLinkedList<T> vacia = new CircularDoubleLinkedList<T>();

    return vacia;
}

private void subLista( int inicio, int fin, Nodo<T> nodo, CircularDoubleLinkedList<T> subLista){
/*
Completa el método
*/  
}


/*********************************************************/
      
        public void quickSort(){
            
        }

        @Override
        public int size() {
            return size(inicio);
        }

        private int size(Nodo<T> nodo){
            if(isEmpty()){
                return 0;
            }else{
                if(nodo.getSiguiente() != inicio){
                    return 1 + size(nodo.getSiguiente());
                }else{
                    return 1;
                }
            }
        }
   
           @Override
        public T get(int index) {
                // TODO Auto-generated method stub
                if(index >= size() || index < 0) return null;
                Nodo<T> n = inicio;
                int i = 0;
                while(i < index){
                    n = n.getSiguiente();
                    i++;
                }
             
                return n.getDato();
            }

 
        @Override
        public T set(int index, T element) {
                T d = null;
                return d;
        }

 
        @Override
        public void addFirst(T e) {
                // TODO Auto-generated method stub
            if(isEmpty()){
                inicio = fin  = new Nodo(e);
                inicio.setSiguiente(inicio);
                inicio.setAnterior(inicio);
            }else{
                Nodo<T> nuevo = new Nodo(e); //1
                nuevo.setSiguiente(inicio); //2
                nuevo.setAnterior(fin);//3
                fin.setSiguiente(nuevo); //4
                inicio.setAnterior(nuevo); //5
                inicio = nuevo;
            }
               
        }
 
        @Override
        public void addLast(T e) {
                // TODO Auto-generated method stub
             if(isEmpty()){
                addFirst(e);
            }else{
                Nodo<T> nuevo = new Nodo(e);
                nuevo.setAnterior(fin);
                nuevo.setSiguiente(inicio);
                inicio.setAnterior(nuevo);
                fin.setSiguiente(nuevo);
                fin = nuevo;
            }
        }

 
        @Override
        public boolean isEmpty() {
                // TODO Auto-generated method stub
                return inicio == null&& fin==null;
        }

        @Override
        public String toString(){
                String salida = "";
                Nodo<T> aux = inicio;
                int i = 0;
                if(!isEmpty()){
                    do{
                        salida +=  aux.getDato() + " \n";
                        aux = aux.getSiguiente();
                    }while(aux != inicio);
                }
                return salida;
        }
 



/*********************************************************/


        @Override
        public boolean contains(Object o) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public Iterator<T> iterator() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public Object[] toArray() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public <T> T[] toArray(T[] a) {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public boolean add(T e) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean remove(Object o) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean containsAll(Collection<?> c) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean addAll(Collection<? extends T> c) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean addAll(int index, Collection<? extends T> c) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean removeAll(Collection<?> c) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean retainAll(Collection<?> c) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public void clear() {
                // TODO Auto-generated method stub
               
        }
 
 
        @Override
        public void add(int index, T element) {
                // TODO Auto-generated method stub             

        }
 
        @Override
        public T remove(int index) {
                T da = null;
               
                return da;
        }
 
        @Override
        public int indexOf(Object o) {
                // TODO Auto-generated method stub
                int posicion = -1;
           
                return posicion;
        }
 
        @Override
        public int lastIndexOf(Object o) {
                // TODO Auto-generated method stub
                return 0;
        }
 
        @Override
        public ListIterator<T> listIterator() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public ListIterator<T> listIterator(int index) {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public List<T> subList(int fromIndex, int toIndex) {
                // TODO Auto-generated method stub

                CircularDoubleLinkedList<T> lista = new  CircularDoubleLinkedList();
                for ( int i = fromIndex; i <= toIndex ; i++ ) {
                    lista.addFirst(get(i));
                }
                return lista;
        }

 
        @Override
        public boolean offerFirst(T e) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean offerLast(T e) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public T removeFirst() {
            return null;

        }
 
        @Override
        public T removeLast() {
                // TODO Auto-generated method stub
                       
                return null;
        }
 
        @Override
        public T pollFirst() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T pollLast() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T getFirst() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T getLast() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T peekFirst() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T peekLast() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public boolean removeFirstOccurrence(Object o) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean removeLastOccurrence(Object o) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean offer(T e) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public T remove() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T poll() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T element() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public T peek() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public void push(T e) {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public T pop() {
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public Iterator<T> descendingIterator() {
                // TODO Auto-generated method stub
                return null;
        }



 

    }