
/*
ID: 001207j1
LANG: JAVA
TASK: nocows
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class nocows {

	public static void main(String[] args) throws IOException {
		nocows n = new nocows();
		n.run();

	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		String s = f.readLine();
		int N = Integer.parseInt(s.split(" ")[0]);
		int K = Integer.parseInt(s.split(" ")[1]);
		int[][] dp = new int[N + 1][K + 1];
		for (int j = 1; j <= K; j++) {
			dp[0][j] = 1;
			dp[1][j] = 1;
		}
		for (int i = 1; i <= N; i += 2)
			for (int j = 2; j <= K; j++) {
				for (int k = 1; k <= i - 1; k += 2)
					dp[i][j] = (dp[i][j] + dp[k][j - 1] * dp[i - 1 - k][j - 1]) % 9901;

			}
		out.println((dp[N][K] - dp[N][K - 1] + 9901) % 9901);
		f.close();
		out.close();
		// System.out.println(System.currentTimeMillis() - start_Time);
	}
}
