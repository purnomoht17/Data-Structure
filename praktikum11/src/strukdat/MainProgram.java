package strukdat;
// import strukdat.Graph;
import strukdat.WeightedGraph;

class MyVertex{
	String nodeName;
	MyVertex(String name)
	{
		this.nodeName = name;
	}

    @Override
    public String toString() {
        return (nodeName);
    }
}


public class MainProgram {
    public static void main(String[] args) {
        MyVertex v1 = new MyVertex("v1");
        MyVertex v2 = new MyVertex("v2");
        MyVertex v3 = new MyVertex("v3");
        MyVertex v4 = new MyVertex("v4");
        MyVertex v5 = new MyVertex("v5");
        MyVertex v6 = new MyVertex("v6");
        MyVertex v7 = new MyVertex("v7");
        MyVertex v8 = new MyVertex("v8");
        MyVertex v9 = new MyVertex("v9");
        MyVertex v10 = new MyVertex("v10");

        WeightedGraph<MyVertex> WG = new WeightedGraph<MyVertex>(true); //undirect


//        WG.addEdge(v1, v3, 6);
//        WG.addEdge(v2, v3, 1);
//        WG.addEdge(v2, v4, 2);
//        WG.addEdge(v4, v5, 2);
//        WG.addEdge(v5, v3, 1);

        WG.addEdge(v1, v2, 10);
        WG.addEdge(v1, v4,20);
        WG.addEdge(v1, v5,20);
        WG.addEdge(v1, v6,5);
        WG.addEdge(v1, v7,15);
        WG.addEdge(v2, v3,5);
        WG.addEdge(v2, v4,10);
        WG.addEdge(v3, v2,15);
        WG.addEdge(v3, v4,5);
        WG.addEdge(v4,v5,10);
        WG.addEdge(v5,v6,5);
        WG.addEdge(v7, v6, 10);
        WG.addEdge(v8,v1,5);
        WG.addEdge(v8,v2,20);
        WG.addEdge(v8,v7,5);
        WG.addEdge(v9,v2,15);
        WG.addEdge(v9,v8,20);
        WG.addEdge(v9,v10,10);
        WG.addEdge(v10,v2,5);
        WG.addEdge(v10,v3,15);

//        System.out.println("Undirected Graph:"); WG.printGraph();
//        System.out.println("\nDFS:"); WG.DFS(v1);
//        System.out.println("\nBFS:"); WG.BFS(v1);
        System.out.println("\nDijkstra: "); WG.dijkstra(v9,v3);
    }
}
