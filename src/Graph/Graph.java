package Graph;

import Exceptions.*;
import LinkedList.LinkedList;

public class Graph<E, F> {
    private class VertexNode {
        public E value;
        public LinkedList<VertexNode> adjacents = new LinkedList<VertexNode>();
        
        public VertexNode(E value) {
            this.value = value;
        }
        
        public String toString() {
            return this.value + "[" + adjacents + "]";
        }
        
        public boolean equals(Object o) {
            if(o.getClass() != this.getClass())
                return false;
            VertexNode other = (VertexNode) o;
            if(other.value.equals(this.value))
                return true;
            else
                return false;
        }
    }
    
    private class EdgeNode {
        public VertexNode vertex;
        public F weight;
        
        public EdgeNode(VertexNode vertex, F weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        
        public String toString() {
            return "(" + this.vertex.value + this.weight + ")";
        }
    }
    
    private LinkedList<VertexNode> vertices = new LinkedList<VertexNode>();
    
    public Graph() {
    }
    
    public void insertVertex(E element) throws DuplicateItemException {
        VertexNode node = new VertexNode(element);
        if(vertices.contains(node))
            throw new DuplicateItemException();
        vertices.insertToBegin(node);
    }
    
    public String toString() {
        return vertices.toString();
    }
}
