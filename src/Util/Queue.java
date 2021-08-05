package Util;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {
    private Link first;
    private Link last;

    private class Link{
        public E value;     
        public Link next;
        
        public Link(E val, Link next){
            this.value = val;
            this.next = next;
        }
    }

    private class QueueIterator implements Iterator<E>{
        Link actual;
        
        public QueueIterator() {
            actual = first;
        }
        
        public boolean hasNext() {
            if(actual == null)
                return false;
            return true;
        }

        public E next() {
            E aux = this.actual.value;
            this.actual = actual.next;
            return aux;
        }
    }
    
    public Queue(){
        first = last = null;
    }
    
    public E getFinalValue(){
        return last.value;
    }
    
    public E getInitialValue(){
        return first.value;
    }
    
    public void enqueue(E element){
        Link temporal = new Link(element, null);
        if (last == null){
            first = last = temporal;
        }
        else{
            last.next = temporal;
            last = temporal;
        }
    }
    /*
    public void encolarPrioridad(E element) {
    	Link newh = new Link(element, null);
    	if(this.last == null)
    		this.first = this.last = newh;
    	else {
    		Link link = this.first;
    		if(element.compareTo(link.value) < 0) {
    			newh.next = link;
    			this.first = newh;
    		}
    		else {
    			while(true) {
        			if(link == this.last) {
        				link.next = newh;
        				this.last = newh;
        				break;
        			}
        			if(element.compareTo(link.next.value) < 0) {
        				newh.next = link.next;
        				link.next = newh;
        				break;
        			}
        			link = link.next;
    			}
    		}
    	}
    }
    */
    
    public void dequeue(){
        if (first != null) {            
            first = first.next;
            if (first == null){
                last = null;
            }            
        }
    }
    public boolean isEmpty(){
        return this.last == null;
    }
    
    public String toString() {
    	String str = "";
    	Link link = this.first;
    	while(link != null) {
    		str += link.value + "\n";
    		link = link.next;
    	}
    	
    	return str;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public static void main(String[] args) {
        Queue <Integer> notas = new Queue<Integer>();
        notas.enqueue(10);
        notas.enqueue(20);
        notas.enqueue(25);
        
        System.out.println(notas.getInitialValue());//10
        System.out.println(notas.getFinalValue());//25
        
        notas.dequeue();
        notas.dequeue();
        System.out.println(notas.isEmpty());//false
        
        System.out.println(notas.getFinalValue());//25
        System.out.println(notas.getInitialValue());//25
        
        notas.enqueue(30);
        notas.enqueue(40);
        notas.enqueue(50);
        notas.dequeue();        
        System.out.println(notas.getInitialValue());
        System.out.println(notas.getFinalValue());
        
    }
}
