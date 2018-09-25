
/*
   ID: 001207j1
   LANG: JAVA
   TASK: castle
 */
import java.io.*;
import java.util.*;

public class castle {
	public static int comp_num = 1;
	public static int[] comp;
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		// long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		String[] t = f.readLine().split(" ");

		int c = Integer.parseInt(t[0]);
		int r = Integer.parseInt(t[1]);
		int[][] cas = new int[r * c][4];
		comp = new int[r * c];
		Arrays.fill(comp, -2);
		for (int i = 0; i < r; i++) {
			t = f.readLine().split(" ");
			for (int j = 0; j < c; j++) {
				String s = Integer.toBinaryString(Integer.parseInt(t[j]));
				for (int k = 0; k < s.length(); k++) {
					cas[i * c + j][k] = Integer.parseInt(s.charAt(s.length() - k - 1) + "");
					// 1 w;2 n;4 e;8 s;
				}
			}
		}
		flood_fill(cas, r, c);
		/*
		 * for (int i = 0; i < r; i++) { for (int j = 0; j < c; j++) {
		 * System.out.print(comp[i * c + j] + " "); } System.out.println(); }
		 */
		out.println(comp_num);

		int max = 0;
		for (int i = 1; i <= comp_num; i++)
			if (map.get(i) > max)
				max = map.get(i);
		out.println(max);

		int wl = 0;
		char d = 'N';
		for (int j = 0; j < c; j++)
			for (int i = r - 1; i >= 0; i--) {
				if (i > 0 && comp[i * c + j] != comp[(i - 1) * c + j]) {
					int n = map.get(comp[i * c + j]) + map.get(comp[(i - 1) * c + j]);
					if (n > max) {
						max = n;
						wl = i * c + j;
						d = 'N';
					}
				}
				if (j < c - 1 && comp[i * c + j] != comp[i * c + j + 1]) {
					int n = map.get(comp[i * c + j]) + map.get(comp[i * c + j + 1]);
					if (n > max) {
						max = n;
						wl = i * c + j;
						d = 'E';
					}
				}

			}
		out.println(max);
		int r_max = (int)wl/c + 1;
		int c_max = wl- (r_max - 1) * c + 1;
		out.println(r_max + " " + c_max + " " + d);
		f.close();
		out.close();
	}

	public static int dfs(int[][] cas, int s, int c, int cnt) {
		comp[s] = comp_num;
		cnt++;
		for (int k = 0; k < 4; k++)
			if (cas[s][k] == 0) {
				int w = dir(s, k, c);
				if (comp[w] != -2)
					continue;
				comp[w] = comp_num;
				cnt = dfs(cas, w, c, cnt);
			}
		return cnt;
	}

	public static void flood_fill(int[][] cas, int r, int c) {
		for (int i = 0; i < r * c; i++) {
			int cnt = 0;
			if (comp[i] != -2)
				continue;
			cnt = dfs(cas, i, c, cnt);
			map.put(comp_num, cnt);
			comp_num++;
		}
		comp_num--;
	}

	public static int dir(int idx, int k, int c) {
		if (k == 0)
			return idx - 1;
		else if (k == 1)
			return idx - c;
		else if (k == 2)
			return idx + 1;
		else
			return idx + c;
	}

}
