package gcj20141C;


import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A {
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
	
	String I = "impossible";
	
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split("/");
		String P = sp[0];
		String Q = sp[1];
		long p = Long.parseLong(P);
		long q = Long.parseLong(Q);
		while(p % 2 == 0 && q % 2 == 0){
			p /= 2;
			q /= 2;
		}

		List<Long> list = new ArrayList<Long>();
		for(int i = 1; i <= 40; i++){
			long n = (long)Math.pow(2, i);
			list.add(n);
			//System.out.println(n);
		}
		BigInteger b0 = BigInteger.valueOf(0);
		BigInteger b2 = BigInteger.valueOf(2);
		BigInteger bsi = BigInteger.valueOf(p);
		BigInteger bo = BigInteger.valueOf(q);
		long ans = 50;
		for(int i = 0; i < list.size(); i++){

			BigInteger b1 = BigInteger.valueOf(1);
			BigInteger bi = BigInteger.valueOf(list.get(i));
			bsi = bsi.multiply(bi);
			while(bsi.compareTo(bo) >= 0){
				bsi = bsi.subtract(bo);
				ans = Math.min(i+1, ans);
			}
			bo = bo.multiply(bi);
			
			
			if(bsi.equals(b0)){
				
				break;
				
			}
			
			
			
		}
		if(bsi.equals(b0) && ans <= 40){
			println(ans);
		}
		else{
			println(I);
		}
		return;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large-practice.in");
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
		
		A b = new A();
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
