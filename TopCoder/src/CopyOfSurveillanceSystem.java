import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class CopyOfSurveillanceSystem {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int[] a = null;
	int[] r = null;
	int L = 0;
	int cnt = 0;
	boolean[] st = null;
	long fil = 0;
	int[] num = null;
	public String getContainerInfo(String c, int[] reports, int L){
		N = c.length();
		a = new int[c.length()];
		r = reports;
		this.L = L;
		cnt = 0;
		st = new boolean[N];
		for(int i = 0; i < N; i++){
			if(c.charAt(i) == 'X'){
				st[i] = true;
			}
		}
		for(int i = 0; i < L; i++){
			fil |= b << i;
		}
		num = new int[N];
		for(int i = 0; i < N - L + 1; i++){
			int n = 0;
			for(int j = 0; j < L; j++){
				if(st[i+j]){
					n++;
				}
			}
			num[i] = n;
		}

		check(0, 0);
		String ret = "";
		for(int i = 0; i < N; i++){
			if(a[i] == cnt){
				ret += "+";
			}
			else if(a[i] == 0){
				ret += "-";
			}
			else{
				ret += "?";
			}
		}
		return ret;
	}
	
	void check(int ind, long u){
		if(ind == r.length){
			cnt++;
			int fidx = 0;
			for(int i = 0; i < N; i++){
				if( (u & b << i) > 0){
					
					int st = max(fidx, i);
					int ed = i + L -1;
					for(int j = st; j <= ed; j++){
						a[j]++;
					}
					fidx = i + L;
					
				}
			}
			return;
		}
		
		for(int i = 0; i < N - L + 1; i++){
			if((u & b << i) > 0) continue;
	
			if(num[i] == r[ind]){
				long nu = u;
				nu |= b << i;
				
				check(ind+1, nu);
			}
		}
		

	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CopyOfSurveillanceSystem t = new CopyOfSurveillanceSystem();
		String c = "-XX--X-XX-X-X--X---XX-X---XXXX-----X";
		int[] reports = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		int L = 7;
//		String c = "-X--XX";
//		int[] reports ={1, 2};
//		int L = 3;
		String r = t.getContainerInfo(c, reports, L);
		out.println(r);
	}
}
