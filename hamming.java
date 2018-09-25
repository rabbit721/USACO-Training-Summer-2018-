
/*
ID: 001207j1
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

public class hamming {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

		String[] s = f.readLine().split(" ");
		int[] q = new int[(int) Math.pow(2, 8)];
		Arrays.fill(q, -2);
		int l = 0;
		int N = Integer.parseInt(s[0]), B = Integer.parseInt(s[1]), D = Integer.parseInt(s[2]);
		int max = (int) Math.pow(2, B) - 1;
		// for (int i = 0; i <= max-N; i++)

		for (int i = 0; i <= max; i++) {
			if (l == N)
				break;
			boolean gd = true;
			for (int j = 0; j < l; j++) {
				if (d(i, q[j]) < D)
					gd = false;
			}
			if (gd)
				q[l++] = i;
		}
		for (int i = 0; i < q.length; i++) {
			if (q[i] == -2)
				break;
			if (q[i + 1] == -2)
				out.print(q[i]);
			else if ((i + 1) % 10 == 0)
				out.println(q[i]);
			else
				out.print(q[i] + " ");

		}
		out.println();
		f.close();
		out.close();
	}

	public static int d(int i, int j) {// distance
		int d = 0;
		String s1 = (Integer.toBinaryString(i)), s2 = Integer.toBinaryString(j);
		String m, n;
		if (Math.max(s1.length(), s2.length()) == s1.length()) {
			m = s1;
			n = s2;
		} else {
			m = s2;
			n = s1;
		}
		for (; n.length() < m.length();)
			n = "0" + n;
		for (int x = 0; x < m.length(); x++)
			if (m.charAt(x) != n.charAt(x))
				d++;
		return d;
	}
}
