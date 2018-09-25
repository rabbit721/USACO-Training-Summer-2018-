
/*
ID: 001207j1
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

public class sort3 {
	public static int[] n = { 0, 0, 0, 0 };
	public static int exc = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int N = Integer.parseInt(f.readLine());
		int[] v = new int[N];
		for (int i = 0; i < N; i++) {
			v[i] = Integer.parseInt(f.readLine());
			n[v[i]] += 1;
		}
		int[] cor = new int[N];
		for (int i = 0; i < N; i++)
			cor[i] = sec(i, n);

		sec_swap(N, n, v, cor);
		for (int i = 1; i <= 3; i++) {
			int t = -1;
			while (t != exc) {
				t = exc;
				num_swap(i, N, v, cor);
			}
		}
		out.println(exc);
		f.close();
		out.close();
	}

	public static void swap(int i, int j, int[] v) {
		int t = v[i];
		v[i] = v[j];
		v[j] = t;
		exc++;
	}

	public static void sec_swap(int N, int[] n, int[] v, int[] cor) {
		for (int i = 0; i < N; i++) {
			if (v[i] == cor[i])
				continue;
			for (int j = N - 1; j > i; j--) {
				if (v[j] == cor[j])
					continue;
				if (v[i] == sec(j, n) && v[j] == sec(i, n))
					swap(i, j, v);
				if (v[i] == cor[i])
					break;
			}
		}
	}

	public static int sec(int i, int[] n) {
		if (i < n[1])
			return 1;
		else if (i < n[2] + n[1])
			return 2;
		else
			return 3;
	}

	public static void num_swap(int x, int N, int[] v, int[] cor) {
		for (int i = 0; i < N; i++) {
			if (v[i] == cor[i])
				continue;
			for (int j = N - 1; j > i; j--) {
				if (v[j] == cor[j])
					continue;
				if (v[i] != x && v[j] == x)
					swap(i, j, v);
				if (v[i] == cor[i])
					break;
			}
		}
	}
}