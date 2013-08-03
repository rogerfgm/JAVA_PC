package srm561;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

// 561 500
public class CirclesGame {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int[] x = null;
	int[] y = null;
	int[] r = null;
	
	List<List<Integer>> pars = null;
	int[] dp = null;
	int[] par = null;
	public String whoCanWin(int[] x, int[] y, int[] r){
		this.x = x;
		this.y = y;
		this.r = r;
		N = x.length;


		par = new int[N];
		
		for(int i = 0; i < N; i++){
			par[i] = -1;
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(i == j) continue;
				if(has(j, i)){
					if(par[i] == -1 ){
						par[i] = j;
					}
					else{
						if(r[par[i]] > r[j]){
							par[i] = j;
						}
					}
				}
			}
		}
		
		dp = new int[N];
		for(int i = 0; i < N; i++){
			dp[i] = -1;
		}
		
		

		int ck = 0;
		for(int i = 0; i < N; i++){
			if(par[i] == -1){
				int ret = grundy(i);
				ck ^= grundy(i);
			}
		}

		if(ck != 0){
			return "Alice";
		}
		else{
			return "Bob";
		}
	}

	
	int grundy(int p){
		if(dp[p] >= 0){
			return dp[p];
		}
		Set<Integer> set = new HashSet<Integer>();
		
		//今の状態から一手でいける状態のgrundy数をsetに追加していく
		// たとえば今の状態がxだとしてkをとるとx-iが次の一手でとり得る値
		for(int i = 0; i < N; i++){
			if(has(p, i)){
				int g = 0;
				for(int j = 0; j < N; j++){
					if(j != p && has(p, j) && par[j] != -1 && has(par[j], i) && (!has(j, i))){
						int tmp = grundy(j);
						g ^= grundy(j);
					}
				}
				set.add(g);
			}
		}
		
		// Sに含まれない最小の非負の整数がxのgrundy数
		for(int i = 0; ; i++){
			if(!set.contains(i)){
				dp[p] = i;
				return i;
			}
		}
		
	}
	

	
	boolean has(int i, int j){
		if(i == j) return true;
		if(i == -1) return true;
		if(j == -1) return false;
		if(r[i] <= r[j]){
			return false;
		}
		long xl = x[i] - x[j];
		long yl = y[i] - y[j];
		
		long R = r[i];
		
		long dist = xl*xl + yl*yl;
		if(dist < R*R){
			return true;
		}
		else{
			return false;
		}
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CirclesGame t = new CirclesGame();
		
//		{
//			int[] x = {-2369, 5861, -1563, -4641, 9512, -4449, -8970, 6202, 9535, 3154, -7378, 4116, -4661, -4568, -4468};
//			int[] y ={-9367, 6752, -6551, 9959, -5812, -4222, 4259, -2294, 2301, 1523, 8840, 9993, 8887, 5625, -7657};
//			int[] r =  {9297, 3275, 1483, 207, 2600, 1929, 128, 1519, 968, 2424, 927, 245, 5251, 1336, 6500};
//			String a = t.whoCanWin(x, y, r);
//			out.println(a); // Bob
//		}

//		
		{
			int[] x = {0, 0, 0, 10, 10, 20};
			int[] y =  {0, 0, 0, 0, 0, 0};
			int[] r =  {1, 2, 3, 1, 2, 1};
			String a = t.whoCanWin(x, y, r);
			out.println(a); // Bob
		}

		


	}

}
