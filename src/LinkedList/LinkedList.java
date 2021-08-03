package LinkedList;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    private class Link{
        public E value;
        public Link next;
        public Link(E val, Link next){
            this.value = val;
            this.next = next;
        }
        
        public String toString() {
            String str = "" + this.value;
            if(this.next != null)
                str += ", " + this.next;
            
            return str;
        }
    }
    
    private class LinkedListIterator implements Iterator<E> {
        Link actual;
        
        public LinkedListIterator() {
            actual = head;
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
    
    protected Link head;
    protected int count;
    
    public LinkedList(){
        this.head = null;
    }
    
    public boolean isEmpty(){
        return this.head == null;
    }
        
    public void removeFirst() throws IllegalStateException{
        if (this.head == null)
            throw new IllegalStateException();

        this.head = this.head.next;
        this.count--;
    }
    
    public E remove(E elemento) throws IllegalStateException {
        if(isEmpty()) throw new IllegalStateException("Lista vacía...");
        
        Link actual = this.head;
        Link aux = actual;
        while(!actual.value.equals(elemento)) {
            if(actual.next == null)
                throw new IllegalStateException("No encontrado...");
            aux = actual;
            actual = actual.next;
        }
        if(actual == this.head)
            this.head = actual.next;
        else
            aux.next = actual.next;
        this.count--;
        return actual.value;
    }
    
    public E removeFor(int k) throws IllegalStateException {
        if(k >= this.count) throw new IllegalStateException("Índice no válido...");
        if(isEmpty()) throw new IllegalStateException("Lista vacía...");
        
        Link actual = this.head;
        if(k == 0) {
            this.head = this.head.next;
            this.count--;
        }
        else {
            Link aux = null;
            for(int i = 0; i < k; i++) {
                aux = actual;
                actual = actual.next;
            }
            aux.next = actual.next;
        }
        
        return actual.value;
    }
    
    public E headValue(){
        return this.head.value;
    }

    public boolean contains(E elemento){
        Link actual = this.head;
        while(actual !=null){
            if (actual.value.equals(elemento)){
                return true;
            }
            else {
                actual=actual.next;
            }
        }
        return false;
    }

    public int length(){
        return this.count;
    }
    
    public void insertToBegin(E element) {
        this.head = new Link(element, this.head);
        count++;
    }
    
    public void insert(E element, int k) {
        if( k == 0)
            this.head = new Link(element, this.head);
        else {
            int i = 1;
            Link preKesimo = this.head;
            while(i < k) {
                preKesimo = preKesimo.next;
                i++;
            }
            preKesimo.next = new Link(element, preKesimo.next);     
        }
        this.count++;
    }
        
    public E searchFor(int k) {
        if(k == 0)
            return this.headValue();
    
        int i = 0;
        Link Kesimo = this.head;
        while(i < k) {
            Kesimo = Kesimo.next;
            i++;
        }
        return Kesimo.value;
    }
        
    public String toString() {
        String str = "" + this.head; 
        
        return str;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
}
