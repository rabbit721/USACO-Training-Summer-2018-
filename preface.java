
/*
ID: 001207j1
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

public class preface {
	public static Hashtable<Integer, String> h = new Hashtable<Integer, String>();
	public static int[] nums = { 1, 5, 10, 50, 100, 500, 1000 };
	public static int[] c = new int[1001];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int N = Integer.parseInt(f.readLine());

		h.put(1, "I");
		h.put(5, "V");
		h.put(10, "X");
		h.put(50, "L");
		h.put(100, "C");
		h.put(500, "D");
		h.put(1000, "M");

		for (int i = 1; i <= N; i++)
			check(i);
		for (int i = 0; i < nums.length; i++)
			if (c[nums[i]] != 0)
				out.println(h.get(nums[i]) + " " + c[nums[i]]);
		f.close();
		out.close();
	}

	public static void count(int i) {
		if (h.get(i) != null) {
			c[i]++;
			return;
		}
		check(i);

	}

	public static void check(int i) {
		int rmd = i;
		int lrmd = 0;
		int[] c1 = c.clone();
		for (int x = nums.length - 1; x >= 0; x -= 2) {
			int tmp = (int) rmd / nums[x];
			if (tmp == 9) {
				c1[nums[x]]++;
				c1[nums[x + 2]]++;
				rmd = rmd % (9 * nums[x]);
			} else if (tmp == 4) {
				c1[nums[x]]++;
				c1[nums[x + 1]]++;
				rmd = rmd % (4 * nums[x]);
			} else if (tmp >= 5) {
				c1[nums[x + 1]]++;
				c1[nums[x]] += tmp - 5;
				rmd = rmd % (tmp * nums[x]);
			} else {
				c1[nums[x]] += tmp;
				rmd = rmd % nums[x];
			}
			if (rmd == 0) {
				c = c1;
				return;
			}
		}
	}
}
