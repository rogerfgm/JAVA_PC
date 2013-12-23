package srm601;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class WinterAndPresents {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public  long getNumber(int[] a, int[] o){
		long ans = 0;
		int N = a.length;
		
		for(int i = 1; ; i++){
			int mina = 0;
			int maxa = 0;
			boolean f = true;
			for(int j = 0; j < N; j++){
				if(a[j] + o[j] < i){
					f = false;
				}
				maxa +=  min(i, a[j]);
				mina += max(0, i - o[j]);
			}
			if(!f){
				break;
			}
			ans += maxa - mina + 1;
		}
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WinterAndPresents t = new WinterAndPresents();
		int[] a = {1000000};
		int[] o = {1000000};
		long r = t.getNumber(a, o);
		out.println(r);
	}

}
