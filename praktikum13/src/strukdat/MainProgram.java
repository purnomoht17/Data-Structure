package strukdat;
// import strukdat.Graph;
import strukdat.WeightedGraph;

import java.util.List;

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
        //create vertex
        MyVertex v1 = new MyVertex("a");
        MyVertex v2 = new MyVertex("b");
        MyVertex v3 = new MyVertex("c");
        MyVertex v4 = new MyVertex("d");
        MyVertex v5 = new MyVertex("e");

        
        WeightedGraph<MyVertex> WG = new WeightedGraph<MyVertex>(true); //undirected
        WG.addEdge(v1, v2, 4);
        WG.addEdge(v1, v3, 6);
        WG.addEdge(v2, v3, 1);
        WG.addEdge(v2, v4, 2);
        WG.addEdge(v4, v5, 2);
        WG.addEdge(v5, v3, 1);

        System.out.println("Direct Graph:"); WG.printGraph();
        System.out.println("Topological Ordering " + WG.topologicalSort());

    }
}
