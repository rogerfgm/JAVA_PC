package srm571;
import java.util.*;

public class MagicMolecule {


	int[] mp = null;
	List<int[]> edges = new ArrayList<int[]>();
	int n = 0;
	int maxk = 0;
	boolean[] used = null;
	int INF = Integer.MAX_VALUE / 10;
	int ans = -1;
	boolean[][] cn = null;
	public int maxMagicPower(int[] magicPower, String[] mB){
		mp = magicPower;
		n = mp.length;
		cn = new boolean[n][n];
		for(int i = 0; i < n; i++){
			String s = mB[i];
			for(int j = 0; j < n; j++){
				if(s.charAt(j) == 'Y'){
					cn[i][j] = cn[j][i] = true;
				}
			}
		}
		
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				if(!cn[i][j]){
					int[] e = new int[2];
					e[0] = i;
					e[1] = j;
					edges.add(e);
				}
			}
		}
		int minm = 2 * n / 3;
		if(2 * n % 3 > 0){
			minm++;
		}
		maxk = n-minm;
		used = new boolean[n];
		check(0, 0);
		
		
		return ans;
	}
	
	void check(int idx, int k){
		if(k > maxk){
			return;
		}
		if(idx == edges.size()){
		
			for(int i = 0; i < n-1; i++){
				if(used[i]){
					continue;
				}
				for(int j = i+1; j < n; j++){
					if(used[j]){
						continue;
					}
					if(!cn[i][j]){
						return;
					}
				}
			}
			int sum = 0;
			for(int i = 0; i < n; i++){
				if(!used[i]){
					sum += mp[i];
				}
			}
			ans = Math.max(ans, sum);
			return;
		}
		int[] e = edges.get(idx);
		if(used[e[0]] || used[e[1]]){
			check(idx+1, k);
		}
		else{
			if(!used[e[0]]){
				used[e[0]] = true;
				check(idx+1, k+1);
				used[e[0]] = false;
			}
			if(!used[e[1]]){
				used[e[1]] = true;
				check(idx+1, k+1);
				used[e[1]] = false;
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicMolecule m = new MagicMolecule();
		int[] in ={3969,9430,7242,8549,8190,8368,3704,9740,1691};
		String[] sn ={"NYYYYYYYY","YNYYYYYYY","YYNYYYYYY","YYYNYYYYY","YYYYNYYYY","YYYYYNYYY","YYYYYYNNY","YYYYYYNNY","YYYYYYYYN"};
		int ret = m.maxMagicPower(in, sn);
		System.out.println(ret);
	}

}
