import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;


public class MayTheBestPetWin {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int calc(int[] A, int[] B){
		int ans = 0;
		
		return ans;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MayTheBestPetWin t = new MayTheBestPetWin();
		int[] A = {1,3,5,4,5};
		int[] B = {2,5,6,8,7};
		int r = t.calc(A, B);
		out.println(r);
	}

}
