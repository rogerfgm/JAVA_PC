package srm560;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TomekPhone {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minKeystrokes(int[] f, int[] k){
		Arrays.sort(f);
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 0; i < k.length; i++){
			for(int j = 1; j <= k[i]; j++){
				l.add(j);
			}
		}
		Collections.sort(l);
		int ans = 0;
		if(f.length > l.size()){
			return -1;
		}
		for(int i = f.length-1; i >= 0; i--){
			int j = f.length - 1 - i;
			ans += f[i] * l.get(j);
		}
		
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TomekPhone t = new TomekPhone();
		int[] f = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
		int[] k = {10,10,10,10,10,10,10,10};
		int r = t.minKeystrokes(f, k);
		out.println(r);
	}

}
