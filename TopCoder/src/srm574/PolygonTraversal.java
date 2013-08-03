package srm574;
import java.util.*;
import static java.lang.Math.*;
import java.io.*;

// Accept 05/10/2013
public class PolygonTraversal {

	long[][] dp = null;
	int N = 0;
	int[] ps = null;
	public long count(int n, int[] ps){
		N = n;
		this.ps = ps;
		dp = new long[N][1 << N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 1 << N; j++){
				dp[i][j] = -1;
			}
		}
		for(int i = 0; i < ps.length; i++){
			ps[i]--;
		}
		int p = 0;
		for(int i = 0; i < ps.length; i++){
			p |= 1 << (ps[i]);
		}
		return check(ps[ps.length-1], p);
		
	}
	
	long check(int idx, int p){
		if(p == (1 << N) -1){
			if( idx != (ps[0] + 1) % N && idx != ( ps[0] - 1 + N) % N  ){
				return 1;	
			}
			else{
				return 0;
			}
		}
		if(dp[idx][p] >= 0){
			return dp[idx][p];
		}
		long ret = 0;
		
		for(int i = 0; i < N; i++){
			if(  (p & (1 << i)) == 0){
				boolean f1 = false;
				boolean f2 = false;
				int j = (i+1)%N;
				while(j != idx){
					if( (p & (1 << j)) > 0){
						f1 = true;
						break;
					}
					j++;
					j%=N;
				}
				j = (idx+1)%N;
				while(j != i){
					if( (p & (1 << j)) > 0){
						f2 = true;
						break;
					}
					j++;
					j%=N;
				}
				if(f1 && f2){
					int np = p | (1 << i);
					ret += check(i, np);
				}
				
			}
		}
		
		
		dp[idx][p] = ret;
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 7;
		int[] ps = {2, 4, 7};
		PolygonTraversal p = new PolygonTraversal();
		long ret = p.count(N, ps);
		System.out.println(ret);
	}

}
