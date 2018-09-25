
/*
   ID: 001207j1
   LANG: JAVA
   TASK: game1
 */
import java.io.*;
import java.util.*;

public class game1 {//dp
	public static int N;

	public static void main(String[] args) throws Exception {
		game1 main = new game1();
		main.run();
	}

	public static int judge(ArrayList<Integer> q, int diff) {
		int step = N - q.size();
		int d;
		if (step == 0)
			return diff;
		int left = q.get(0) - Math.max(q.get(1), q.get(q.size() - 1));
		int right = q.get(q.size() - 1) - Math.max(q.get(0), q.get(q.size() - 2));
		if (step % 2 == 0) 
			d = diff + Math.max(left, right);
		else 
			d = diff - Math.max(left, right);
		
		if (left == right) {
			ArrayList<Integer> q1 = new ArrayList<Integer>(q);
			q1.remove(0);
			int r1 = judge(q1, d);
			ArrayList<Integer> q2 = new ArrayList<Integer>(q);
			q2.remove(q.size() - 1);
			int r2 = judge(q2, d);
			return Math.max(r1, r2);
		} else {
			if (left < right)
				q.remove(q.size() - 1);
			else
				q.remove(0);
			return judge(q, d);
		}
	}

	public static void run() throws Exception {
		long st = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		N = in.nextInt();
		ArrayList<Integer> q = new ArrayList<Integer>();
		for (int i = 0; i < N; i++)
			q.add(in.nextInt());
		System.out.println(judge(q, 0));
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis() - st);
	}
}
