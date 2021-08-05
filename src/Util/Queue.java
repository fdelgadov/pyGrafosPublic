package Util;

public class Queue<E extends Comparable<E>> {
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
    
    public Queue(){
        first = last = null;
    }
    
    public E ObtenerValorFinal(){
        return last.value;
    }
    
    public E ObtenerValorInicial(){
        return first.value;
    }
    
    public void Encolar(E element){
        Link temporal = new Link(element, null);
        if (last == null){
            first = last = temporal;
        }
        else{
            last.next = temporal;
            last = temporal;
        }
    }
    
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
    
    public void desencolar(){
        if (first != null) {            
            first = first.next;
            if (first == null){
                last = null;
            }            
        }
    }
    public boolean estaVacio(){
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

    public static void main(String[] args) {
        Queue <Integer> notas = new Queue<Integer>();
        notas.Encolar(10);
        notas.Encolar(20);
        notas.Encolar(25);
        
        System.out.println(notas.ObtenerValorInicial());//10
        System.out.println(notas.ObtenerValorFinal());//25
        
        notas.desencolar();
        notas.desencolar();
        System.out.println(notas.estaVacio());//false
        
        System.out.println(notas.ObtenerValorFinal());//25
        System.out.println(notas.ObtenerValorInicial());//25
        
        notas.Encolar(30);
        notas.Encolar(40);
        notas.Encolar(50);
        notas.desencolar();        
        System.out.println(notas.ObtenerValorInicial());
        System.out.println(notas.ObtenerValorFinal());
        
    }
}
