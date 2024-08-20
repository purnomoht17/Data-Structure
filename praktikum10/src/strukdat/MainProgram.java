package strukdat;
//import strukdat.Graph;

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
        // Create vertices
        MyVertex v1 = new MyVertex("A");
        MyVertex v2 = new MyVertex("B");
        MyVertex v3 = new MyVertex("C");
        MyVertex v4 = new MyVertex("D");
        MyVertex v5 = new MyVertex("E");
        MyVertex v6 = new MyVertex("F");
        MyVertex v7 = new MyVertex("G");
        MyVertex v8 = new MyVertex("H");
        MyVertex v9 = new MyVertex("I");

        // Create a weighted graph (directed)
        WeightedGraph<MyVertex> WG = new WeightedGraph<>(true);

        WG.addEdge(v1, v2, 1);
        WG.addEdge(v1, v4, 1);
        WG.addEdge(v1, v5, 1);
        WG.addEdge(v2, v5, 1);
        WG.addEdge(v3, v2, 1);
        WG.addEdge(v4, v7, 1);
        WG.addEdge(v5, v6, 1);
        WG.addEdge(v5, v8, 1);
        WG.addEdge(v6, v3, 1);
        WG.addEdge(v6, v8, 1);
        WG.addEdge(v7, v8, 1);
        WG.addEdge(v8, v9, 1);
        WG.addEdge(v9, v6, 1);

        // Print the graph
        System.out.println("directed Graph:");
        WG.printGraph();
        System.out.println();

        System.out.println("DFS Traversal mulai dari 'a' ");
        WG.dfsTraversal(v1);
        System.out.println();

        System.out.println("BFS Traversal mulai dari 'a' ");
        WG.bfsTraversal(v1);
        System.out.println();

        //Delete an edge
        System.out.println("hapus edge (a, b)");
        WG.deleteEdge(v1, v2);
        System.out.println();

        // Print the graph after deleting the edge
        System.out.println("Graph setelah menghapus edge (a, b):");
        WG.printGraph();
        System.out.println();
    }
}