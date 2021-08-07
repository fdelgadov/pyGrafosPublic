package Graph;

public class Test {
    public static void main(String[] args) throws Exception {
        //graphTest();
        //dijkstra();
        //ejercicio4();
        //isIncludeTest();
        paths();
    }

    public static void graphTest() throws Exception{
        Graph<Integer> graph = new Graph<Integer>();
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
        System.out.println("bfs()");
        graph.bfs();
        graph.printVertexEdgeLabel();
    }

    public static void dijkstra() throws Exception{
        Graph<String> graph = new Graph<String>();
        graph.insertVertex("s");
        graph.insertVertex("u");
        graph.insertVertex("x");
        graph.insertVertex("v");
        graph.insertVertex("y");
        graph.insertEdge("s", "u", 10);
        graph.insertEdge("s", "x", 5);
        graph.insertEdge("s", "y", 7);
        graph.insertEdge("u", "x", 2);
        graph.insertEdge("u", "v", 1);
        graph.insertEdge("x", "v", 9);
        graph.insertEdge("x", "y", 2);
        graph.insertEdge("v", "y", 4);
        System.out.println(graph);
        System.out.println("dijkstra(s)");
        Object[][] res = graph.dijkstra("s");
        for(Object[] obj : res){
            System.out.println(obj[0] + " | " + obj[1] + " | " + obj[2]);
        }

    }

    public static void ejercicio4() throws Exception{
        Graph<String> graph = new Graph<String>();
        String[] words = {"words", "cords", "corps", "coops", "crops", "drops", "drips", "grips", "gripe", "grape", "graph"};

        for(String word : words){
            graph.insertVertex(word);
        }

        Object[] vertices = graph.vertices();
        for(Object vertex1 : vertices){
            for(Object vertex2 : vertices){
                String v1 = (String) vertex1, v2 = (String) vertex2;
                if(differentChars(v1, v2) == 1 && !graph.areAdjacent(v1, v2)){
                    graph.insertEdge(v1, v2, 0);
                }   
            }
        }

        System.out.println(graph);
    }

    private static int differentChars(String a, String b){
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }

    public static void isIncludeTest() throws Exception {
        Graph<String> graph = new Graph<String>();
        String[] words = {"words", "cords", "corps", "coops", "crops", "drops", "drips", "grips", "gripe", "grape", "graph"};

        for(String word : words){
            graph.insertVertex(word);
        }

        Object[] vertices = graph.vertices();
        for(Object vertex1 : vertices){
            for(Object vertex2 : vertices){
                String v1 = (String) vertex1, v2 = (String) vertex2;
                if(differentChars(v1, v2) == 1 && !graph.areAdjacent(v1, v2)){
                    graph.insertEdge(v1, v2, 0);
                }   
            }
        }

        System.out.println("Graph01\n" + graph);

        Graph<String> graph02 = new Graph<String>();
        graph02.insertVertex("grape");
        graph02.insertVertex("gripe");
        graph02.insertVertex("grips");
        graph02.insertVertex("drips");
        graph02.insertEdge("grape", "gripe", 0);
        graph02.insertEdge("gripe", "grips", 0);
        graph02.insertEdge("grips", "drips", 0);
        System.out.println("Graph02:\n" + graph02);
        System.out.println("isIncluded(g1,g2): " + Graph.isIncluded(graph, graph02));
        System.out.println("G2.insert(apples)");
        graph02.insertVertex("apples");
        System.out.println("isIncluded(g1,g2): " + Graph.isIncluded(graph, graph02));
        System.out.println("G2.remove(apples)");
        graph02.removeVertex("apples");
        System.out.println("isIncluded(g1,g2): " + Graph.isIncluded(graph, graph02));
        System.out.println("insertEdge(grape,drips,0)");
        graph02.insertEdge("grape", "drips", 0);    //tag = 3
        System.out.println("isIncluded(g1,g2): " + Graph.isIncluded(graph, graph02));
        System.out.println("removeEdge(3)");
        graph02.removeEdge(3);
        System.out.println(graph02);
        System.out.println("isIncluded(g1,g2): " + Graph.isIncluded(graph, graph02));
    }

    public static void paths() throws Exception {
        Graph<String> graph = new Graph<String>();
        graph.insertVertex("I");
        graph.insertVertex("H");
        graph.insertVertex("G");
        graph.insertVertex("F");
        graph.insertVertex("E");
        graph.insertVertex("D");
        graph.insertVertex("C");
        graph.insertVertex("B");
        graph.insertVertex("A");
        graph.insertEdge("A", "D", 0);
        graph.insertEdge("A", "C", 0);
        graph.insertEdge("A", "B", 0);
        graph.insertEdge("B", "C", 0);
        graph.insertEdge("B", "F", 0);
        graph.insertEdge("B", "E", 0);
        graph.insertEdge("C", "F", 0);
        graph.insertEdge("D", "G", 0);
        graph.insertEdge("F", "G", 0);
        graph.insertEdge("H", "I", 0);
        System.out.println(graph);
        System.out.println("dfs()");
        graph.dfs();
        System.out.println("bfs()");
        graph.bfs();
    }
}
