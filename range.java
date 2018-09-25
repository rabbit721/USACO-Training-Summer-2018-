
/*
   ID: 001207j1
   LANG: JAVA
   TASK: range
 */
import java.io.*;
import java.util.*;

public class range {
	public static void main(String[] args) throws Exception {
		range main = new range();
		main.run();
	}

	public static void run() throws Exception {
		long st = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		int N = Integer.parseInt(f.readLine());
		StringBuilder[] s = new StringBuilder[N];
		for (int i = 0; i < N; i++) {
			s[i] = new StringBuilder(f.readLine());
		}
		StringBuilder[] overlap = new StringBuilder[N - 1];
		StringBuilder b = new StringBuilder("1");
		for (int i = 0; i < N - 1; i++) {
			overlap[i] = join(s[i], s[i + 1]);
			b.append("1");
		}
		int[] rcd = new int[N + 1];
		for (int i = 0; i < N - 1; i++) {
			StringBuilder base = new StringBuilder(b);
			for (int j = i; j < N - 1; j++) {
				base = join(base, overlap[j]);
				rcd[j - i + 2] += count(j - i + 2, new StringBuilder(base));
			}
		}

		for (int i = 0; i < rcd.length; i++)
			if (rcd[i] != 0)
				out.println(i + " " + rcd[i]);
		f.close();
		out.close();
		//System.out.println(System.currentTimeMillis() - st);
	}

	public static StringBuilder join(StringBuilder a, StringBuilder b) {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i))
				t.append( a.charAt(i));
			else
				t.append('0');
		}
		return new StringBuilder(t);
	}

	public static int count(int l, StringBuilder n) {
		int ct = 0;
		n.append("0");
		int track = 0;
		for (int i = 0; i < n.length(); i++) {
			if (n.charAt(i) == '1')
				track++;
			else {
				if (track >= l)
					ct += track - l + 1;
				track = 0;
			}
		}
		return ct;
	}
}
