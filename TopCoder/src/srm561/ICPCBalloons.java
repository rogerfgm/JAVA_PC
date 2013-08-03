package srm561;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

// 解説のように変更した
public class ICPCBalloons {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;

	Comparator<Integer> comp = new Comparator<Integer>() {
		
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2) * -1;
		}
	};
	
	int[] mx = null;
	int ans = 0;
	int baL = 0;
	int baM = 0;
	List<Integer> lc = new ArrayList<Integer>();
	List<Integer> mc = new ArrayList<Integer>();
	public int minRepaintings(int[] cs, String S, int[] mx) {
		N = mx.length;

		int[] tmp = new int[mx.length];
		Arrays.sort(mx);
		for(int i = 0; i < mx.length; i++){
			tmp[i] = mx[mx.length - 1 - i];
		}
		this.mx = tmp;
		lc = new ArrayList<Integer>();
		mc = new ArrayList<Integer>();
		baL = 0;
		baM = 0;
		for(int i = 0; i < S.length(); i++){
			if(S.charAt(i) == 'L'){
				lc.add(cs[i]);
				baL += cs[i];
			}
			else{
				mc.add(cs[i]);
				baM += cs[i];
			}
		}
		
		Collections.sort(lc, comp);
		Collections.sort(mc, comp);
		ans = INF;
		check(0, 0, 0);
		if(ans >= INF){
			return -1;
		}
		
		return ans;
	}
	
	void check(int idx, int l, int m){
		if(idx == N){
			int suml = 0;
			int summ = 0;
			for(int i = 0; i < N; i++){
				if( (l & 1 << i) > 0){
					suml += mx[i];
				}
				if( (m & 1 << i) > 0){
					summ += mx[i];
				}
			}
			if(suml > baL || summ > baM){
				return;
			}
			int lidx = 0;
			int midx = 0;
			int cost = 0;
			for(int i = 0; i < N; i++){
				if( (l & 1 << i) > 0){
					if(lidx >= lc.size()){
						cost += mx[i];
					}
					else{
						if(mx[i] > lc.get(lidx)){
							cost += mx[i] - lc.get(lidx);
						}
					}
					lidx++;
				}
				if( (m & 1 << i) > 0){
					if(midx >= mc.size()){
						cost += mx[i];
					}
					else{
						if(mx[i] > mc.get(midx)){
							cost += mx[i] - mc.get(midx);
						}
					}
					midx++;
				}
			}
			ans = min(ans, cost);
			
			return;
		}
		int nl = l | 1 << idx;
		check(idx+1, nl, m);
		int nm = m | 1 << idx;
		check(idx+1, l, nm);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ICPCBalloons t = new ICPCBalloons();
		int[] cs =   {1,18,4,7,19,7,7,1,4,8,10,5,14,13,8,22,6,3,13,5,3,4,2,1,3,15,19,4,5,9,4,11,2,7,12,20,11,26,22,7,2,10,9,20,13,20,2,9,11,9};
		String S = "LLMLLLLMLLLLLLLLLLLLMLLLLLLLLLLMMLMLLLMLLLLLLLLMLL";
		int[] mx = {44,59,29,53,16,23,13,14,29,42,13,15,66,4,47};

//		int[] cs =   {46, 57, 33, 47, 52, 30, 30, 62, 51, 9, 22, 60, 46, 2, 18, 37, 28, 58};
//		String S ="LLLLLLMLLLLLLLLLLL";
//		int[] mx = {68, 61, 21, 83, 90, 58, 58, 15, 69, 24, 7, 35, 11, 23, 63};
		
		int ret = t.minRepaintings(cs, S, mx);
		
		out.println(ret);
		/*
		 Problem: 250
Test Case: 81
Succeeded: No
Execution Time: 4 ms
Args:
{{46, 57, 33, 47, 52, 30, 30, 62, 51, 9, 22, 60, 46, 2, 18, 37, 28, 58}, 
"LLLLLLMLLLLLLLLLLL",
 {68, 61, 21, 83, 90, 58, 58, 15, 69, 24, 7, 35, 11, 23, 63}}

Expected:
124

Received:
150
		 */
	}

}
