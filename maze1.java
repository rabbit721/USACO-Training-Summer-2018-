
/*
   ID: 001207j1
   LANG: JAVA
   TASK: maze1
 */
import java.io.*;
import java.util.*;

public class maze1 {
	public static int h, w;
	public static boolean[][] m;
	public static int[] d;
	public static ArrayList<Integer> a = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		maze1 m = new maze1();
		m.run();

	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		String[] temp = f.readLine().split(" ");
		w = Integer.parseInt(temp[0]);
		h = Integer.parseInt(temp[1]);
		m = new boolean[w * h][w * h];
		d = new int[w * h];
		Arrays.fill(d, 38000);
		char[][] c = new char[2 * h + 2][2 * w + 2];
		for (int i = 1; i <= 2 * h + 1; i++) {
			String t = f.readLine();
			for (int x = 2 * w + 1 - t.length(); x > 0; x--)
				t += " ";
			for (int j = 1; j <= t.length(); j++) {
				c[i][j] = t.charAt(j - 1);
				find_ex(i, j, t.charAt(j - 1));
			}
		}
		matrix(c);
		for (int s : a)
			BFS(s, new boolean[w * h]);
		int max = 0;
		for (int x : d)
			if (x > max) 
				max = x;
		out.println(max);
		//System.out.println(System.currentTimeMillis() - start_Time);
		f.close();
		out.close();
	}

	public static void BFS(int s, boolean[] mark) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(s);
		mark[s] = true;
		d[s] = 1;
		while (!q.isEmpty()) {
			int u = q.peek();
			mark[u] = true;
			for (int j = 0; j < w * h; j++) {
				if (m[u][j] && !mark[j]) {
					if (d[j] > d[u] + 1) {
						q.add(j);
						d[j] = d[u] + 1;
					}
				}
			}
			q.remove();
		}
	}

	public static void matrix(char[][] c) {
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w - 1; j++)
				if (c[(i + 1) * 2][(j + 1) * 2 + 1] == ' ') {
					m[i * w + j][i * w + j + 1] = true;
					m[i * w + j + 1][i * w + j] = true;
				}
		for (int j = 0; j < w; j++)
			for (int i = 0; i < h - 1; i++)
				if (c[(i + 1) * 2 + 1][(j + 1) * 2] == ' ') {
					m[i * w + j][(i + 1) * w + j] = true;
					m[(i + 1) * w + j][i * w + j] = true;
				}
	}

	public static void find_ex(int i, int j, char c) {
		if (i == 1 && c == ' ')
			a.add(j / 2 - 1);
		else if (i == 2 * h + 1 && c == ' ')
			a.add((h - 1) * w + j / 2 - 1);
		else if (j == 1 && c == ' ')
			a.add((i / 2 - 1) * w);
		else if (j == 2 * w + 1 && c == ' ')
			a.add((i / 2 - 1) * w + w - 1);
	}

}