package gcj20131C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Bsmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		int x = sc.nextInt();
		int y = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		String p = null;
		String m = null;
		if(x >= 0){
			p = "E";
			m = "W";
		}
		else{
			p = "W";
			m = "E";
		}
		x = abs(x);
		
		for(int i = 0; i < x; i++){
			sb.append(m + p);
		}
		if(y >= 0){
			p = "N";
			m = "S";
		}
		else{
			p = "S";
			m = "N";
		}
		y = abs(y);
		for(int i = 0; i < y; i++){
			sb.append(m + p);
		}
		out.println(sb);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		Bsmall b = new Bsmall();
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
