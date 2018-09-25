
/*
ID: 001207j1
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;

public class money {

	public static void main(String[] args) throws IOException {
		money m = new money();
		m.run();

	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		String[] s = f.readLine().split(" ");
		int V = Integer.parseInt(s[0]);
		int N = Integer.parseInt(s[1]);
		Set<Integer> a = new HashSet<Integer>();
		String str;
		while ((str = f.readLine()) != null) {
			s = str.split(" ");
			for (int i = 0; i < s.length; i++)
				a.add(Integer.parseInt(s[i]));
		}
		V = a.size();
		Integer nums[] = a.toArray(new Integer[V]);
		long[][] sum = new long[V + 1][N + 1];
		for (int i = 0; i <= V; i++)
			sum[i][0] = 1;
		for (int i = 1; i <= V; i++)
			for (int j = 1; j <= N; j++) {
				if (i == 1 && j % nums[i - 1] != 0)
					continue;
				int x = j;
				while (x >= 0) {
					sum[i][j] += sum[i - 1][x];
					x -= nums[i - 1];
				}
			}
		out.println(sum[V][N]);
		f.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start_Time);
	}
}
