package srm593;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

//解説は理解した答えはmax(S-totalA, totalB-S)になる。Sは選択したiにおけるsum(Ai+Bi)
//iと(Ai+Bi)の値でDPできる。Ai+Biのとり得る値は10,000*50*2なので
public class NOTMayTheBestPetWin {

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
		NOTMayTheBestPetWin t = new NOTMayTheBestPetWin();
		int[] A = {1,3,5,4,5};
		int[] B = {2,5,6,8,7};
		int r = t.calc(A, B);
		out.println(r);
	}

}
