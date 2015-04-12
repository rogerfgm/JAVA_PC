import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TaroCoins {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	long MR = Long.parseLong("2000000000");
	int N = 0;
	long b = 1;
	Object[] maps = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	long[] ds = null;
	long[] sums = null;
	public long getNumber(long T){
		
		
		List<Long> list = new ArrayList<Long>();
		long m = 1;
		list.add(m);
		while(true){
			m *= 2;
			if(m <= T){
				list.add(m);
			}
			else{
				break;
			}
		}
		N = list.size();
		maps = new Object[N];
		for(int i = 0; i < N; i++){
			maps[i] = new HashMap<Long, Long>();
		}
		ds = new long[N];
		for(int i = 0; i < N; i++){
			ds[i] = list.get(i);
		}
		sums = new long[N];
		sums[0] = 2;
		for(int i = 1; i < N; i++){
			sums[i] = sums[i-1] + ds[i] * 2;
		}
		return get(N-1, T);
	}
	
	long get(int idx, long t){
		if(idx == 0 && t <= 2){
			return 1;
		}
		Map<Long, Long> map = (HashMap<Long, Long>)maps[idx];
		if(map.containsKey(t)){
			return map.get(t);
		}
		long ans = 0;
		if(t >= ds[idx] * 2){
			long nt = t - ds[idx] * 2;
			if(nt <= sums[idx-1]){
				ans += get(idx-1, nt);
			}
		}
		if(t >= ds[idx]){
			long nt = t - ds[idx];
			if(nt <= sums[idx-1]){
				ans += get(idx-1, nt);
			}
		}
		if(t <= sums[idx-1]){
			ans += get(idx-1, t);
		}
		map.put(t, ans);
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaroCoins t = new TaroCoins();
		long r = t.getNumber(Long.parseLong("1000000000000000000"));
		//long r = t.getNumber(Long.parseLong("1000000000"));
		out.println(r);
	}

}
