import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class HyperKnight {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public long countCells(int A, int B, int r, int c, int k){
		

		long a = max(A, B);
		long b = min(A, B);
		
		
		long R = 0;
		long C = 0;

		long ans = 0;
		
		if(k == 8){
			R = r - 2 * a;
			C = c - 2 * a;
			ans = R * C;
		}
		else if(k == 6){

			R = a - b ;
			C = c - 2 * a;
			ans += 2 * R * C;
			C = r  - 2 * a;
			ans += 2 * R * C;
		}
		else if(k == 4){

			R = r - 2 * a ;
			C = b;
			ans += 2 * R * C;
			
			R = c  - 2 * a ;
			ans += 2 * R * C;
			
			R = a - b;
			C = a - b;
			ans += 4 * R * C;

		}
		else if(k == 3){
			ans = 8 * (a - b) * b;
		}
		else if(k == 2){
			ans = 4 * b * b;

		}

		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HyperKnight t = new HyperKnight();
		long r = t.countCells(3, 2, 8, 8, 2);
		//long r = t.countCells(2, 3, 1000000000, 1000000000, 8);
		/*
		 * 
		 {3, 2, 8, 8, 2}

Expected:
16

Received:
32
		 */
		out.println(r);
	}

}
