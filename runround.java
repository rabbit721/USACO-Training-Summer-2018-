
/*
ID: 001207j1
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;

public class runround {

	public static void main(String[] args) throws IOException {
		runround m = new runround();
		m.run();
	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int n = Integer.parseInt(f.readLine());
		for (n++; !isRunRound(n); n++)
			;
		out.println(n);
		f.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start_Time);
	}

	public static boolean isRunRound(int n) {
		String t = Integer.toString(n);
		if (!check(t))
			return false;
		boolean[] mark = new boolean[t.length()];
		int i = 0;
		for (int ct = 0; ct < t.length(); ct++) {
			i = (i + t.charAt(i) - '0') % t.length();
			if (!mark[i]) {
				mark[i] = true;
			} else
				return false;
		}
		return true;
	}

	public static boolean check(String t) {
		boolean[] check = new boolean[10];
		for (int i = 0; i < t.length(); i++) {
			if (check[t.charAt(i) - '0'])
				return false;
			else
				check[t.charAt(i) - '0'] = true;
		}
		return true;
	}
}
