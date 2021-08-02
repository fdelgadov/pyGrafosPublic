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
    }
}
