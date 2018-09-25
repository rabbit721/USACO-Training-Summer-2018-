
/*
ID: 001207j1
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

public class sprime {
	public static Integer[] sprimes_list = {2, 3, 5, 7};

	public static void main(String[] args) throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		int[] add_nums = { 1, 3, 5, 7, 9 };
				//new ArrayList<Integer>(Arrays.asList(init_nums));
		int N = Integer.parseInt(f.readLine());
		for (int i = 2; i <= N; i++) {
			generate_nums(i, add_nums);
		}
		for (int x : sprimes_list) {
			out.println(x);
		}
		out.close();
	}

	public static void generate_nums(int chang, int[] add_nums) {
		ArrayList<Integer> stor = new ArrayList<Integer>();
		for (int x = 0; x < sprimes_list.length; x++) {
			String temp = sprimes_list[x] + "";
			for (int i = 0; i < add_nums.length; i++) {
				int t = Integer.parseInt(temp + add_nums[i]);
				if (isPrime(t)) 
					stor.add(new Integer(t));
			}
			
		}
		sprimes_list = stor.toArray(new Integer[stor.size()]);
	}

	public static boolean isPrime(int num) {
		if (num % 2 == 0 && num != 2)
			return false;
		for (int i = 3; i * i <= num; i += 2)
			if (num % i == 0)
				return false;
		return true;
	}
}
