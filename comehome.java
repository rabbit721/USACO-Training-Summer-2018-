
/*
   ID: 001207j1
   LANG: JAVA
   TASK: comehome
 */
import java.io.*;
import java.util.*;

public class comehome {
	public static void main(String[] args) throws IOException {
		comehome c = new comehome();
		c.run();

	}

	public static void run() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		int n = Integer.parseInt(f.readLine());
		int[][] dist = new int[52][52];
		for (int i = 0; i < 52; i++)
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		for (int i = 1; i <= n; i++) {
			String s[] = f.readLine().split(" ");
			int u = (int) (s[0].charAt(0));
			int v = (int) (s[1].charAt(0));
			u -= u > 96 ? 71 : 65;
			v -= v > 96 ? 71 : 65;
			dist[u][v] = dist[v][u] = Math.min(Integer.parseInt(s[2]), dist[v][u]);
			if (u == v)
				dist[u][v] = 0;
		}
		for (int k = 0; k < 52; k++)
			for (int i = 0; i < 52; i++)
				for (int j = i + 1; j < 52; j++)
					if (dist[i][k] != Integer.MAX_VALUE && dist[j][k] != Integer.MAX_VALUE)
						if (dist[i][k] + dist[k][j] < dist[i][j])
							dist[j][i] = dist[i][j] = dist[i][k] + dist[k][j];

		int min = Integer.MAX_VALUE;
		int cow = 0;
		for (int i = 0; i < 25; i++)
			if (min > dist[25][i]) {
				min = dist[25][i];
				cow = i;
			}

		out.println((char) (cow + 65) + " " + dist[cow][25]);
		f.close();
		out.close();
	}

}