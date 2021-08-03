package Graph;

import Exceptions.*;
import LinkedList.*;

public class Graph<E, F> {
    private class VertexNode {
        public E value;
        public LinkedList<EdgeNode> adjacents = new LinkedList<EdgeNode>();
        
        public VertexNode(E value) {
            this.value = value;
        }
        
        public boolean isAdjacentTo(VertexNode vertex) {
            for(EdgeNode edgeNode : this.adjacents) {
                if(edgeNode.vertex == vertex)
                    return true;
            }
            return false;
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
        public int tag;
        public F weight;
        
        public EdgeNode(VertexNode vertex, F weight) {
            this.vertex = vertex;
            this.weight = weight;
            this.tag = tagCount;
        }
        
        public EdgeNode(int tag) {
            this.tag = tag;
        }
        
        public String toString() {
            return this.tag + ": (" + this.vertex.value + ", " + this.weight + ")";
        }
        
        public boolean equals(Object o) {
            EdgeNode other = (EdgeNode) o;
            if(other.tag == this.tag || other.vertex == this.vertex)
                return true;
            else
                return false;
        }
    }
    
    private int tagCount = 0;
    private LinkedList<VertexNode> vertices = new LinkedList<VertexNode>();
    
    public Graph() {
    }
    
    public void insertVertex(E element) throws DuplicateItemException {
        VertexNode node = new VertexNode(element);
        if(vertices.contains(node))
            throw new DuplicateItemException();
        vertices.insertToBegin(node);
    }
    
    public void insertEdge(E ver1, E ver2, F element) throws VertexNotFound, DuplicatedEdge {
        VertexNode vertex1 = null, vertex2 = null;
        
        for(VertexNode vertex : this.vertices) {
            if(vertex.value.equals(ver1))
                vertex1 = vertex;
            
            if(vertex.value.equals(ver2))
                vertex2 = vertex;
            
            if(vertex1 != null && vertex2 != null)
                break;
        }
        
        if(vertex1 == null || vertex2 == null)
            throw new VertexNotFound();
        
        if(vertex1.isAdjacentTo(vertex2))
            throw new DuplicatedEdge();
        
        vertex1.adjacents.insertToBegin(new EdgeNode(vertex2, element));
        vertex2.adjacents.insertToBegin(new EdgeNode(vertex1, element));
        this.tagCount++;
    }
    
    public void removeVertex(E v) {
        VertexNode remove = this.vertices.remove(new VertexNode(v));            
        for(VertexNode vertex : this.vertices) {
            try {
                vertex.adjacents.remove(new EdgeNode(remove, null));
            }
            catch(Exception e) {
            }
        }
    }
    
    public void removeEdge(int tag) {
        for(VertexNode vertex : this.vertices) {
            try {
                vertex.adjacents.remove(new EdgeNode(tag));
            }
            catch(Exception e){
            }
        }
    }
    
    public String toString() {
        String res = "";
        
        for(VertexNode vertex : this.vertices) {
            res += vertex + "\n";
        }
        
        return res;
    }
}
