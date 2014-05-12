package srm618;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class Family {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	String pos = "Possible";
	String imp = "Impossible";
	public String isFamily(int[] p1, int[] p2){
		N = p1.length;
		int[] m = new int[N];
		for(int i = N-1; i >= 0; i--){
			if(p1[i] == -1 && p2[i] == -1){
				continue;
			}
			else if(p1[i] == -1 || p2[i] == -1){
				return imp;
			}
			if(m[p1[i]] != 0){
				continue;
			}
			m[p1[i]] = 1;
			m[p2[i]] = 2;
			while(true){
				boolean f = false;
				for(int j = N-1; j >= 0; j--){
					if(p1[j] == -1) continue;
					if(m[p1[j]] == 0 && m[p2[j]] != 0){
						f = true;
						if(m[p2[j]] == 1){
							m[p1[j]] = 2;
						}
						else{
							m[p1[j]] = 1;
						}
					}
					else if(m[p1[j]] != 0 && m[p2[j]] == 0){
						f = true;
						if(m[p1[j]] == 1){
							m[p2[j]] = 2;
						}
						else{
							m[p2[j]] = 1;
						}
					}
					else if(m[p1[j]] != 0 && m[p2[j]] != 0 && m[p1[j]] == m[p2[j]]){
						return imp;
					}
				}
				if(!f) break;
			}
		}
		
		return pos;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Family t = new Family();
		int[] p1 = {-1,-1,-1,2,2,-1,5,6,4,6,2,1,8,0,2,4,6,9,-1,16,-1,11};
		int[] p2 ={-1,-1,-1,1,0,-1,1,4,2,0,4,8,2,3,0,5,14,14,-1,7,-1,13};
		String r = t.isFamily(p1, p2);
		out.println(r);
	}

}
