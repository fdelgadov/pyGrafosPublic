package Util;

public class PriorityQueue<E extends Comparable<E>> extends Queue<E> {
    public void enqueue(E element) {
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
}
