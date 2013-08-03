package srm578;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class DeerInZooDivTwo {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	
	public int[] getminmax(int N, int K){
		int[] ret = new int[2];
		if(N > K){
			ret[0] = N - K;
		}
		int d = K / 2;
		if(K % 2 > 0){
			d++;
		}
		ret[1] = N - d;
		
		
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeerInZooDivTwo t = new DeerInZooDivTwo();
		int[] r = t.getminmax(3, 2);
		
		out.println(r[0] + " " + r[1]);
	}

}
