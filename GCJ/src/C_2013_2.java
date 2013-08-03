

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class C_2013_2 {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		int[] inc = new int[N];
		int[] dec = new int[N];
		for(int i = 0; i < N; i++){
			inc[i] = sc.nextInt();
		}
		for(int i = 0; i < N; i++){
			dec[i] = sc.nextInt();
		}
		int[] ans = new int[N];
		int now = 1;
		while(now <= N){
			int midx = -1;
			int min = INF;
			for(int i = 0; i < N; i++){
				if(ans[i] == 0){
					if(inc[i] <= min){
						min = inc[i];
						midx = i;
					}
				}
			}
			ans[midx] = now;
			now++;
		}
		for(int i = 0; i < N; i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(ans[i]);
		}
		out.println();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		C_2013_2 b = new C_2013_2();
		int T = 0;
		if(sc != null){
			T = sc.nextInt();
		}
		else{
			T = parseInt(br.readLine());
		}
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
