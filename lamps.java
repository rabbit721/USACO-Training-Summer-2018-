
/*
ID: 001207j1
LANG: JAVA
TASK: lamps
*/
import java.io.*;
import java.util.*;

public class lamps {
	public static int[] rec;
	public static int c = 0;
	public static int fin_c;
	public static ArrayList<String> a = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		lamps l = new lamps();
		l.run();
	}

	public static void run() throws Exception {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int N = Integer.parseInt(f.readLine());
		fin_c = Integer.parseInt(f.readLine());
		rec = new int[N];
		String[] s = f.readLine().split(" ");
		Arrays.fill(rec, -1);
		fill(rec, 1, s);
		s = f.readLine().split(" ");
		fill(rec, 0, s);

		int[] org = new int[N];
		boolean[] o = new boolean[4];
		Arrays.fill(org, 1);
		dfs(fin_c, 0, org, o, out);
		if (c == 0)
			out.println("IMPOSSIBLE");
		Collections.sort(a);
		for (String x : a)
			out.println(x);
		f.close();
		out.close();
		//System.out.println(System.currentTimeMillis() - start_Time);
	}

	public static void fill(int[] rec, int v, String[] s) {
		for (int i = 0; i < s.length - 1; i++) {
			int x = Integer.parseInt(s[i]);
			rec[x - 1] = v;
			if (x % 2 != 0)
				if (x % 3 != 1)
					odd_but_four(rec, v);
				else
					odd_and_four(rec, v);

			else if (x % 2 == 0)
				if (x % 3 != 1)
					even_but_four(rec, v);
				else
					even_and_four(rec, v);
		}
	}

	public static boolean odd_but_four(int[] org, int v) {
		for (int j = 0; j < rec.length; j += 2)
			if ((j + 1) % 3 != 1) {
				if (rec[j] != -1 && rec[j] != v)
					return false;
				org[j] = v;
			}
		return true;
	}

	public static boolean odd_and_four(int[] org, int v) {
		for (int j = 0; j < rec.length; j += 2)
			if ((j + 1) % 3 == 1) {
				if (rec[j] != -1 && rec[j] != v)
					return false;
				org[j] = v;
			}
		return true;
	}

	public static boolean even_but_four(int[] org, int v) {
		for (int j = 1; j < rec.length; j += 2)
			if ((j + 1) % 3 != 1) {
				if (rec[j] != -1 && rec[j] != v)
					return false;
				org[j] = v;				
			}
		return true;
	}

	public static boolean even_and_four(int[] org, int v) {
		for (int j = 1; j < rec.length; j += 2)
			if ((j + 1) % 3 == 1) {
				if (rec[j] != -1 && rec[j] != v)
					return false;
				org[j] = v;
			}
		return true;
	}

	public static void dfs(int ct, int x, int[] org, boolean[] o, PrintWriter out) throws Exception {
		if (ct == 0 || x > 3) {
			if ((fin_c - ct) % 2 == fin_c % 2)
				conver(org, o, out);
			return;

		}
		for (int r = 0; r < 2; r++) {
			boolean[] o1 = o.clone();
			if (r == 0) {
				o1[x] = !o1[x];
				dfs(ct - 1, x + 1, org, o1, out);
			} else
				dfs(ct, x + 1, org, o1, out);
		}

	}

	public static void conver(int[] org, boolean[] o, PrintWriter out) throws Exception {
		int[] org1 = org.clone();
		if (o[0] == !o[1]) {// odd change
			if (!odd_but_four(org1, 0))
				return;
			if (!o[3])
				if (!odd_and_four(org1, 0))
					return;
		} else if (o[3]) {
			if (!odd_and_four(org1, 0))
				return;
		}
		if (o[0] == !o[2]) {// even change
			if (!even_but_four(org1, 0))
				return;
			if (!o[3])
				if (!even_and_four(org1, 0))
					return;
		} else if (o[3]) {
			if (!even_and_four(org1, 0))
				return;
		}
		for (int j = 0; j < rec.length; j++)
			if (rec[j] != -1 && rec[j] != org1[j])
				return;
		c++;
		String t = "";
		for (int x : org1)
			t += x;
		if (a.contains(t))
			return;
		else
			a.add(t);
	}
}
