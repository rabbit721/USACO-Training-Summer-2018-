
/*
ID: 001207j1
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {

	public static void main(String[] args) throws IOException {
		prefix m = new prefix();
		m.run();
	}

	public static void run() throws IOException {
		long start_Time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

		String s = f.readLine();
		ArrayList<String> a = new ArrayList<String>();
		while (s.charAt(0) != '.') {
			String[] t = s.split(" ");
			for(String tt : t)
				a.add(tt);
			s = f.readLine();
		}

		String seq = "";
		s = f.readLine();
		while (s != null) {
			seq += s;
			s = f.readLine();
		}
		s = "";
		System.out.println(System.currentTimeMillis() - start_Time);
		boolean[] rc = new boolean[seq.length() + 1];
		int c = 0;
		boolean change = false;
		int j = 1;
		for (int i = 0; i < seq.length() - 1;) {
			for (; j <= seq.length(); j++) {
				if (search(seq.substring(i, j), a)) {
					c = j;
					change = true;
				} else if (change) {
					i = j - 1;
					j = i + 1;
				}
			}
		}
		out.println(c);
		f.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start_Time);
	}

	public static boolean search(String s, ArrayList<String> a) {
		for (int i = a.size() - 1; i >= 0; i--)
			if ((a.get(i)).length() == s.length() && s.equals(a.get(i)))
				return true;
			else if((a.get(i)).length() < s.length())
				return false;
		return false;
	}
}
