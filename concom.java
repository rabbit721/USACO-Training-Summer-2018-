
/*
ID: 001207j1
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.*;

public class concom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		concom c = new concom();
		c.run();
	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		int n = Integer.parseInt(f.readLine());
		int[][] st = new int[101][101];
		boolean[][] c = new boolean[101][101];
		Set<Integer> a = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			String[] s = f.readLine().split(" ");
			int x = Integer.parseInt(s[0]), y = Integer.parseInt(s[1]), v = Integer.parseInt(s[2]);
			a.add(x);
			a.add(y);
			st[x][y] = v;
		}

		for (int s : a) {
			int[] ct = new int[101];
			boolean[] flag= new boolean[101];
			DFS(st, s, ct, flag, a);
			for (int j : a)
				if (ct[j] > 50)
					out.println(s + " " + j);
		}

		f.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start_Time);
	}

	public static void DFS(int[][] st, int s, int[] ct, boolean[] flag, Set<Integer> a) {
		if(flag[s])
			return;
		flag[s] = true;
		for (int j : a) {
			ct[j] += st[s][j];
			if (ct[j] > 50)
				DFS(st, j, ct, flag, a);
		}
	}
}
