package gcj2014qual;


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
	
	double ans = 0;
	double C, F, X;
	
	public void solve() throws Exception{
		String s = br.readLine();
		String[] sp = s.split(" ");
		C = Double.parseDouble(sp[0]); // 増やすのに必要
		F = Double.parseDouble(sp[1]); // 増える量
		X = Double.parseDouble(sp[2]); //　目標
		
		ans = X / 2;
		double add = 2;
		double time = 0;
		for(int i = 0; i < 200000; i++){
			time = time + C / add;
			add += F;
			ans = min(ans, time + X / add);
		}
		out.println(ans);
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
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
