package srm571;
import java.util.*;
public class MagicMoleculeEasy {

	boolean[][] cn = null;
	int[] mp = null;

	boolean[] used = null;
	List<int[]> edges = null;
	int k = 0;
	int K = 0;
	int ans = -1;
	Data[] ds = null;
	int n = 0;
	public int maxMagicPower(int[] magicPower, String[] magicBond, int K){
		this.K = K;
		mp = magicPower;
		ds = new Data[mp.length];
		for(int i = 0; i < mp.length; i++){
			Data d = new Data();
			d.mp = mp[i];
			d.idx = i;
			ds[i] = d;
		}
		Arrays.sort(ds, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o2.mp - o1.mp;
			}
		});
		
		n = mp.length;
		edges = new ArrayList<int[]>();
		used = new boolean[n];
		for(int i = 0; i < n-1; i++){
			String s = magicBond[i];
			for(int j = i+1; j < n; j++){
				if(s.charAt(j) == 'Y'){
					int[] e = new int[2];
					e[0] = i;
					e[1] = j;
					edges.add(e);
				}
			}
		}
		check(0, 0, 0);
		
		return ans;
	}
	
	void check(int idx, int k, int sum){
		if(k > K){
			return;
		}

		if(idx == edges.size()){
			if(k < K){
				for(int i = 0; i < ds.length && k < K; i++){
					if(!used[ds[i].idx]){
						sum += ds[i].mp;
						k++;
					}
				}
			}
			ans = Math.max(ans, sum);
			return;
		}
		int[] e = edges.get(idx);
		int p1 = e[0];
		int p2 = e[1];
		if(used[p1] || used[p2]){
			check(idx+1, k, sum);
		}
		else{
			if(!used[p1]){
				used[p1] = true;
				check(idx+1, k+1, sum + mp[p1]);
				used[p1] = false;
			}
			if(!used[p2]){
				used[p2] = true;
				check(idx+1, k+1, sum + mp[p2]);
				used[p2] = false;
			}
		}
		
		
		
	}
	
	class Data{
		int mp = 0;
		int idx = 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicMoleculeEasy e = new MagicMoleculeEasy();
		int[] m = {4, 7, 5, 8};
		String[] s = {"NYNY", "YNYN", "NYNY", "YNYN"};
		int ret = e.maxMagicPower(m, s, 2);
		System.out.println(ret);
	}

}
