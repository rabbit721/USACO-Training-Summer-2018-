
/*
   ID: 001207j1
   LANG: JAVA
   TASK: butter
 */
import java.io.*;
import java.util.*;

public class butter {
	public static int N, P, C;
	public static int[][] dist;
	public static int[] cows;
	public static ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception {
		butter b = new butter();
		b.run();
	}

	public static void run() throws Exception {
		long st = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		String[] s = f.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		P = Integer.parseInt(s[1]);
		C = Integer.parseInt(s[2]);
		dist = new int[P + 1][P + 1];
		a.add(new ArrayList<Integer>());
		for (int i = 1; i <= P; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			a.add(new ArrayList<Integer>());
			dist[i][i] = 0;
		}
		cows = new int[N];

		for (int i = 0; i < N; i++)
			cows[i] = Integer.parseInt(f.readLine());
		for (int i = 0; i < C; i++) {
			s = f.readLine().split(" ");
			int u = Integer.parseInt(s[0]), v = Integer.parseInt(s[1]);
			a.get(u).add(v);
			a.get(v).add(u);
			dist[u][v] = dist[v][u] = Integer.parseInt(s[2]);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= P; i++) {
			min = Math.min(min, Dijkstra(i));
		}
		out.println(min);
		f.close();
		out.close();
	}

	public static int Dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		Node[] list = new Node[P + 1];
		for (int i = 0; i <= P; i++)
			list[i] = new Node(i);
		pq.add(list[s]);
		list[s].dist = 0;
		list[0].dist = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Node u = pq.poll();
			if (!u.visited) {
				u.visited = true;
				for (int x : a.get(u.ID))
					if (!list[x].visited && list[x].dist > dist[u.ID][x] + list[u.ID].dist) {
						dist[x][s] = dist[s][x] = list[x].dist = dist[u.ID][x] + dist[u.ID][s];
						pq.add(list[x]);
					}

			}
		}
		int total = 0;
		for (int i = 0; i < N; i++)
			total += list[cows[i]].dist;
		return total;
	}
}

class Node implements Comparable<Node> {
	public boolean visited;
	public int ID;
	public int dist;

	public Node(int id) {
		this.ID = id;
		this.dist = Integer.MAX_VALUE;
		this.visited = false;
	}

	public int compareTo(Node a) {
		if (this.dist == a.dist)
			return this.ID - a.ID;
		else
			return this.dist - a.dist;
	}
}
