
/*
ID: 001207j1
LANG: JAVA
TASK: inflate
*/
import java.io.*;
import java.util.*;

public class inflate {

	public static void main(String[] args) throws IOException {
		long star = System.currentTimeMillis();
		Scanner br = new Scanner(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		int M = br.nextInt();
		int N = br.nextInt();
		int[] cost = new int[M + 1];
		for (int i = 0; i < N; i++) {
			int a = br.nextInt();
			int b = br.nextInt();
			for (int j = b; j <= M; j++) {
				cost[j] = Math.max(cost[j], cost[j - b] + a);
			}
		}

		out.write(Integer.toString(cost[M]) + "\n");
		out.close();
	}
}