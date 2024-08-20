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

	// DFS
	public void DFS(T src) {
		if (!adj.containsKey(src)) // invalid input
			return;
		HashMap<T, Boolean> visited = new HashMap<>(); // Visited
		helper(src, visited);
		System.out.println();
	}

	private void helper(T v, HashMap<T, Boolean> visited) {
		visited.put(v, true); // true = sudah dikunjungi
		System.out.print(v.toString() + " ");
		SingleList<Edge<T>> thelist = adj.get(v);
		Node<Edge<T>> curr = thelist.head;
		while (curr != null) {
			T u = curr.data.neighbor;
			if (visited.get(u) == null)
				helper(u, visited);
			curr = curr.next;
		}
	}

	// BFS
	public void BFS(T src) {
		if (!adj.containsKey(src)) // invalid input
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
			while (curr != null) {
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
	 * Shortest Path dari vertex start ke vertex end
	 * menggunakan Algoritma Dijkstra
	 * ==========================================================
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void dijkstra(T start, T end) {
		Map<T, Integer> res = new HashMap<>();
		PriorityQueue<Map.Entry<T, Integer>> pq = new PriorityQueue<>((a, b) -> (int) (a.getValue() - b.getValue()));
		Map<T, T> prev = new HashMap<>();

		for (T key : adj.keySet())
			res.put(key, Integer.MAX_VALUE);
		pq.offer(new AbstractMap.SimpleEntry(start, 0));
		res.put(start, 0);

		while (!pq.isEmpty()) {
			T u = pq.poll().getKey();
			if (u.equals(end)) {
				break; // Stop if we reach the destination node
			}
			SingleList<Edge<T>> thelist = adj.get(u);
			Node<Edge<T>> curr = thelist.head;
			while (curr != null) {
				T v = curr.data.neighbor;
				int weight = curr.data.weight;
				if (res.get(v) > res.get(u) + weight) {
					res.put(v, res.get(u) + weight);
					pq.offer(new AbstractMap.SimpleEntry(v, res.get(v)));
					prev.put(v, u);
				}
				curr = curr.next;
			}
		}
		printPath(prev, start, end, res);
	}

	private void printPath(Map<T, T> prev, T start, T target, Map<T, Integer> res) {
		Stack<T> path = new Stack<>();
		for (T at = target; at != null; at = prev.get(at)) {
			path.push(at);
		}
		System.out.print("Shortest path: ");
		while (!path.isEmpty()) {
			System.out.print(path.pop());
			if (!path.isEmpty()) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
		System.out.println("Dengan jarak " + res.get(target));
	}
}