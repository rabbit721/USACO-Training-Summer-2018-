
/*
   ID: 001207j1
   LANG: JAVA
   TASK: cowtour
 */
import java.io.*;
import java.util.*;
import java.math.BigDecimal;

public class cowtour {
	public static int comp_num = 0;
	public static int[] comp;
	public static boolean[][] cnn;
	public static double min = Double.MAX_VALUE;
	public static double[][] dist;

	public static void main(String[] args) throws IOException {
		cowtour c = new cowtour();
		c.run();

	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		int n = Integer.parseInt(f.readLine());
		int[][] cord = new int[n][2];
		cnn = new boolean[n][n];
		dist = new double[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(dist[i], Double.MAX_VALUE);
		comp = new int[n];
		for (int i = 0; i < n; i++) {
			String[] t = f.readLine().split(" ");
			cord[i][0] = Integer.parseInt(t[0]);
			cord[i][1] = Integer.parseInt(t[1]);
		}
		for (int i = 0; i < n; i++) {
			String t = f.readLine();
			for (int j = 0; j < n; j++) {
				cnn[i][j] = t.charAt(j) == '1' ? true : false;
				cnn[j][i] = cnn[i][j];
				if (cnn[i][j]) {
					int dx = cord[i][0] - cord[j][0];
					int dy = cord[i][1] - cord[j][1];
					dist[i][j] = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
					dist[j][i] = dist[i][j];
				}
				if (i == j)
					dist[i][j] = dist[j][i] = 0;
			}
		}
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						dist[j][i] = dist[i][j];
					}

		double[] maxd = new double[n];
		double max1 = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (dist[i][j] != Double.MAX_VALUE) {
					maxd[i] = Math.max(dist[i][j], maxd[i]);
					max1 = Math.max(max1, maxd[i]);
				}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (dist[i][j] == Double.MAX_VALUE) {
					int dx = cord[i][0] - cord[j][0];
					int dy = cord[i][1] - cord[j][1];
					double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
					min = Math.min(min, d + maxd[i] + maxd[j]);
				}
		min = Math.max(max1, min);
		out.println((new BigDecimal(min)).setScale(6, BigDecimal.ROUND_HALF_UP));
		f.close();
		out.close();
	}
}