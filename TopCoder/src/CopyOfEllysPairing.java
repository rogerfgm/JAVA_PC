import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class CopyOfEllysPairing {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	
	public int getMax(int M, int[] ct, int[] ft, int[] ml, int[] ad){
		long mn = 0;
		int mc = 0;
		N = ct.length;
		long total = 0;
		for(int i = 0; i < N; i++){
			long mul = ml[i];
			long sec = (mul * ft[i] + ad[i]) % M;
			if(sec == ft[i]){
				if(mc > ct[i]){
					mc = ct[i];
					mn = ft[i];
				}
			}
			total += ct[i];
		}
		if(mn > 0){
			long hit = 0;
			long other = 0;
			for(int i = 0; i < N; i++){
				long cur = ft[i];
				for(int j = 0; j < ct[i]; j++){
					if(cur == mn){
						hit += 1;
					}
					else{
						other += 1;
					}
					cur = (cur * ml[i] + ad[i]) % M;
				}
			}
			if(hit <= other){
				return (int)(total / 2);
			}
			else{
				return (int) other;
			}
		}
		else{
			return (int)(total / 2);
		}
		
		
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CopyOfEllysPairing t = new CopyOfEllysPairing();
		int M = 16;
		int[] ct = {4, 7};
		int[] ft ={5, 3};
		int[] ml ={2, 3};
		int[] ad ={1, 0};
		int r = t.getMax(M, ct, ft, ml, ad);
		out.println(r);
	}

}
