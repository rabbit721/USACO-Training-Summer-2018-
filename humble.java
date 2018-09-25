
/*
ID: 001207j1
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;

public class humble {
	public static long[] num;
	public static int[] pindex;
	public static long[] humbles;

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		String[] str = br.readLine().split("\\s");
		int K = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		str = br.readLine().split("\\s");
		num = new long[K];
		pindex = new int[K];
		humbles = new long[N + 1];
		for (int i = 0; i < K; num[i] = Integer.parseInt(str[i]), i++)
			;
		Arrays.sort(num);
		humbles[0] = 1;

		for (int i = 1; i <= N; i++) {
			long last = humbles[i - 1];
			long min = Long.MAX_VALUE;
			for (int k = 0; k < K; k++) {
				for (int j = pindex[k]; j < i; j++) {
					if (num[k] * humbles[j] > last) {
						min = Math.min(min, num[k] * humbles[j]);
						break;
					} else {
						pindex[k] = j;
					}
				}
			}
			humbles[i] = min;
		}
		out.println(Long.toString(humbles[N]));
		br.close();
		out.close();
	}
}