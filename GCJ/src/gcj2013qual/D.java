package gcj2013qual;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class D {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int N = 0;
	int[] type = null;
	List<List<Integer>> list = null;
	Set<String> dp = null;
	public void solve() throws Exception{
		dp = new HashSet<String>();
		int K = sc.nextInt();
		N = sc.nextInt();
		int[] ks = new int[K];
		List<Integer> mykey = new ArrayList<Integer>();
		for(int i = 0; i < K; i++){
			ks[i] = sc.nextInt();
			mykey.add(ks[i]);
		}
		
		type = new int[N];
		list = new ArrayList<List<Integer>>();
		for(int i = 0; i < N; i++){
			type[i] = sc.nextInt();
			List<Integer> l = new ArrayList<Integer>();
			int k = sc.nextInt();
			for(int j = 0; j < k; j++){
				int key = sc.nextInt();
				l.add(key);
			}
			list.add(l);
		}
		
		boolean[] used = new boolean[N];
		List<Integer> ret = check(mykey, used);
		if(ret != null){
			for(int i : ret){
				out.print(" " + i);
			}
			out.println();
		}
		else{
			out.println(" IMPOSSIBLE");
		}
	}
	
	List<Integer> check(List<Integer> mykey, boolean[] used){
		boolean suc = true;
		for(int i = 0; i < N; i++){
			if(!used[i]) suc = false;
		}
		if(suc){
			return new ArrayList<Integer>();
		}
		String dps = getdpkey(mykey, used);
		if(dp.contains(dps)){
			return null;
		}
		dp.add(dps);

		
		for(int i = 0; i < N; i++){
			if(used[i]) continue;
			for(int j = 0; j < mykey.size(); j++){
				int key = mykey.get(j);

				if(type[i] == key){
					List<Integer> nmk = new ArrayList<Integer>();
					nmk.addAll(mykey);
					nmk.remove(j);
					nmk.addAll(list.get(i));
					
					boolean[] nused = Arrays.copyOf(used, used.length);
					nused[i] = true;
					List<Integer> ret = check(nmk, nused);
					if(ret != null){
						ret.add(0, i+1);
						return ret;
					}
				}
			}
		}
		
		return null;
	}
	
	String getdpkey(List<Integer> mykey, boolean[] used){
		StringBuilder sb = new StringBuilder();
		int[] ks = new int[mykey.size()];
		for(int i = 0; i < ks.length; i++){
			ks[i] = mykey.get(i);
		}
		Arrays.sort(ks);
		for(int i = 0; i < ks.length; i++){
			sb.append(ks[i] + " ");
		}
		sb.append("::");
		for(int i = 0; i < N; i++){
			if(used[i]){
				sb.append("f");
			}
			else{
				sb.append("b");
			}
		}
		return sb.toString();
	}
	
	List<Integer> copy(List<Integer> b, int key){
		List<Integer> list = new ArrayList<Integer>();
		boolean del = false;
		for(int i : b){
			if(!del && i == key){
				del = true;
			}
			else{
				list.add(i);
			}
		}
		return list;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("D-small-attempt3.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		D b = new D();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ":");
			b.solve();
			t++;
		}
		out.close();
	}
}
