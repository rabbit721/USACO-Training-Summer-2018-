import java.util.*;

public class scorer {
	public static ArrayList<int[]> scoring = new ArrayList<int[]>();
	public static int[] rd_score = { 40, 40, 39, 38, 38, 37, 37, 36, 35, 35, 34, 33, 33, 32, 32, 31, 31, 30, 30, 29, 29,
			28, 28, 27, 26, 26, 25, 25, 24, 24, 23, 23, 22, 22, 21, 21, 20, 20, 19, 19, 18, 17, 17, 16, 15, 15, 14, 13,
			12, 11, 10, 10, 10 };
	public static int[] wl_score = { 40, 39, 38, 37, 36, 35, 34, 34, 33, 32, 32, 31, 30, 30, 29, 28, 28, 27, 26, 26, 25,
			25, 24, 23, 23, 22, 21, 21, 20, 19, 19, 18, 17, 16, 16, 15, 14, 13, 13, 12, 11, 10, 10, 10, 10 };
	public static int[] mt_score = { 80, 79, 78, 76, 75, 74, 73, 71, 70, 69, 68, 67, 67, 66, 65, 64, 63, 62, 61, 60, 60,
			59, 58, 57, 56, 56, 55, 54, 53, 52, 52, 51, 50, 49, 48, 48, 47, 46, 45, 44, 43, 42, 41, 39, 38, 37, 36, 34,
			33, 32, 31, 29, 28, 26, 24, 23, 21, 20, 20 };

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String tmp = in.nextLine();
		scoring.add(rd_score);
		scoring.add(wl_score);
		scoring.add(mt_score);
		while (!tmp.equals("terminate")) {
			String s[] = tmp.split(" ");
			// System.out.println(tmp+" "+s);
			String out = "";
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					out += "rd: ";
					break;
				case 1:
					out += "wl: ";
					break;
				case 2:
					out += "mth: ";
					break;
				}
				int t = 10 * scoring.get(i)[Integer.parseInt(s[i])];
				sum += t;
				out += t + ";   ";
			}
			System.out.println(out + " sum: "+sum+";");
			tmp = in.nextLine();
		}
		in.close();
	}

}
