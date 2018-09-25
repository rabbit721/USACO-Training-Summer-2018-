
/*
ID: 001207j1
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

public class subset {
	public static int cnt = 0;
	public static int sum = 0;

	public static void main(String[] args) throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int N = Integer.parseInt(f.readLine());

		sum = (int) N * (N + 1) / 2;
		if (sum % 2 != 0)
			out.write("0\n");
		else {
			long[][] cost = new long[N][sum + 1];

			for (int i = 0; i < N; i++) {
				cost[i][i] = 1;
				for (int j = 0; j <= sum; j++) {
					if (i > 0) {
						cost[i][j] = cost[i - 1][j];
						if (j >= i)
							cost[i][j] += cost[i - 1][j - i];
					}
				}
			}
			out.write(Long.toString(cost[N - 1][sum / 2]) + "\n");
		}
		f.close();
		out.close();
		// System.out.println(System.currentTimeMillis() - start_Time);
	}
}
