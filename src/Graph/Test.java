package Graph;

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
        catch(Exception e) {
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
        System.out.println("removeVertex(20)");
        graph.removeVertex(20);
        System.out.println(graph);
        try {
            System.out.println("removeVertex(10)");
            graph.removeVertex(10);
            System.out.println(graph);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        graph.insertVertex(10);
        graph.insertVertex(40);
        System.out.println(graph);
        System.out.println("insertEdge(10,30,5)");
        graph.insertEdge(10, 30, 5);
        System.out.println("insertEdge(10,30,3)");
        graph.insertEdge(10, 30, 3);
        System.out.println("insertEdge(40,30,3)");
        graph.insertEdge(40, 30, 3);
        System.out.println(graph);
        System.out.println("removeEdge(3)");
        graph.removeEdge(3);
        System.out.println(graph);
    }
}
