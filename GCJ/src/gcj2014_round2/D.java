package gcj2014_round2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class D {
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
	
	int X = 0;
	int Y = 0;
	String[] ss = null;
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split(" ");
		M = Integer.parseInt(sp[0]);
		N = Integer.parseInt(sp[1]);
		ss = new String[M];
		for(int i = 0; i < M; i++){
			ss[i] = br.readLine();
		}
		int[] d = new int[M];
		X = 0;
		check(d, 0);
		X+=N;
		println(X + " " + Y);
	}
	
	void check(int[] d, int idx){
		if(idx == M){
			boolean[] used = new boolean[N];
			for(int i = 0; i < M; i++){
				int k = d[i];
				used[k] = true;
			}
			for(int i = 0; i < N; i++){
				if(!used[i])return;
			}
			int cnt = 0;
			for(int i = 0; i < N; i++){
				List<String> l = new ArrayList<String>();
				Set<String> set = new HashSet<String>();
				for(int j = 0; j < M; j++){
					if(d[j] == i){
						String s = ss[j];
						for(int k = 1; k < s.length()+1; k++){
							
								String p = s.substring(0, k);
								set.add(p);
							
						}
					}
				}
				cnt += set.size();
			}
			if(cnt > X){
				X = cnt;
				Y = 1;
			}
			else if(cnt == X){
				Y++;
			}
		}
		else{
			for(int i = 0; i < N; i++){
				int[] nd = new int[M];
				for(int j = 0; j < M; j++){
					nd[j] = d[j];
				}
				nd[idx] = i;
				check(nd, idx+1);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("D-small-attempt0.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		D b = new D();
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
