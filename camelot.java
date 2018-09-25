
/*
   ID: 001207j1
   LANG: JAVA
   TASK: camelot
 */

import java.io.*;
import java.util.*;

public class camelot {
	public static void main(String[] args) throws Exception {
		camelot main = new camelot();
		main.run();
	}

	public static void run() throws Exception {
		long st = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		String[] s = f.readLine().split(" ");
		int R = Integer.parseInt(s[0]), C = Integer.parseInt(s[1]);
		s = f.readLine().split(" ");
		Point king = new Point((int) s[0].charAt(0) - 65, Integer.parseInt(s[1]) - 1);
		ArrayList<Point> knights = new ArrayList<Point>();
		String br = f.readLine();
		int move1[][] = { { -1, -2 }, { -1, 2 }, { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { 1, -2 } };
		while (br != null) {
			s = br.split(" ");
			for (int i = 0; i < s.length; i += 2)
				knights.add(new Point((int) s[i].charAt(0) - 65, Integer.parseInt(s[i + 1]) - 1));
			br = f.readLine();
		}
		int[][] dst = new int[R * C][R * C];
		for (int i = 0; i < R * C; i++) {
			Arrays.fill(dst[i], 99999999);
			dst[i][i] = 0;
		}

		for (int i = 0; i < R * C; i++) {
			int ux = i % C;
			int uy = (int) i / C;
			for (int x = 0; x < move1.length; x++) {
				int vx = ux + move1[x][0], vy = uy + move1[x][1];
				if (vx >= 0 && vy >= 0 && vx < C && vy < R) {
					int j = vy * C + vx;
					dst[i][j] = dst[j][i] = 1;
				}
			}
		}
		int fin_min = 99999999;
		// System.out.println(System.currentTimeMillis()-st);
		floyd(dst, R, C);
		// System.out.println(System.currentTimeMillis()-st);
		for (int i = 0; i < R * C; i++) {
			int sum = 0;
			int min = 1061109567;
			for (Point k : knights)
				sum += dst[k.y * C + k.x][i];
			if (sum > fin_min)
				continue;
			for (Point k : knights) {
				int tmp = sum - dst[k.y * C + k.x][i];
				int min2 = 1061109567;
				for (int m = 0; m < R * C; m++) 
					min2 = Math.min(tmp + dst[k.y * C + k.x][m] + dst[m][i]
							+ Math.max(Math.abs(m % C - king.x), Math.abs(m / C - king.y)), min2);
				
				min = Math.min(min2, min);
			}
			fin_min = Math.min(min, fin_min);
		}

		f.close();
		if (knights.isEmpty())
			out.println(0);
		else
			out.println(fin_min);
		System.out.println(System.currentTimeMillis() - st);
		out.close();
	}

	public static void floyd(int[][] dst, int R, int C) {
		for (int k = 0; k < R * C; k++) {
			for (int i = 0; i < R * C; i++) {
				for (int j = 0; j < R * C; j++) {
					dst[i][j] = Math.min(dst[i][j], dst[i][k] + dst[k][j]);
				}
			}
		}
	}

}

class Point {
	public int x, y;

	Point(int a, int b) {
		x = a;
		y = b;
	}

	public String toString() {
		return x + " " + y;
	}

}
