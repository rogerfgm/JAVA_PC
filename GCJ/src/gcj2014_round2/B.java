package gcj2014_round2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B {
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
	
	int[] d = null;
	int ans = 0;
	public void solve() throws Exception{
		String s = br.readLine();
		
		//String[] sp = s.split(" ");
		N = parseInt(s);
		d = new int[N];
		s = br.readLine();
		String[] sp = s.split(" ");
		for(int i = 0; i < N; i++){
			d[i] = parseInt(sp[i]);
		}
		int ans = 0;
		for(int i = 0; i < N; i++){
			int l = 0;
			int r = 0;
			for(int j = 0; j < i; j++){
				if(d[j] > d[i]){
					l++;
				}
			}
			for(int k = i+1; k < N; k++){
				if(d[k] > d[i]){
					r++;
				}
			}
			ans += Math.min(l, r);
		}
		
		println(ans);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-large.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		B b = new B();
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
