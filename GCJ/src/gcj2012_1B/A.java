package gcj2012_1B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class A {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int[] d = null;
	int sum = 0;
	public void solve() throws Exception{
		N = sc.nextInt();
		d = new int[N];
		sum = 0;
		for(int i = 0; i < N; i++){
			d[i] = sc.nextInt();
			sum += d[i];
		}
		for(int i = 0; i < N; i++){
			if(check(i, 0)){
				out.print(" 0.0");
			}
			else{
				double min = 0;
				double max = 1;
				while(min + DF < max){
					double mid = (min + max) / 2;
					if(check(i, mid)){
						max = mid;
					}
					else{
						min = mid;
					}
				}
				if(max < DF){
					max = 0;
				}
				max *= 100;
				
				out.print(" " + max);
			}
		}
		out.println();
	}
	
	boolean check(int idx, double per){
		double target = d[idx] + sum * per;
		
		double remper = 1 - per;
		for(int i = 0; i < N; i++){
			if(i == idx) continue;
			double tar = target - d[i];
			if(tar > 0){
				double tper = tar / sum;
				remper -= tper;
			}
		}
		if(remper <= 0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		A b = new A();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ":");
			b.solve();
			t++;
		}
		out.close();
	}
}
