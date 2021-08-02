package LinkedList;

public class LinkedList<E> {
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
    
    public void remove(E elemento) throws IllegalStateException {
        if(isEmpty()) throw new IllegalStateException();
        
        Link actual = this.head;
        Link aux = actual;
        while(!actual.value.equals(elemento)) {
            if(actual.next == null)
                throw new IllegalStateException();
            aux = actual;
            actual = actual.next;
        }
        if(actual == this.head)
            this.head = actual.next;
        else
            aux.next = actual.next;
        this.count--;
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
}
