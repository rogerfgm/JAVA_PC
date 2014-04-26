

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class C {
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
		String s = br.readLine();
		N = 1000;
		String[] sp = s.split(" ");
		int[] good = new int[1000];
		int[] bad = new int[1000];
		Random rnd = new Random();
		int t = 0;
		for(int i = 0; i < N; i++){
			good[i] = bad[i] = i;
		}
		for(int i = 0; i < N; i++){
			int idx = rnd.nextInt(N);
			t = bad[i];
			bad[i] = bad[idx];
			bad[idx] = t;
			
			
			idx = rnd.nextInt(N-i);
			idx += i;
			t = good[i];
			good[i] = good[idx];
			good[idx] = t;
		}
		
		int gsum = 0;
		int bsum = 0;
		for(int i = 0; i < N; i++){
			gsum += abs(good[i] - i);
			bsum += abs(bad[i] - i);
		}
		System.out.println(gsum + " " + bsum);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		C b = new C();
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
			System.out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
	
	void print(int i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(int i){
		out.println(i + "");
		System.out.println(i);
	}
	void print(String s){
		out.print(s);
		System.out.print(s);
	}
	void println(String s){
		out.println(s);
		System.out.println(s);
	}
	void print(long i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(long i){
		out.println(i + "");
		System.out.println(i);
	}
}
