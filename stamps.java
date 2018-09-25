
/*
ID: 001207j1
LANG: JAVA
TASK: stamps
*/
import java.io.*;
import java.util.*;

public class stamps {
	public static void main(String[] args) throws IOException {
		stamps s = new stamps();
		s.run();
	}

	public static void run() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		String s[] = f.readLine().split(" ");
		int K = Integer.parseInt(s[0]);
		int N = Integer.parseInt(s[1]);
		int fac[] = new int[N];
		String br;
		int x = 0;
		while ((br = f.readLine()) != null) {
			s = br.split(" ");
			for (int i = 0; i < s.length; i++) {
				fac[x] = Integer.parseInt(s[i]);
				x++;
			}
		}
		Arrays.sort(fac);
		int dp[] = new int[K * fac[fac.length - 1] + 1];
		int v = -1;
		for (int i = 1; i <= K * fac[fac.length - 1]; i++) {
			int m = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++)
				if (i - fac[j] >= 0)
					m = Math.min(dp[i - fac[j]], m);
			dp[i] = m + 1;
			if (dp[i] > K) {
				v = i - 1;
				break;
			} else
				v = i;
		}
		out.println(v);
		f.close();
		out.close();
	}

}