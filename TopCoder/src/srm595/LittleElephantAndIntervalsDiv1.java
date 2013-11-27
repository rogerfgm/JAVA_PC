package srm595;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class LittleElephantAndIntervalsDiv1 {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public long getNumber(int M, int[] L, int[] R){
		long a = 1;
		boolean[] f = new boolean[M];
		for(int i = 0; i < L.length; i++){
			int idx = L.length - i - 1;
			int l = L[idx] - 1;
			int r = R[idx] - 1;
			boolean flag = false;
			for(int j = l; j <= r; j++){
				if(!f[j]){
					flag = true;
				}
				f[j] = true;
			}
			if(flag) a *= 2;
		}
		
		
		return a;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LittleElephantAndIntervalsDiv1 t = new LittleElephantAndIntervalsDiv1();

		out.println();
	}

}
