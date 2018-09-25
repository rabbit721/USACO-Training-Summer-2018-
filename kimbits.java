
/*
   ID: 001207j1
   LANG: JAVA
   TASK: kimbits
 */
import java.io.*;

public class kimbits {
	public static int N, L;
	public static long I;
	public static int[][] dp;

	public static void main(String[] args) throws Exception {
		kimbits k = new kimbits();
		k.run();
	}

	public static void run() throws Exception {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		String s[] = f.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		L = Integer.parseInt(s[1]);
		I = Long.parseLong(s[2]);

		dp = new int[N + 1][L + 1];
		for (int i = 0; i <= L; i++)
			dp[0][i] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= L; j++)
				if (j == 0)
					dp[i][j] = 1;
				else
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
		}
		print_bits(N, L, out, I - 1);
		out.println();
		f.close();
		out.close();

	}

	public static void print_bits(int len, int ones, PrintWriter out, long index) throws Exception {
		if (len == 0)
			return;
		if (dp[len - 1][ones] > index) {
			out.print('0');
			print_bits(len - 1, ones, out, index);
		} else {
			out.print('1');
			print_bits(len - 1, ones - 1, out, index - dp[len - 1][ones]);
		}
	}
}
