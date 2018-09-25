
/*
   ID: 001207j1
   LANG: JAVA
   TASK: fence
 */

import java.io.*;
import java.util.*;

public class fence {
	public static Map<Integer, PriorityQueue<Integer>> a = new HashMap<Integer, PriorityQueue<Integer>>();
	public static ArrayList<Integer> cc = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		fence main = new fence();
		main.run();
	}

	public void run() throws Exception {
		long st = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		int F = Integer.parseInt(f.readLine());

		for (int i = 0; i < F; i++) {
			String[] s = f.readLine().split(" ");
			int u = Integer.parseInt(s[0]), v = Integer.parseInt(s[1]);
			if (!a.containsKey(u))
				a.put(u, new  PriorityQueue<Integer>());
			if (!a.containsKey(v))
				a.put(v, new PriorityQueue<Integer>());
			a.get(u).add(v);
			a.get(v).add(u);
		}
		boolean flag = false;
		SortedSet<Integer> sorted = new TreeSet<>(a.keySet());
		for (int i : sorted)
			if (a.get(i).size() % 2 != 0) {
				find_cc(i);
				flag = true;
				break;
			}
		if (!flag)
			for (int i = 1; i <= F; i++)
				if (a.get(i).size() != 0) {
					find_cc(i);
					break;
				}
		for (int i = cc.size() - 1; i >= 0; i--)
			out.println(cc.get(i));
		f.close();
		out.close();
	}

	public static void find_cc(Integer s) {
		if (a.get(s).size() == 0) {
			cc.add(s);
		} else {
			while(!a.get(s).isEmpty()) {
				Integer nxt = a.get(s).poll();
				a.get(nxt).remove(s);
				find_cc(nxt);
			}
			cc.add(s);
		}
	}
}
