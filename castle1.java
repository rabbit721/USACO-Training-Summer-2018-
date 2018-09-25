
/*
   ID: 001207j1
   LANG: JAVA
   TASK: castle
 */
import java.io.*;
import java.util.*;

public class castle1 {
	public static int comp_num = 0;
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
		BFS(cas, r, c);
		out.println(comp_num);
		// System.out.println(map);

		int max = 0;
		for (int i = 1; i <= comp_num; i++)
			if (map.get(i) > max)
				max = map.get(i);
		out.println(max);

		int[] link_list = new int[comp_num + 1];
		int[][] walls = new int[comp_num + 1][2];
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++) {
				int x = i * c + j;
				for (int k = 0; k < 4; k++) {
					int y = dir(x, k, c);
					if (y % c == 0 && y == x + 1 || x % c == 0 && x == y + 1)
						continue;
					if (0 <= y && y < r * c && comp[y] != comp[x] && map.get(comp[y]) >= map.get(link_list[comp[x]])) {
						if(i==49) 
							System.out.print("");
						if (map.get(comp[y]) == map.get(link_list[comp[x]]))
							if (x % c > walls[comp[x]][0] % c)
								continue;
							else if (x % c == walls[comp[x]][0] % c)
								if ((int) x / c < (int) walls[comp[x]][0] / c)
									continue;
								else if ((int) x / c == (int) walls[comp[x]][0] / c)
									if (!(k == 1 && walls[comp[x]][1] == 2))
										continue;
						walls[comp[x]] = new int[] { x, k };
						link_list[comp[x]] = comp[y];
						
						

					}
				}
			}
		int num = 0;
		for (int i = 1; i <= comp_num; i++) {
			int size = map.get(link_list[i]) + map.get(i);
			if (size > max) {
				max = size;
				num = i;
			}else if(size == max) {
				if(walls[i][0]%c<walls[num][0]%c)
					num = i;
				else if(walls[i][0]%c==walls[num][0]%c)
					if((int)walls[i][0]/c>(int)walls[num][0]/c) {
						num = i;
					}else if((int)walls[i][0]/c==(int)walls[num][0]/c) {
						if(walls[i][1]==1 && walls[num][1] ==2)
							num = i;
					}
				
			}
		}
		num = prior(num, link_list, walls, c);

		out.println(max);
		int r_max = (int) walls[num][0] / c + 1;
		int c_max = walls[num][0] - (r_max - 1) * c + 1;
		out.println(r_max + " " + c_max + " " + string(walls[num][1]));
		f.close();
		out.close();
	}

	public static void BFS(int[][] cas, int r, int c) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] marked = new boolean[r * c];
		int ct = 0;
		for (int i = 0; i < r * c; i++) {
			if (marked[i])
				continue;
			else {
				map.put(comp_num, ct);
				comp_num += 1;
				ct = 0;
			}
			marked[i] = true;// source = i
			q.add(i);
			comp[i] = comp_num;
			ct++;
			while (!q.isEmpty()) {
				int x = q.remove();
				for (int j : adj(cas, x, c)) {
					if (marked[j])
						continue;
					marked[j] = true;
					comp[j] = comp_num;
					ct++;
					q.add(j);
				}

			}
		}
		map.put(comp_num, ct);
	}

	public static Set<Integer> adj(int[][] cas, int idx, int c) {
		Set<Integer> adj = new HashSet<Integer>();
		for (int i = 0; i < 4; i++) {
			if (cas[idx][i] == 1)
				continue;
			adj.add(dir(idx, i, c));
		}
		return adj;

	}

	public static String string(int k) {
		if (k == 0)
			return "W";
		else if (k == 1)
			return "N";
		else if (k == 2)
			return "E";
		else
			return "S";
	}

	public static int prior(int num, int[] link_list, int[][] walls, int c) {
		int y = link_list[num];
		if (walls[num][0] % c > walls[y][0] % c)
			return y;
		else if (walls[num][0] % c == walls[y][0] % c)
			if ((int) walls[num][0] / c < (int) walls[y][0] / c)
				return y;
			else if ((int) walls[num][0] / c == (int) walls[y][0] / c)
				if (walls[y][1] == 1 && walls[num][1] == 2)
					return y;

		return num;
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
