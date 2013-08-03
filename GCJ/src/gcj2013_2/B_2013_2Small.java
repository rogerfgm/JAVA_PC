package gcj2013_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B_2013_2Small {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int P = 0;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		P = sc.nextInt();
		
		if(P == 1){
			out.println("0 0");
			return ;
		}
		long M = b << N;
		int a1 = 0;
		int a2 = 0;
		for(int i = 1; i < M; i++){
			int remain = i;
			int cnt = 0;
			long m = M;
			while(remain > 0){
				remain--;
				remain /= 2;
				cnt += m / 2;
				m/= 2;
			}
			if(cnt < P){
				a1 = i;
			}
			else{
				break;
			}
		}
		
		for(int i = 1; i < M; i++){
			long remain = M - i - 1;
			long cnt = 0;
			long m = M;
			while(remain > 0){
				remain--;
				remain /= 2;
				cnt += m / 2;
				m/= 2;
			}
			cnt = M - cnt;
			if(cnt <= P){
				a2 = i;
			}
			else{
				break;
			}
		}
		
		out.println(a1 + " " + a2);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		File file = new File("B-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		B_2013_2Small b = new B_2013_2Small();
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
