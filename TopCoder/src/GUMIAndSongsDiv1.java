import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class GUMIAndSongsDiv1 {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int maxSongs(int[] ds, int[] ts, int T){
		int ans = 0;
		N = ds.length;
		C[] cs = new C[N];
		for(int i = 0; i < N; i++){
			C c = new C();
			c.d = ds[i];
			c.t = ts[i];
			cs[i] = c;
		}
		Arrays.sort(cs, new Comparator<C>(){

			@Override
			public int compare(C o1, C o2) {
				return o1.t - o2.t;
			}
		});
		
		for(int i = 0; i < N; i++){
			
			for(int j = i; j < N; j++){
				int sum = 0;
				for(int k = i; k <= j; k++){
					sum += cs[k].d;
					if(k != i){
						sum += cs[k].t - cs[k-1].t;
					}
				}
				if(sum <= T){
					int num = j - i + 1;
					
					ans = max(ans, j-i+1);
					continue;
				}
				List<C> list = new ArrayList<C>();
				for(int k = i+1; k < j; k++){
					list.add(cs[k]);
				}
				if(list.size() == 0){
					continue;
				}
				Collections.sort(list, new Comparator<C>(){
					@Override
					public int compare(C o1, C o2) {
						return o2.d - o1.d;
					}
				});
				while(sum > T && list.size() > 0){
					C c = list.remove(0);
					sum -= c.d;
				}
				if(sum <= T){
					int num = 2 + list.size();
					ans = max(num, ans);
				}
			}
		}
		
		
		return ans;
	}
	
	class C{
		public int d = 0;
		public int t = 0;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GUMIAndSongsDiv1 t = new GUMIAndSongsDiv1();
		int[] a1 = {87,21,20,73,97,57,12,80,86,97,98,85,41,12,89,15,41,17,68,37,21,1,9,65,4,
				 67,38,91,46,82,7,98,21,70,99,41,21,65,11,1,8,12,77,62,52,69,56,33,98,97};
		int[] a2 = {88,27,89,2,96,32,4,93,89,50,58,70,15,48,31,2,27,20,31,3,23,86,69,12,59,
				 61,85,67,77,34,29,3,75,42,50,37,56,45,51,68,89,17,4,47,9,14,29,59,43,3};
		int T = 212;
		int r = t.maxSongs(a1, a2, T);
		out.println(r);
	}

}
