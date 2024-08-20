package strukdat;

import java.util.*;

class Edge<T> {
	T neighbor; // connected vertex
	int weight; // weight

	// Constructor, Time O(1) Space O(1)
	public Edge(T v, int w) {
		this.neighbor = v;
		this.weight = w;
	}

	// Time O(1) Space O(1)
	@Override
	public String toString() {
		return "(" + neighbor + "," + weight + ")";
	}
}

public class WeightedGraph<T> {
	private Map<T, SingleList<Edge<T>>> adj;
	boolean directed;

	// Constructor, Time O(1) Space O(1)
	public WeightedGraph(boolean type) {
		adj = new HashMap<>();
		directed = type; // false: undirected, true: directed
	}

	// Add edges including adding nodes, Time O(1) Space O(1)
	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new SingleList<>()); // add node
		adj.putIfAbsent(b, new SingleList<>()); // add node
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1); // add edge
		if (!directed) { // undirected
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2);
		}
	}

	// Delete edge, Time O(E) Space O(1)
	public void deleteEdge(T a, T b) {
		removeEdgeFromList(a, b);
		if (!directed) {
			removeEdgeFromList(b, a);
		}
	}

	// Helper method to remove an edge from the adjacency list
	private void removeEdgeFromList(T a, T b) {
		if (adj.containsKey(a)) {
			SingleList<Edge<T>> list = adj.get(a);
			Node<Edge<T>> curr = list.head;
			while (curr != null) {
				if (curr.data.neighbor.equals(b)) {
					list.remove(curr.data);
					break;
				}
				curr = curr.next;
			}
		}
	}

	// Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key : adj.keySet()) {
			System.out.print(key.toString() + " : ");
			SingleList<Edge<T>> thelist = adj.get(key);
			Node<Edge<T>> curr = thelist.head;
			while (curr != null) {
				System.out.print(curr.data);
				curr = curr.next;
			}
			System.out.println();
		}
	}

	//DFS
	public void dfsTraversal(T src) {
		if (!adj.containsKey(src)) {
			return;
		}

		Set<T> visited = new HashSet<>();
		dfsHelper(src, visited);
		System.out.println();
	}

	private void dfsHelper(T node, Set<T> visited) {
		visited.add(node);
		System.out.print(node + " ");

		SingleList<Edge<T>> edges = adj.get(node);
		Node<Edge<T>> curr = edges.head;

		while (curr != null) {
			T neighbor = curr.data.neighbor;
			if (!visited.contains(neighbor)) {
				dfsHelper(neighbor, visited);
			}
			curr = curr.next;
		}
	}

	//BFS
	public void bfsTraversal(T src) {
		if (!adj.containsKey(src)) {
			return;
		}

		SingleList<T> queue = new SingleList<>(); // Queue
		Set<T> visited = new HashSet<>();

		queue.pushQ(src);
		visited.add(src);

		while (!queue.isEmpty()) {
			T node = queue.remove();
			System.out.print(node + " "); // Visit the node

			SingleList<Edge<T>> edges = adj.get(node);
			Node<Edge<T>> curr = edges.head;

			while (curr != null) {
				T neighbor = curr.data.neighbor;
				if (!visited.contains(neighbor)) {
					queue.pushQ(neighbor);
					visited.add(neighbor);
				}
				curr = curr.next;
			}
		}
		System.out.println();
	}
}
