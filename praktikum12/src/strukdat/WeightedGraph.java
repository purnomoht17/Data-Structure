package strukdat;
/*
 * Struktur data Graph dengan bobot pada setiap edge
 * sources:
 * 1. https://www.lavivienpost.net/weighted-graph-as-adjacency-list/
 * 2. https://www.lavivienpost.net/find-shortest-path-using-dijkstras-algorithm/
 *
*/

import java.lang.reflect.Array;
import java.util.*;


class Edge<T> {
	T vertex; //the vertex
	T neighbor; //connected vertex
	int weight; //weight

	//Constructor, Time O(1) Space O(1)
	public Edge(T u, T v, int w) {
		this.vertex = u;
		this.neighbor = v;
		this.weight = w;
	}

	//Time O(1) Space O(1)
	@Override
	public String toString() {
		return "(" + vertex + "," + neighbor + "," + weight + ")";
	}
}

public class WeightedGraph<T> {
    //Map<T, LinkedList<Edge<T>>> adj;
	private Map<T, SingleList<Edge<T>>> adj;
	boolean directed;

	//Constructor, Time O(1) Space O(1)
	public WeightedGraph (boolean type) {
        adj = new HashMap<>();
		directed = type; // false: undirected, true: directed
	}

    //Add edges including adding nodes, Time O(1) Space O(1)
	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new SingleList<>()); //add node
		adj.putIfAbsent(b, new SingleList<>()); //add node
		Edge<T> edge1 = new Edge<>(a, b, w);
		adj.get(a).pushQ(edge1);//add(edge1); //add edge
		if (!directed) { //undirected
			Edge<T> edge2 = new Edge<>(b, a, w);
			adj.get(b).pushQ(edge2);
		}
	}



    //Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key: adj.keySet()) {
			//System.out.println(key.toString() + " : " + adj.get(key).toString());
            System.out.print(key.toString() + " : ");
			SingleList<Edge<T>> thelist = adj.get(key);
			Node<Edge<T>> curr = thelist.head;
			while(curr != null) {
				System.out.print(curr.data);
				curr = curr.next;
			}
			System.out.println();
		}
	}

	//DFS
	public void DFS(T src) {
		if (!adj.containsKey(src)) //invalid input
			return;
		HashMap<T, Boolean> visited = new HashMap<>();
	    helper(src, visited);
	    System.out.println();
	}
	private void helper(T v, HashMap<T, Boolean> visited) {
	    visited.put(v, true);
	    System.out.print(v.toString() + " ");
	    SingleList<Edge<T>> thelist = adj.get(v);
		Node<Edge<T>> curr = thelist.head;
		while(curr != null) {
			T u = curr.data.neighbor;
	        if (visited.get(u) == null)
	            helper(u, visited);
			curr = curr.next;
		}
	}

	//BFS
	public void BFS(T src) {
		if (!adj.containsKey(src)) //invalid input
			return;
		SingleList<T> q = new SingleList<>();
	    HashMap<T, Boolean> visited = new HashMap<>();
	    q.pushQ(src);
	    visited.put(src, true);
	    while (!q.isEmpty()) {
	        T v = q.pop().data;
	        System.out.print(v.toString() + " ");
			SingleList<Edge<T>> thelist = adj.get(v);
			Node<Edge<T>> curr = thelist.head;
			while(curr != null) {
				T u = curr.data.neighbor;
	            if (visited.get(u) == null) {
	                q.pushQ(u);
	                visited.put(u, true);
	            }
				curr = curr.next;
			}
	    }
	    System.out.println();
	}

	/*
	 * Shortest Paths dari vertex v ke semua vertex lainnya
	 * menggunakan Algoritma Dijkstra
	 * ==========================================================
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public void dijkstra(T start) { // N = number of nodes
        Map<T, Integer> res = new HashMap<>();
        PriorityQueue<Map.Entry<T, Integer>> pq =new PriorityQueue<>((a,b) -> (int)(a.getValue() - b.getValue()));
        Map<T,T> prev = new HashMap<>();

		for (T key: adj.keySet())
        	res.put(key, Integer.MAX_VALUE);
        pq.offer(new AbstractMap.SimpleEntry(start, 0));
        res.put(start, 0);
        while (!pq.isEmpty()) {
            T u = pq.poll().getKey();
			SingleList<Edge<T>> thelist = adj.get(u);
			Node<Edge<T>> curr = thelist.head;
			while(curr != null) {
                T v = curr.data.neighbor;  //.connectedVetex;
                int weight = curr.data.weight;
                if (res.get(v) > res.get(u) + weight) {
                    res.put(v, res.get(u) + weight);
                    pq.offer(new AbstractMap.SimpleEntry(v, res.get(v)));
					prev.put(v, u);
                }
				curr = curr.next;
            }
            System.out.println(res);
        }
        /*
		 * Lengkapi bagian ini untuk menampilkan
		 * jalur terpendek dari vertex 'start'
		 * ke vertex lainnya ==============================
		 */
		for (T key: adj.keySet()) {
			if(key != start) {
				SingleList<T> path = new SingleList<T>();
				path.pushS(key);
				T temp = key;
				while(temp != null) {
					temp = prev.get(temp);
					if(temp != null) path.pushS(temp);
				}
				path.cetakList();
			}
		}
		/*
		 * ===================================================
		 */
    }


	//MST menggunakan Algoritm Prim
	public void MSTPrim(T src) {
		if (!adj.containsKey(src)) // invalid input
			return;
		SingleList<Edge<T>> outputList = new SingleList<>();
		//
		Set<T> inMST = new HashSet<>();
		PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

		inMST.add(src);
		SingleList<Edge<T>> startList = adj.get(src);
		Node<Edge<T>> curr = startList.head;
		while (curr != null) {
			pq.offer(curr.data);
			curr = curr.next;
		}

		int totalMST = 0;

		while (!pq.isEmpty()) {
			Edge<T> edge = pq.poll();
			T u = edge.vertex;
			T v = edge.neighbor;

			if (inMST.contains(v)) continue;
			inMST.add(v);
			outputList.pushQ(edge);
			totalMST += edge.weight;

			SingleList<Edge<T>> edgesList = adj.get(v);
			Node<Edge<T>> temp = edgesList.head;
			while (temp != null) {
				if (!inMST.contains(temp.data.neighbor)) {
					pq.offer(temp.data);
				}
				temp = temp.next;
			}
		}

		outputList.cetakList();
		System.out.println("MST Length = " + totalMST);
	}


	//MST menggunakan algoritma kruskal
	public void MSTKruskal() {
		//output: list of edges nodes
		SingleList<Edge<T>> outputList = new SingleList<>();
		int totalMST = 0;

		//add all edges in a list and sort them

		// Create a list of all edges
		List<Edge<T>> allEdges = new ArrayList<>();
		Set<String> uniqueEdges = new HashSet<>();
		for (T key : adj.keySet()) {
			SingleList<Edge<T>> edgeList = adj.get(key);
			Node<Edge<T>> curr = edgeList.head;
			while (curr != null) {
				// Ensure each edge is only added once for undirected graphs
				String edgeKey = key.toString() + "-" + curr.data.neighbor.toString();
				String reverseEdgeKey = curr.data.neighbor.toString() + "-" + key.toString();
				if (!uniqueEdges.contains(edgeKey) && !uniqueEdges.contains(reverseEdgeKey)) {
					allEdges.add(curr.data);
					uniqueEdges.add(edgeKey);
				}
				curr = curr.next;
			}
		}

		// Sort edges based on weight
		allEdges.sort(Comparator.comparingInt(e -> e.weight));

		// Union-Find structure to detect cycles
		Map<T, T> parent = new HashMap<>();
		for (T vertex : adj.keySet()) {
			parent.put(vertex, vertex);
		}

		// Helper function for union-find to find the root
		class UnionFind {
			T find(T v) {
				if (parent.get(v) != v) {
					parent.put(v, find(parent.get(v)));
				}
				return parent.get(v);
			}

			void union(T u, T v) {
				T root1 = find(u);
				T root2 = find(v);
				if (!root1.equals(root2)) {
					parent.put(root1, root2);
				}
			}
		}

		UnionFind uf = new UnionFind();

		// Process edges in ascending order of weight
		for (Edge<T> edge : allEdges) {
			T u = edge.vertex;
			T v = edge.neighbor;

			// Check if the current edge forms a cycle
			if (!uf.find(u).equals(uf.find(v))) {
				uf.union(u, v);
				outputList.pushQ(edge);
				totalMST += edge.weight;
			}
		}

		// Print MST edges
		outputList.cetakList();
		System.out.println("MST Length = " + totalMST);
	}
}
