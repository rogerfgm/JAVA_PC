package srm580;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class EelAndRabbit {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getmax(int[] l, int[] t){
		int[] x = new int[l.length];
		int[] y = new int[l.length];
		set = new HashSet<Integer>();
		for(int i = 0; i < l.length; i++){
			x[i] = t[i];
			y[i] = x[i] + l[i];
			set.add(x[i]);
			set.add(y[i]);
		}
		Integer[] T = new Integer[0];
		T = set.toArray(T);
		
		int ans = 0;
		for(int i = 0; i < T.length-1; i++){
			for(int j = i+1; j < T.length; j++){
				set = new HashSet<Integer>();
				for(int k = 0; k < x.length; k++){
					int ck = T[i];
					if(ck >= x[k] && ck <= y[k]){
						set.add(k);
					}
					ck = T[j];
					if(ck >= x[k] && ck <= y[k]){
						set.add(k);
					}
				}
				ans = max(ans, set.size());
			}
		}
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EelAndRabbit t = new EelAndRabbit();
		int[] l = {1, 1, 1};
		int[] tt = {2, 0, 4};
		int r = t.getmax(l, tt);
		
		out.println(r);
	}

}
