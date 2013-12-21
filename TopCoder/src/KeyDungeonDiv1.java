import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class KeyDungeonDiv1 {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[] dp = null;
	int[] dR = null;
	int[] dG = null;
	int[] rR = null;
	int[] rG = null;
	int[] rW = null;
	int ans = 0;
	public int maxKeys(int[] doorR, int[] doorG, int[] roomR, int[] roomG, int[] roomW, int[] keys){
		dR = doorR;
		dG = doorG;
		rR = roomR;
		rG = roomG;
		rW = roomW;
		N = doorR.length;
		ans = 0;
		dp = new int[1<<12];
		for(int i = 0; i < 1 << 12; i++){
			dp[i] = -1;
		}
		
		check(0, keys[0], keys[1], keys[2]);
		
		return ans;
	}
	
	void check(int st, int r, int g, int w){

		if(dp[st] >= w){
			return;
		}
		dp[st] = w;
		ans = max(ans, r + g + w);
		for(int i = 0; i < N; i++){
			if( ((1 << i) & st) == 0){
				int nw = w;
				int nr = r - dR[i];
				if(nr < 0){
					nw += nr;
					nr = 0;
				}
				int ng = g - dG[i];
				if(ng < 0){
					nw += ng;
					ng = 0;
				}
				if(nw < 0 ){
					continue;
				}
				nr += rR[i];
				ng += rG[i];
				nw += rW[i];
				
				int nst = st | (1 << i);
				check(nst, nr, ng, nw);
				
			}
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KeyDungeonDiv1 t = new KeyDungeonDiv1();
		int[] i1 = {9,5,10,8,4,3,0,8,4,1,3,9};
		int[] i2 = {9,10,0,8,9,4,3,8,1,8,10,4};
		int[] i3 = {1,2,0,2,3,3,5,3,1,3,0,5};
		int[] i4 = {5,2,5,0,5,2,3,4,0,0,5,2};
		int[] i5 = {1,5,1,2,0,4,4,0,3,3,1,3};
		int[] i6 = {5,0,1};
		int r = t.maxKeys(i1, i2, i3, i4, i5, i6 );
		out.println(r);
	}

}
