
/*
ID: 001207j1
LANG: JAVA
TASK: zerosum
*/

import java.io.*;

public class zerosum {

	public static int rcd = 0;
	public static String[] symbol = { " ", "+", "-" };

	public static void main(String[] args) throws IOException {
		zerosum z = new zerosum();
		z.run();
	}

	public static void run() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		int n = Integer.parseInt(f.readLine());
		comb(out, 2, n, "");
		f.close();
		out.close();
	}

	public static void comb(PrintWriter out, int i, int n, String t) {
		if (i > n) {

			if (check(t, n))
				out.println(p(t, n));
			return;
		}
		for (int j = 0; j < 3; j++) {
			String t1 = t;
			t1 += symbol[j];
			comb(out, i + 1, n, t1);
		}
	}

	public static boolean check(String t, int n) {
		int[] rcd = new int[n + 1];
		rcd[1] = 1;
		int temp = -1;
		for (int i = 2; i <= n; i++) {// t.l = n-1;
			if (temp != -1 && t.charAt(i - 2) != ' ') {
				int l = ("" + temp).length();
				if (i > l + 1 && t.charAt(i - l - 2) == '-')
					rcd[i - 1] = rcd[i - l - 1] - temp;
				else
					rcd[i - 1] = rcd[i - l - 1] + temp;
				temp = -1;
			}
			if (t.charAt(i - 2) == ' ') {
				if (temp != -1) {
					temp = temp * 10 + i;
				} else {
					temp = (i - 1) * 10 + i;
				}

			} else if (t.charAt(i - 2) == '+') {
				temp = -1;
				rcd[i] = rcd[i - 1] + i;
			} else if (t.charAt(i - 2) == '-') {
				temp = -1;
				rcd[i] = rcd[i - 1] - i;
			}
		}
		if (temp != -1) {
			int l = ("" + temp).length();
			if (n >= l + 1 && t.charAt(n - l - 1) == '-')
				rcd[n] = rcd[n - l] - temp;
			else
				rcd[n] = rcd[n - l] + temp;
			temp = -1;
		}
		System.out.println(rcd[n]);
		if (rcd[n] == 0)
			return true;
		return false;
	}

	public static String p(String t, int n) {
		String fin = "1";
		for (int i = 2; i <= n; i++) {
			fin += t.charAt(i - 2) + "" + i;
		}
		return fin;
	}
}
