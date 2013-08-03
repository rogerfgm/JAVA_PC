package gcj2012_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class WAC2012_2 {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	final int max = 1000000000;
	
	final int xlen = 1000;
	int[] ans = null;
	int[] d = null;
	public void solve() throws Exception{
		N = sc.nextInt();
		
		d = new int[N-1];
		for(int i = 0; i < N-1; i++){
			d[i] = sc.nextInt()-1;
		}
		ans = new int[N];
		

		int idx = 0;
		while(idx < N-1){
		
			int hidx = d[idx];
			ans[hidx] = max;
			if(!check(idx, hidx)){
				out.println("Impossible");
				return;
			}
			idx = hidx;
		}
		for(int i =  0; i < N; i++){
			if(i != 0) out.print(" ");
			out.print(ans[i]);
		}
		out.println();
		
	}
	
	boolean check(int idx, int hidx){
		int start = idx;
		for(int i = idx+1; i < hidx; i++){
			if(d[i] > hidx || d[i] <= i){
				return false;
			}
		}
		
		if(idx + 1 == hidx){
			return true;
		}
		if(hidx <= idx){
			return false;
		}
		while(idx < hidx){
			if(d[idx] != hidx){
				double kata = (double)(ans[hidx] - ans[idx]) / (hidx-idx) / 100;
				int nheit = (int)(kata * (d[idx] - idx) * 100 ) + 10;
				ans[d[idx]] = nheit;
				boolean f =  check(idx, d[idx]);
				if(!f){
					return false;
				}
				idx = d[idx];
			}
			else{
				idx++;
			}
		}
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-practice.in");
		//File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		WAC2012_2 b = new WAC2012_2();
		int T = sc.nextInt();
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
