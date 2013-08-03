package gcj2013qual;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class C {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		long A = sc.nextLong();
		long B = sc.nextLong();
		int ST = (int)sqrt(A);
		long tmp = ST;
		if(tmp * tmp != A){
			ST++;
		}
		int MAX = (int)sqrt(B);
		int cnt = 0;
		for(int i = ST; i <= MAX; i++){
			long t = i;
			if(isp(t) && isp(t*t)){
				cnt++;
			}
		}
		out.println(cnt);
	}
	
	boolean isp(long p){
		String s = Long.toString(p);
		int lidx = s.length()-1;
		int idx = 0;
		while(idx < lidx){
			if(s.charAt(idx) != s.charAt(lidx)){
				return false;
			}
			
			idx++;
			lidx--;
		}
		return true;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-large-1.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		C b = new C();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
