package gcj2012_2;



import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Bsmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		int N = sc.nextInt();
		int W = sc.nextInt();
		int L = sc.nextInt();
		int[] r = new int[N];
		for(int i = 0; i < N; i++){
			r[i] = sc.nextInt();
		}
		int[] x = new int[N];
		int[] y = new int[N];
		Random rd = new Random();
		while(true){
			for(int i = 0; i < N; i++){
				x[i] = rd.nextInt(W+1);
				y[i] = rd.nextInt(L+1);
			}
			boolean flag = true;
			for(int i = 0; i < N-1; i++){
				for(int j = i+1; j < N; j++){
					double dist = dist(x[i], y[i], x[j], y[j]);
					if(dist <= r[i] + r[j] - DF){
						flag = false;
						break;
					}
				}
				if(!flag)break;
			}
			if(flag){
				break;
			}
		}
		for(int i = 0; i < N; i++){
			out.print(" " + x[i] + " " + y[i]);
		}
		out.println();
			
	}
	
	double dist(double x1, double y1, double x2, double y2){
		double x = x1 - x2;
		double y = y1 - y2;
		return sqrt(x*x + y*y);
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
			out.print("Case #" + t + ":");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
