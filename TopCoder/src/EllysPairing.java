import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class EllysPairing {

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

		N = ct.length;
		long total = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < N; i++){
			long cur = ft[i];
			for(int j = 0; j < ct[i]; j++){
				int c = (int)cur;
				if(!map.containsKey(c)){
					map.put(c, 1);
				}
				else{
					int cnt = map.get(c) + 1;
					map.put(c, cnt);
				}
				cur = (cur * ml[i] + ad[i]) % M;
			}
			total += ct[i];
		}
		System.out.println("test");
		long mx = 0;
		for(int key : map.keySet()){
			if(mx < map.get(key).intValue()){
				mx = map.get(key);
			}
		}
		long other = total - mx;
		if(other >= mx){
			return (int)(total / 2);
		}
		else{
			return (int)other;
		}
		
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EllysPairing t = new EllysPairing();
		int M = 1073741824;
		int[] ct = {894602, 946569, 887230, 856152, 962583, 949356, 904847, 829100, 842183, 958440,
				 811081, 864078, 809209, 938727, 949135, 892809, 816528, 961237, 979142, 890922};
		int[] ft ={844085078, 898937259, 243490172, 887804102, 187696417, 156820442, 237600210, 618812924, 153000239, 912364033,
				 254936966, 650298774, 982988140, 649258331, 566444626, 201481721, 492943390, 1061953081, 492672963, 960519711};
		int[] ml ={1036482037, 583219072, 819168094, 253755052, 548208982, 401830167, 638626082, 43642932, 123607749, 485521178,
				 860368129, 30334704, 219771462, 733375600, 130839219, 415503960, 294132484, 1044831462, 256910484, 198852170};
		int[] ad ={889169006, 604831366, 967292994, 980686280, 844875791, 1027687492, 357734882, 295879743, 48284748, 421729100,
				 1049536313, 327207332, 948053446, 271229570, 664579359, 795815285, 842856528, 876662975, 675611938, 634229925};
		int r = t.getMax(M, ct, ft, ml, ad);
		out.println(r);
	}

}
