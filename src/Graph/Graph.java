package Graph;

import Exceptions.*;
import Util.*;

public class Graph<E, F> {
    public int tagCount = 0;
    public LinkedList<VertexNode> vertices = new LinkedList<VertexNode>();
    
    public static final int UNEXPLORED = 0;
    public static final int DISCOVERY = 1;
    public static final int BACK = 2;
    public static final int VISITED = 3;
    public static final int CROSS = 4;

    private class VertexNode {
        public E value;
        public LinkedList<EdgeNode> adjacents = new LinkedList<EdgeNode>();
        public int label; //para dfs -> 0:unexplored, 1:discovery, 2:back
        
        public VertexNode(E value) {
            this.value = value;
        }
        
        public boolean isAdjacentTo(VertexNode vertex) {
            for(EdgeNode edgeNode : this.adjacents) {
                if(edgeNode.vertex == vertex)
                    return true;
            }

            for(EdgeNode edgeNode : vertex.adjacents) {
                if(edgeNode.vertex == this)
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
        public Edge edge;
        
        public EdgeNode(VertexNode vertex, Edge edge) {
            this.vertex = vertex;
            this.edge = edge;
        }

        public EdgeNode(int tag) {
            this.edge = new Edge();
            this.edge.tag = tag;
        }
                
        public String toString() {
            return edge.tag + ": (" + this.vertex.value + ", " + edge.weight + ")";
        }
        
        public boolean equals(Object o) {
            EdgeNode other = (EdgeNode) o;
            if(other.edge.tag == this.edge.tag || other.vertex == this.vertex)
                return true;
            else
                return false;
        }
    }

    private class Edge {
        public int tag;
        public F weight;
        public int label; //para dfs -> 0:unexplored, 1:discovery, 2:back

        public Edge(F weight){
            this.weight = weight;
            this.tag = tagCount;
            tagCount++;
        }
        
        public Edge() {
            
        }
    }

    public void insertVertex(E element) throws DuplicateItemException {
        VertexNode node = new VertexNode(element);
        if(vertices.contains(node))
            throw new DuplicateItemException();
        vertices.insertToBegin(node);
    }
    
    public void insertEdge(E ver1, E ver2, F element) throws VertexNotFound, DuplicatedEdge {
        Object[] nodePair = getNodePair(ver1, ver2);
        VertexNode vertex1 = (VertexNode) nodePair[0], vertex2 = (VertexNode) nodePair[1];
        
        Edge edge = new Edge(element);
        vertex1.adjacents.insertToBegin(new EdgeNode(vertex2, edge));
        vertex2.adjacents.insertToBegin(new EdgeNode(vertex1, edge));
    }
    
    public void removeVertex(E v) {
        VertexNode remove = this.vertices.remove(new VertexNode(v));            
        for(VertexNode vertex : this.vertices) {
            try {
                vertex.adjacents.remove(new EdgeNode(remove, new Edge()));
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void removeEdge(int tag) {
        for(VertexNode vertex : this.vertices) {
            EdgeNode remove = new EdgeNode(tag);
            try {
                vertex.adjacents.remove(remove);
            }
            catch(Exception e){
            }
        }
    }

    public boolean areAdjacent(E v, E w) throws VertexNotFound {
        Object[] nodePair = getNodePair(v, w);
        VertexNode vertex1 = (VertexNode) nodePair[0], vertex2 = (VertexNode) nodePair[1];
        return  vertex1.isAdjacentTo(vertex2);
    }

    private Object[] getNodePair(E v, E w) throws VertexNotFound {
        VertexNode vertex1 = null, vertex2 = null;

        for(VertexNode vertex : this.vertices) {
            if(vertex.value.equals(v))
                vertex1 = vertex;
            
            if(vertex.value.equals(w))
                vertex2 = vertex;
            
            if(vertex1 != null && vertex2 != null)
                break;
        }
        
        if(vertex1 == null || vertex2 == null)
            throw new VertexNotFound();

        Object[] ret = new Object[2];
        ret[0] = vertex1;
        ret[1] = vertex2;
        return ret;
    }

    public void dfs() {
        for(VertexNode vertex : this.vertices){
            vertex.label = UNEXPLORED;
            for(EdgeNode edgeNode : vertex.adjacents) edgeNode.edge.label = UNEXPLORED;
        }

        for(VertexNode vertex : this.vertices) if(vertex.label == UNEXPLORED) dfs(vertex);
    }

    public void dfs(VertexNode v){
        v.label = VISITED;
        for(EdgeNode edgeNode : v.adjacents){
            if(edgeNode.edge.label == UNEXPLORED){
                VertexNode w = this.opposite(v, edgeNode);
                if(w.label == UNEXPLORED){
                    edgeNode.edge.label = DISCOVERY;
                    dfs(w);
                }
                else edgeNode.edge.label = BACK;
            }
        }
    }
    
    public void bfs() {
        for(VertexNode vertex : this.vertices){
            vertex.label = UNEXPLORED;
            for(EdgeNode edgeNode : vertex.adjacents) edgeNode.edge.label = UNEXPLORED;
        }

        for(VertexNode vertex : this.vertices) if(vertex.label == UNEXPLORED) bfs(vertex);
    }

    public void bfs(VertexNode v){
        Queue<VertexNode> list = new Queue<VertexNode>();
        list.enqueue(v);
        v.label = VISITED;

        Queue<VertexNode> listI = list;
        while(!listI.isEmpty()){
            Queue<VertexNode> aux = new Queue<VertexNode>();
            for(VertexNode vertex : listI){
                for(EdgeNode edgeNode : vertex.adjacents){
                    if(edgeNode.edge.label == UNEXPLORED){
                        VertexNode w = opposite(vertex, edgeNode);
                        if(w.label == UNEXPLORED){
                            edgeNode.edge.label = DISCOVERY;
                            w.label = VISITED;
                            aux.enqueue(w);
                        }
                        else w.label = CROSS;
                    }
                }
            }
            listI = aux;
        }
    }

    private VertexNode opposite(VertexNode v, EdgeNode e){
        for(EdgeNode edge : v.adjacents) if(edge == e) return edge.vertex;
        return null;
    }

    public void printVertexEdgeLabel(){
        String edgeLabel = "";
        System.out.println("VertexLabel");
        for(VertexNode vertex : this.vertices){
            System.out.print(vertex.value + ":" + vertex.label + " ");
            for(EdgeNode edgeNode : vertex.adjacents)
                edgeLabel += edgeNode.edge.tag + ":" + edgeNode.edge.label + " ";
            edgeLabel += "\n";
        }
        System.out.println("\n" + edgeLabel);
    }
    
    public String toString() {
        String res = "";
        
        for(VertexNode vertex : this.vertices) {
            res += vertex + "\n";
        }
        
        return res;
    }
}
