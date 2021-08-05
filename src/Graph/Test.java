package Graph;

public class Test {
    public static void main(String[] args) throws Exception {
        Graph<Integer, Integer> graph = new Graph<Integer, Integer>();
        System.out.println("insertVertex(20)");
        graph.insertVertex(20);
        System.out.println(graph);
        System.out.println("insertVertex(30)");
        graph.insertVertex(30);
        System.out.println(graph);
        System.out.println("insertVertex(20)");
        try {
            graph.insertVertex(20);
            System.out.println(graph);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("insertEdge(20,30,5)");
        graph.insertEdge(20, 30, 5);
        System.out.println("insertEdge(20,10,2)");
        try {
            graph.insertEdge(20, 10, 2);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(graph);
        System.out.println("removeVertex(20)");
        graph.removeVertex(20);
        System.out.println(graph);
        System.out.println("removeVertex(10)");
        try {
            graph.removeVertex(10);
            System.out.println(graph);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("insertVertex(10)");
        graph.insertVertex(10);
        System.out.println("insertVertex(40)");
        graph.insertVertex(40);
        System.out.println(graph);
        System.out.println("insertEdge(10,30,5)");
        graph.insertEdge(10, 30, 5);
        System.out.println("insertEdge(10,30,3)");
        try {
            graph.insertEdge(10, 30, 3);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("insertEdge(40,30,3)");
        graph.insertEdge(40, 30, 3);
        System.out.println(graph);
        System.out.println("removeEdge(3)");
        graph.removeEdge(3);
        System.out.println(graph);
        System.out.println("insertEdge(40,30,3)");
        graph.insertEdge(40, 30, 3);
        System.out.println("removeEdge(1)");
        graph.removeEdge(1);
        System.out.println("insertVertex(50)");
        graph.insertVertex(50);
        System.out.println("insertEdge(50,30,0)");
        graph.insertEdge(50, 30, 0);
        System.out.println("insertEdge(50,40,0)");
        graph.insertEdge(50, 40, 7);
        System.out.println(graph);
        System.out.println("dfs()");
        graph.dfs();
        graph.printVertexEdgeLabel();
    }
}
