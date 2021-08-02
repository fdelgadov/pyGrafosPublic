package Graph;

import Exceptions.DuplicateItemException;

public class Test {
    public static void main(String[] args) throws Exception {
        Graph<Integer, Integer> graph = new Graph<Integer, Integer>();
        graph.insertVertex(20);
        System.out.println(graph);
        graph.insertVertex(30);
        System.out.println(graph);
        try {
            graph.insertVertex(20);
            System.out.println(graph);
        }
        catch(DuplicateItemException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("insertEdge(20,30,5)");
        graph.insertEdge(20, 30, 5);
        try {
            System.out.println("insertEdge(20,10,5)");
            graph.insertEdge(20, 10, 2);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(graph);
    }
}
