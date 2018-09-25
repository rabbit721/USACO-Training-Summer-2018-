
/*
ID: 001207j1
LANG: JAVA
TASK: contact
*/
import java.io.*;
import java.util.*;

public class contact {
	public static void main(String[] args) throws Exception {
		contact c = new contact();
		c.run();
		System.exit(0);
	}

	public static void run() throws Exception {

		long star = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder(f.readLine());
		String line = f.readLine();

		while (line != null) {
			sb.append(line);
			line = f.readLine();
		}
		String seq = sb.toString();
		f.close();
		// System.out.println(System.currentTimeMillis() - star);
		Map<Integer, ArrayList<String>> m = new HashMap<Integer, ArrayList<String>>();
		for (int L = A; L <= B; L++) {
			int[] rcd = new int[(int) Math.pow(2, L)];
			String[] srcd = new String[(int) Math.pow(2, L)];
			for (int i = 0; i <= seq.length() - L; i++) {
				String key = seq.substring(i, i + L);
				srcd[Integer.parseInt(key, 2)] = key;
				rcd[Integer.parseInt(key, 2)]++;
			}
			for (int i = 0; i < rcd.length; i++) {
				if (rcd[i] == 0)
					continue;
				else {
					ArrayList<String> a = new ArrayList<String>();
					if (m.get(rcd[i]) == null) {
						a.add(srcd[i]);
						m.put(rcd[i], a);
					} else {
						a = m.get(rcd[i]);
						a.add(srcd[i]);
						m.put(rcd[i], a);
					}
				}

			}
		}
		// System.out.println(System.currentTimeMillis() - star);
		SortedSet<Integer> ks = new TreeSet<>(m.keySet());
		Integer[] keys = ks.toArray(new Integer[ks.size()]);
		for (int i = keys.length - 1; i >= keys.length - N && i >= 0; i--) {
			out.println(keys[i]);
			int r = 0;
			ArrayList<String> t = m.get(keys[i]);
			for (int j = 0; j < t.size(); j++) {
				if (j == 0) {
					out.print(t.get(j));
					r += t.get(j).length() + 1;
				} else {
					if (r + t.get(j).length() > 80) {
						out.println();
						out.print(t.get(j));
						r = t.get(j).length() + 1;
					} else {
						r += t.get(j).length() + 1;
						out.print(" " + t.get(j));
					}
				}

			}
			out.println();
		}

		// System.out.println(System.currentTimeMillis() - star);
		out.close();
	}
}