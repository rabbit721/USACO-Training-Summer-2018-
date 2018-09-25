
/*
ID: 001207j1
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

public class frac1 {
	public static boolean[][] q = new boolean[161][161];
	public static Map<Double, String> map = new HashMap<Double, String>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int n = Integer.parseInt(f.readLine());
		for (int i = 1; i <= n; i++) {
			int[] num = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				num[j] = j;
			}
			v(num, i);
			/*for (int x : num)
				System.out.print(x + " ");
			System.out.println();*/

		}

		SortedSet<Double> keys = new TreeSet<>(map.keySet());
		for (double a : keys)
			out.println(map.get(a));
		
		f.close();
		out.close();

	}

	public static void v(int[] num, int i) {
		for (int a = 0; a <= i; a++) {
			for (int b = 0; b <= a; b++) {
				if (a == 0 && b == 0)
					continue;
				int d = GCD(num[a], num[b]);
				if (d == 1) {
					q[a][b] = true;
					map.put((double)b/a, (b+"/"+a));
				}
				else if (!q[a / d][b / d]) {
					q[a / d][b / d] = true;
					map.put((double)b/a, (b/d+"/"+a/d));
				}
				

			}
		}
	}

	public static int GCD(int a, int b) {
		return b == 0 ? a : GCD(b, a % b);
	}
}