import java.util.*;

class Edge<T> {
	T neighbor; // vertex yang terhubung
	double weight; // bobot edge

	// Konstruktor, Time O(1) Space O(1)
	public Edge(T v, double w) {
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
	private Map<T, SingleList<Edge<T>>> adj; // adjacency list
	boolean directed; // apakah graf berarah

	// Konstruktor, Time O(1) Space O(1)
	public WeightedGraph(boolean type) {
		adj = new HashMap<>();
		directed = type; // false: tidak berarah, true: berarah
	}

	// Menambahkan edge termasuk menambahkan node, Time O(1) Space O(1)
	public void addEdge(T a, T b, double w) {
		adj.putIfAbsent(a, new SingleList<>()); // menambahkan node a jika belum ada
		adj.putIfAbsent(b, new SingleList<>()); // menambahkan node b jika belum ada
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1); // menambahkan edge dari a ke b
		if (!directed) { // jika graf tidak berarah
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2); // menambahkan edge dari b ke a
		}
	}

	/*
	 * Jalur Terpendek dari vertex start ke vertex end
	 * menggunakan Algoritma Dijkstra
	 * ==========================================================
	 */
	public void dijkstra(T start, T end) {
		// Map untuk menyimpan jarak terpendek dari start ke setiap node
		Map<T, Double> res = new HashMap<>();
		// PriorityQueue untuk memproses node berdasarkan jarak terpendek yang diketahui
		PriorityQueue<Map.Entry<T, Double>> pq = new PriorityQueue<>((a, b) -> Double.compare(a.getValue(), b.getValue()));
		// Map untuk melacak node sebelumnya dalam jalur terpendek
		Map<T, T> prev = new HashMap<>();

		// Inisialisasi semua jarak dengan nilai maksimum (tak terhingga)
		for (T key : adj.keySet())
			res.put(key, Double.MAX_VALUE);

		// Memasukkan node awal ke dalam priority queue dengan jarak 0
		pq.offer(new AbstractMap.SimpleEntry(start, 0.0));
		res.put(start, 0.0);

		boolean found = false; // Penanda untuk mengecek apakah node tujuan tercapai

		// Loop sampai priority queue kosong
		while (!pq.isEmpty()) {
			// Mengambil node dengan jarak terpendek dari priority queue
			T u = pq.poll().getKey();

			// Jika node tujuan tercapai, berhenti
			if (u.equals(end)) {
				found = true;
				break;
			}

			// Mengambil daftar tetangga dari node saat ini
			SingleList<Edge<T>> thelist = adj.get(u);
			Node<Edge<T>> curr = thelist.head;

			// Iterasi melalui semua tetangga
			while (curr != null) {
				T v = curr.data.neighbor; // Tetangga dari node saat ini
				double weight = curr.data.weight; // Bobot dari edge ke tetangga

				// Jika jalur baru ke tetangga lebih pendek, perbarui jaraknya
				if (res.get(v) > res.get(u) + weight) {
					res.put(v, res.get(u) + weight);
					pq.offer(new AbstractMap.SimpleEntry(v, res.get(v))); // Tambahkan tetangga ke priority queue
					prev.put(v, u); // Catat node sebelumnya untuk rekonstruksi jalur
				}
				curr = curr.next; // Lanjutkan ke tetangga berikutnya
			}
		}

		// Cetak jalur terpendek dan jarak totalnya
		printPath(prev, start, end, res, found);
	}

	// Metode untuk mencetak jalur terpendek
	private void printPath(Map<T, T> prev, T start, T target, Map<T, Double> res, boolean found) {
		if (!found || res.get(target) == Double.MAX_VALUE) {
			// Jika node tujuan tidak ditemukan atau jaraknya tetap tak terhingga
			System.out.println("Rute tidak ditemukan atau jarak infinity.");
			return;
		}

		Stack<T> path = new Stack<>(); // Stack untuk membangun kembali jalur dari tujuan ke awal
		for (T at = target; at != null; at = prev.get(at)) {
			path.push(at); // Tambahkan node ke stack
		}

		// Cetak jalur terpendek
		System.out.print("Shortest path: ");
		while (!path.isEmpty()) {
			System.out.print(path.pop()); // Pop node dari stack dan cetak
			if (!path.isEmpty()) {
				System.out.print(" -> "); // Tambahkan tanda panah jika bukan node terakhir
			}
		}
		System.out.println();
		// Cetak jarak total dari start ke target
		System.out.println("Dengan jarak " + String.format("%.2f", res.get(target)));
	}
}
