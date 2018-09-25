
/*
   ID: 001207j1
   LANG: JAVA
   TASK: ttwo
 */
import java.io.*;
import java.util.*;

public class ttwo {
	public static char[][] c = new char[10][10];

	public static int min = 0;
	// n, e, s, w

	public static void main(String[] args) throws IOException {
		ttwo t = new ttwo();
		t.run();

	}

	public static void run() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		boolean[] dirF = { true, false, false, false };
		boolean[] dirC = { true, false, false, false };
		Integer fx = 0, fy = 0, cx = 0, cy = 0;
		for (int i = 0; i < 10; i++) {
			String t = f.readLine();
			for (int j = 0; j < 10; j++) {
				c[i][j] = t.charAt(j);
				if (c[i][j] == 'F') {
					fx = i;
					fy = j;
				} else if (c[i][j] == 'C') {
					cx = i;
					cy = j;
				}
			}

		}
		int[][] markf = new int[100][4];
		int[][] markc = new int[100][4];
		boolean flag = false;
		while (!(markf[fx * 10 + fy][find(dirF)] == 2) || !(markc[cx * 10 + cy][find(dirC)] == 2)) {
			if (fx * 10 + fy == cx * 10 + cy) {
				out.println(min);
				flag = true;
				break;
			}
			boolean cgf = false, cgc = false;
			while (!cgf && !cgc) {
				markf[fx * 10 + fy][find(dirF)]++;
				markc[cx * 10 + cy][find(dirC)]++;
				if (markf[fx * 10 + fy][find(dirF)]==2 && markc[cx * 10 + cy][find(dirC)]==2)
					break;
				int[] nf = move(fx, fy, dirF);
				cgf = !(fx == nf[0] && fy == nf[1]);
				fx = nf[0];
				fy = nf[1];
				int[] nc = move(cx, cy, dirC);
				cgc = !(cx == nc[0] && cy == nc[1]);
				cx = nc[0];
				cy = nc[1];
				min++;
			}
		}
		// System.out.println(min);
		if (!flag)
			out.println(0);
		f.close();
		out.close();
	}

	public static int[] move(int x, int y, boolean[] d) {
		for (int i = 0; i < 4; i++)
			if (d[i]) {
				int[] n = nxt(i, x, y);
				int nx = n[0];
				int ny = n[1];
				if (nx >= 0 && nx < 10 && ny >= 0 && ny < 10 && c[nx][ny] != '*')
					return new int[] { nx, ny };
				else {
					d[i] = false;
					d[(i + 1) % 4] = true;
					return new int[] { x, y };
				}
			}
		return new int[] { x, y };
	}

	public static int[] nxt(int i, Integer x, Integer y) {
		int nx = x, ny = y;
		switch (i) {
		case 0:
			nx--;
			break;
		case 1:
			ny++;
			break;
		case 2:
			nx++;
			break;
		case 3:
			ny--;
			break;
		}
		return new int[] { nx, ny };
	}

	public static int find(boolean[] b) {
		for (int i = 0; i < 4; i++)
			if (b[i])
				return i;
		return -1;
	}
}