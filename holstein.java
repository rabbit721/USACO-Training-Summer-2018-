
/*
ID: 001207j1
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

public class holstein {
	public static int min_s = 100000000;
	public static Queue<Integer> queue = new ArrayDeque<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		int V = Integer.parseInt(f.readLine());
		int[] req = new int[V];
		String[] t = f.readLine().split(" ");
		for (int i = 0; i < V; i++)
			req[i] = Integer.parseInt(t[i]);
		int G = Integer.parseInt(f.readLine());
		int[][] sc = new int[G][V];
		for (int i = 0; i < G; i++) {
			t = f.readLine().split("\\s");
			for (int j = 0; j < V; j++) {
				sc[i][j] = Integer.parseInt(t[j]);
			}
		}
		dfs(0, sc, req, 0, queue);

		out.print(min_s);
		for (int i : queue)
			out.print(" " + i);
		out.println();
		f.close();
		out.close();
	}

	public static void dfs(int i, int[][] sc, int[] cur, int count, Queue<Integer> q) {
		boolean e = true;
		for (int x : cur)
			if (x > 0)
				e = false;
		if (e || e && i == sc.length) {
			if (queue.isEmpty() || min_s > count || min_s == count && queue.peek() > q.peek())
				queue = q;
			min_s = min_s > count ? count : min_s;
			return;
		} else if (i == sc.length)
			return;
		for (int x = 1; x >= 0; x--) {
			int count1 = count;
			Queue<Integer> q1 = new ArrayDeque<Integer>(q);
			int[] c = cur.clone();
			add(x, sc[i], c);
			count1 += x;
			if (x != 0)
				q1.add(i + 1);
			dfs(i + 1, sc, c, count1, q1);
		}

	}

	public static void add(int n, int[] v, int[] c) {
		for (int i = 0; i < v.length; i++)
			c[i] -= n * v[i];

	}

}