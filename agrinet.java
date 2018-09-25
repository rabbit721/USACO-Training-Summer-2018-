
/*
ID: 001207j1
LANG: JAVA
TASK: agrinet
*/
import java.io.*;
import java.util.*;

public class agrinet {
	public static int dist[][];
	public static int opt = Integer.MAX_VALUE;
	public static int[] min_nxt = { Integer.MAX_VALUE, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		int N = Integer.parseInt(f.readLine());
		dist = new int[N][N];
		String br;
		int temp = 0;
		while ((br = f.readLine()) != null) {
			String[] s = br.split(" ");
			for (int i = 0; i < s.length; i++) {
				int u = temp % N;
				int v = temp / N;
				dist[u][v] = Integer.parseInt(s[i]);
				if (dist[u][v] == 0 && u != v)
					dist[u][v] = Integer.MAX_VALUE;
				temp++;
			}
		}
		for (int s = 0; s < N; s++)
			dfs(s, new boolean[N], N, 0);
		out.println(opt);
		f.close();
		out.close();
	}

	public static void dfs(int s, boolean intree[], int N, int sum) {
		intree[s] = true;
		int nxt = -1;
		int min = Integer.MAX_VALUE;
		for (int u = 0; u < N; u++) {
			int minu = Integer.MAX_VALUE;
			for (int k = 0; k < N; k++)
				if (intree[k] && dist[k][u] != Integer.MAX_VALUE)
					minu = Math.min(minu, dist[k][u]);

			if (u != s && !intree[u] && minu < min) {
				min = minu;
				nxt = u;
			}
		}
		if (nxt == -1) {
			opt = Math.min(opt, sum);
			return;
		}
		dfs(nxt, intree, N, sum + min);
	}
}