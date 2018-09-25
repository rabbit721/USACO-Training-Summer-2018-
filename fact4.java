
/*
   ID: 001207j1
   LANG: JAVA
   TASK: fact4
 */
import java.io.*;

public class fact4 {
	public static void main(String[] args) throws Exception {
		fact4 f = new fact4();
		f.run();
	}

	public static void run() throws Exception {
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		int N = Integer.parseInt(f.readLine());
		int rcd = 1;
		for (int i = 2; i <= N; i++) {
			rcd = mtp(rcd, i, Integer.toString(i).length());
		}
		rcd = mtp(rcd, 1, 1);
		out.println(rcd);
		f.close();
		out.close();

	}

	public static int mtp(int rcd, int i, int pos) {
		String result = Integer.toString(rcd * i);
		String rt = "";
		int ct = 0;
		for (int x = result.length() - 1; x >= 0; x--)
			if (result.charAt(x) != '0' && ct == 0 || ct < pos && ct != 0) {
				rt = result.charAt(x) + rt;
				ct++;
			}
		return Integer.parseInt(rt);
	}
}
