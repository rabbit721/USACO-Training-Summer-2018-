/*
   ID: 001207j1
   LANG: JAVA
   TASK: ariprog
 */
import java.io.*;
import java.util.*;

public class ariprog {
public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        //long start_Time = System.currentTimeMillis();
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        int N = Integer.parseInt(new StringTokenizer(f.readLine()).nextToken());
        int M = Integer.parseInt(new StringTokenizer(f.readLine()).nextToken());
        int num_max = (int) (2*Math.pow(M,2));
        ArrayList<int[]> record_List = new ArrayList<int[]>();
        boolean[] num_List = new boolean[num_max+1];
        for(int x = 0; x <= M; x++) {
                for(int y = 0; y <= M; y++) {
                        int a = (int) (Math.pow(x,2)+Math.pow(y,2));
                        num_List[a] = true;
                }
        }
        System.out.println(num_List.length);
        for (int i = 0; i < num_max+1; i++) {
                if(!num_List[i]) continue;
                for(int j = i+1; j < num_max+1; j++) {
                        if(!num_List[j]) continue;
                        int b = j - i;
                        if (i+(N-1)*b > num_max) {
                                break;
                        }
                        if (checkNum(i,num_List,b,N)) {
                                int k[] = {i,b};
                                record_List.add(k);
                        }
                }
        }
        Collections.sort(record_List,new Comparator<int[]>(){
                        public int compare(int[] o1, int[] o2) {
                                if(o1[1] < o2[1]) return -1;
                                if(o1[1] > o2[1]) return 1;
                                if(o1[0] < o2[0]) return -1;
                                if(o1[0] < o2[0]) return 1;
                                return 0;
                        }
                });
        if(record_List.size()==0) {
                out.println("NONE");
        }
        for(int[] ab : record_List)
                out.println(ab[0]+" "+ab[1]);


        // output result
        f.close();
        out.close();
        //System.out.println(System.currentTimeMillis()-start_Time);
}
public static boolean checkNum(int i, boolean[] num_List,int b, int N){
        for (int j = 2; j < N; j++)
                if(!num_List[i+b*j])
                        return false;
        return true;
}
}
