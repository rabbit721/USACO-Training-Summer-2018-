
/*
   ID: 001207j1
   LANG: JAVA
   TASK: spin
 */
import java.io.*;
import java.util.Arrays;

public class spin {
	public static void main(String[] args) throws Exception {
		spin s = new spin();
		s.run();
	}

	public static void run() throws Exception {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		int[][] rcd = new int[5][5];
		int[] sp = new int[5];
		int[][] ext = new int[5][5];
		for (int i = 0; i < 5; i++) {
			Arrays.fill(rcd[i], -1);
			Arrays.fill(ext[i], -1);
		}
		for (int i = 0; i < 5; i++) {
			String s[] = f.readLine().split(" ");
			sp[i] = Integer.parseInt(s[0]);
			for (int j = 0; j < Integer.parseInt(s[1]) * 2; j += 2) {
				rcd[i][j / 2] = Integer.parseInt(s[j + 2]);
				ext[i][j / 2] = Integer.parseInt(s[j + 3]);
			}
		}
		int sec = 0;
		while (true) {
			boolean mark[] = new boolean[360];
			boolean flag = true;
			for (int i = 0; i < 5; i++) {
				boolean flag2 = false;
				boolean mark1[] = new boolean[360];
				for (int j = 0; j < 5; j++) {
					if (rcd[i][j] < 0)
						continue;
					for (int k = 0; k <= ext[i][j]; k++) {
						int tmp = (rcd[i][j] + k) % 360;
						if (i == 0 || mark[tmp])
							flag2 = mark1[tmp] = true;
					}
				}
				flag = flag && flag2;
				mark = mark1;
				for (int k = 0; k < 5; k++)
					rcd[i][k] = (rcd[i][k] + sp[i]) % 360;
			}
			sec++;
			if (sec > 360 || flag)
				break;
		}
		if (sec < 360)
			out.println(sec - 1);
		else
			out.println("none");
		f.close();
		out.close();

	}
}
