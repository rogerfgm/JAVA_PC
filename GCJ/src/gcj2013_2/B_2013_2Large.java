package gcj2013_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B_2013_2Large {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	long N = 0;
	long P = 0;
	long M = 0;
	
	public void solve() throws Exception{
		N = sc.nextLong();
		P = sc.nextLong();
		
		if(P == 1){
			out.println("0 0");
			return ;
		}
		M = b << N;
		long a1 = 0;
		long a2 = 0;
		{
			long l = 0;
			long r = M-1;
			if(ck1(r)){
				a1 = r;
			}
			else{
				while(l + 1 < r){
					long m = (l + r) / 2;
					if(ck1(m)){
						l = m;
					}
					else{
						r = m;
					}
				}
				a1 = l;
			}
			
			
		}
		

		{
			long l = 0;
			long r = M-1;
			if(ck2(r)){
				a2 = r;
			}
			else{
				while(l + 1 < r){
					long m = (l + r) / 2;
					if(ck2(m)){
						l = m;
					}
					else{
						r = m;
					}
				}
				a2 = l;
			}
		
		}
		
		out.println(a1 + " " + a2);
		
	}
	
	boolean ck1(long a){
		long remain = a;
		long cnt = 0;
		long m = M;
		while(remain > 0){
			remain--;
			remain /= 2;
			cnt += m / 2;
			m/= 2;
		}
		if(cnt < P){
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean ck2(long a){
		long remain = M - a - 1;
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
			return true;
		}
		else{
			return false;
		}
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
		
		B_2013_2Large b = new B_2013_2Large();
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
