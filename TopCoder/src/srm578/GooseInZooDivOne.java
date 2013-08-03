package srm578;
import java.util.*;
import static java.lang.Math.*;

public class GooseInZooDivOne {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int MOD =  1000000007;
	
	int[] dt = null;
	int[][] dp = null;
	int N = 0;
	int d = 0;
	boolean[][] used = null;
	List<Integer> getl(String[] fid, int d){
		this.d = d;
		List<Integer> list = new ArrayList<Integer>();
		boolean[][] f = new boolean[fid.length][fid[0].length()];
		used = new boolean[fid.length][fid[0].length()];
		
		for(int i = 0; i < fid.length; i++){
			String s = fid[i];
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == 'v'){
					f[i][j] = true;
				}
			}
		}
	
		
		
		for(int i = 0; i < f.length; i++){
			
			for(int j = 0; j < f[0].length; j++){

				if(f[i][j] && !used[i][j]){
					int ret = dfs(f, i, j, d);
					list.add(ret);
				}
			}
		}
		
		return list;
	}
	
	int dfs(boolean[][] f, int y, int x, int rem){
		if(used[y][x]) return 0;
		int ret = 0;
	
		if(!used[y][x] && f[y][x]){
			f[y][x] = false;
			used[y][x] = true;
			ret++;
			rem = d;
		}
		if(rem == 0){
			return ret;
		}
		if(x < f[0].length -1){
			ret += dfs(f, y, x+1, rem-1);
		}
		if(y < f.length -1){
			ret += dfs(f, y+1, x, rem-1);
		}
		if(x > 0){
			ret += dfs(f, y, x-1, rem-1);
		}
		if(y > 0){
			ret += dfs(f, y-1, x, rem-1);
		}
		return ret;
	}
	
	public int count(String[] fid, int d){
		long ret = 0;

		List<Integer> set = getl(fid, d);
//		int tmp = 0;
//		for(int i : set){
//			tmp += i;
//		}
//		System.out.println(tmp);
		
		dt = new int[set.size()];
		for(int i = 0; i < set.size(); i++){
			dt[i] = set.get(i);
		}
		N = set.size();
		dp = new int[N][2];
		for(int i = 0; i < N; i++){
			dp[i][0] = dp[i][1] = -1;
		}
		ret = get(0, 0);
		ret-= 1;
		return (int)ret;
	}
	
	int get(int idx, int ev){
		long ret = 0;
		if(idx == N){
			if(ev == 0){
				return 1;
			}
			else{
				return 0;
			}
		}
		if(dp[idx][ev] >= 0){
			return dp[idx][ev];
		}
		
		if(dt[idx] % 2 == 0){
			ret += (long)get(idx+1, ev) * (long)2;
		}
		else{
			ret += (long)get(idx+1, 0) + (long)get(idx+1, 1);
		}
		ret %= MOD;
		dp[idx][ev] = (int)ret;
		
		return dp[idx][ev];
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GooseInZooDivOne t = new GooseInZooDivOne();
		String[] f = {"vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvv.vvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"};
		int dist = 100;
		
//		String[] f = {".v..v.v........", "..v....vvv.v.v.", "...v.v.v.......", "....v.v.....v..", ".v.vv....v....v", "........v......", "..v....vvv.v...", "........v......", "............v.v", "...v.....vv.v..", ".......vv.v..v.", "v...vv..v....vv", ".v....v...v..v.", ".....v.....v.v.", "v.v....v......v"};
//		int dist = 3;
		int ret = t.count(f, dist);
		
		System.out.println(ret);
	}

}
