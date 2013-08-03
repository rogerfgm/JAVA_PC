package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class A {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int[] d = null;
	
	public void solve() throws Exception{
		int A = sc.nextInt();
		int N = sc.nextInt();
		d = new int[N];
		for(int i = 0; i < N; i++){
			d[i] = sc.nextInt();
		}
		Arrays.sort(d);
		int ret = check(A, 0);
		out.println(ret);
	}
	
	int check(int a, int ind){
		int ret = d.length - ind;;
		if(ind == d.length){
			return 0;
		}
		int num = 0;
		if(a <= d[ind]){
			if(a == 1){
				return ret;
			}
			while(a <= d[ind]){
				a += a-1;
				num++;
			}
		}
		a += d[ind];
		ret = min(ret, num + check(a, ind+1));
		
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		A b = new A();
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
