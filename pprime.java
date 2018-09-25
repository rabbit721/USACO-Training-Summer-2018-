/*
ID: 001207j1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;
public class pprime {
  public static String str_Num = "";
  public static boolean enough = false;
  public static PrintWriter out;
  public static BufferedReader f;
  public static int minL;
  public static int maxL;
  public static void main (String [] args) throws IOException {
	long start_Time = System.currentTimeMillis();
	f = new BufferedReader(new FileReader("pprime.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    int[] nums = {0,0,1,2,3,4,5,6,7,8,9};
    String[] ss = f.readLine().split(" ");
    int lower_Limit = (int)Integer.parseInt(ss[0]);
    int upper_Limit = (int)Integer.parseInt(ss[1]);
    maxL = ss[1].length();
    minL = ss[0].length();
    for (int chang = minL; chang< maxL+1; chang++) {
        generate_Palind(nums, chang, 1, lower_Limit, upper_Limit);
    }

    out.close();
    //System.out.println(System.currentTimeMillis()-start_Time);
  }
  public static boolean isPrime(int n) {
		if (n % 2 == 0 && n != 2)
		    return false;
		for (int i = 3; i * i <= n; i += 2)
		    if (n % i == 0)
			return false;
		return true;
  }
  public static void generate_Palind(int[] nums, int chang, int pos, int lower_Limit, int upper_Limit){
      if(enough) return;
      int lang = (int)((chang+1)/2);
      if(pos>chang){
    	int num = Integer.parseInt(str_Num);
        if (num > upper_Limit){
          enough = true;
          return;
        }
        
        if(isPrime(num) && num >= lower_Limit){
          out.println(num);
        }
        str_Num = str_Num.substring(0, lang);
        return;
      }
      if (pos<= lang)
        for (int i =1 ; i < 11; i++){
          if (pos==1 && nums[i] == 0) continue;
          if (chang == minL && Integer.parseInt(str_Num+nums[i]) < Integer.parseInt((lower_Limit+"").substring(0, pos)))   continue;
          str_Num += nums[i];
          generate_Palind(nums, chang, pos+1, lower_Limit, upper_Limit);
          str_Num = str_Num.substring(0,str_Num.length()-1);
        }
      else{
          str_Num += str_Num.charAt(chang-pos);
          generate_Palind(nums, chang, pos+1,lower_Limit, upper_Limit);
      }
  }
}
