package gcj20141C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class Bsmall {
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
	int[] st = null;
	int[] ed = null;
	String[] ds = null;
	
	public void solve() throws Exception{
		String s = br.readLine();
		N = Integer.parseInt(s);
		s = br.readLine();
		String[] sp = s.split(" ");
		ds = new String[N];
		st = new int[N];
		ed = new int[N];
		for(int i = 0; i < N; i++){
			s = sp[i];
			StringBuilder sb = new StringBuilder();
			sb.append(s.substring(0,  1));
			for(int j = 1; j < s.length(); j++){
				if(s.charAt(j) != s.charAt(j-1)){
					sb.append(s.substring(j, j+1));
				}
			}
			ds[i] = sb.toString();
			st[i] = ds[i].charAt(0);
			ed[i] = (int)ds[i].charAt(ds[i].length()-1);
		
		}
		long ans = 0;
		
		boolean hasError = false;
		for(int i = 0; i < N; i++){
			s = ds[i];
			Set<Integer> set = new HashSet<Integer>();
			set.add((int)ds[i].charAt(0));
			for(int j = 1; j < s.length(); j++){
				if(set.contains((int)s.charAt(j)) && s.charAt(j) != s.charAt(j-1)){
					hasError = true;
				}
			}
		}
		if(hasError){
			println("0");
			return;
		}
		
		for(int i = 0; i < N; i++){
			boolean[] fs = new boolean[N];
			fs[i] = true;
			ans += check(fs, ed[i]);
	
		}
		println(ans);
	}
	
	long check(boolean[] fs, int p){
		boolean end = true;
		for(int i = 0; i < N; i++){
			if(!fs[i]){
				end = false;
			}
		}
		if(end) return 1;
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < N; i++){
			if(fs[i]){
				for(int j = 0; j < ds[i].length(); j++){
					set.add((int)ds[i].charAt(j));
				}
			}
		}
		long sum = 0;
		for(int i = 0; i < N; i++){
			if(!fs[i]){
				String s = ds[i];
				int idx = 0;
				while(idx < s.length() && (int)s.charAt(idx) == p){
					idx++;
				}
				boolean faul = false;
				for(int j = idx; j < s.length(); j++){
					if(set.contains((int)s.charAt(j)) ){
						faul = true;
					}
				}
				if(!faul){
					fs[i] = true;
					sum += check(fs, ed[i]);
					fs[i] = false;
				}
			}
		}
		
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		else{
			throw new Exception("can't find a input file : " + file.getAbsolutePath());
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		Bsmall b = new Bsmall();
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
