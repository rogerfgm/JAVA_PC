package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ2010 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int C = 0;
	static int F = 0;
	public void solve() throws Exception{
		int[][] dt = new int[C][2];
		for(int i = 0; i < C; i++){
			dt[i][0] = sc.nextInt();
			dt[i][1] = sc.nextInt();
		}
		
		Arrays.sort(dt, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		
		int mid = N/2;
		long hicost = 0;
		long lwcost = 0;
		int[] hi = new int[mid];
		int[] lw = new int[C - mid -1];
		for(int i = 0; i < mid; i++){
			hi[i] = dt[i][1]; 
		}
		for(int i = mid+1; i < C; i++){
			lw[i-mid-1] = dt[i][1];
		}
		Arrays.sort(hi);
		Arrays.sort(lw);
		List<Integer> his = new ArrayList<Integer>();
		List<Integer> lws = new ArrayList<Integer>();
	
		for(int i = 0; i < hi.length; i++){
			his.add(hi[i]);
			if(i < mid){
				hicost += hi[i];
			}
		}
		for(int i = 0; i < lw.length; i++){
			lws.add(lw[i]);
			if(i < mid){
				lwcost += lw[i];
			}
		}
		
		int th = mid -1;
		for(int i = mid; i < C-mid; i++){
			long sum = hicost + dt[i][1] + lwcost;
			if(sum <= F){
				out.println(dt[i][0]);
				return;
			}
			if(i == C-mid -1){
				break;
			}
			int ret = add(dt[i][1], his, th);
			hicost += ret;
			ret = del(dt[i+1][1], lws, th);
			lwcost += ret;
		}
		out.println("-1");
	}
	
	int add(int v, List<Integer> l, int th){
		if(l.size() == 0) return 0;
		int idx = find(l, v);
		if(idx > th){
			l.add(idx, v);
			return 0;
		}
		int sub = l.get(th);
		l.add(idx, v);
		return v - sub;
	}
	
	int del(int v, List<Integer> l, int th){
		if(l.size() == 0) return 0;
		int idx = find(l, v);
		if(idx > th){
			l.remove(idx);
			return 0;
		}
		int add = l.get(th+1);
		l.remove(idx);
		return add -v;
	}
	
	int find(List<Integer> l, int v){
		if(l.size() == 0){
			return 0;
		}
		if(l.get(l.size()-1).intValue() < v){
			return l.size();
		}
		if(l.get(l.size()-1).intValue() == v){
			return l.size()-1;
		}
		if(l.get(0).intValue() >= v){
			return 0;
		}
		
		int min = 0;
		int max = l.size()-1;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			int midval = l.get(mid).intValue();
			if(midval == v){
				return mid;
			}
			else if(midval > v){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		return max;
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ2010 p = new POJ2010();

		while(true){
			try{
				N = sc.nextInt();
				C = sc.nextInt();
				F = sc.nextInt();
				if(N == 0 && C == 0 && F == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}

}
