package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// ノーヒントでとけず分類を見てしまった…。
//そしてTLE...
public class POJ3662 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int P = 0;
	static int K = 0;
	
	List<List<Edge>> G = null;
	boolean[] used = null;
	public void solve() throws Exception{
		G = new ArrayList<List<Edge>>();
		
		for(int i = 0; i <= N; i++){
			List<Edge> l = new ArrayList<Edge>();
			G.add(l);
		}
		
		for(int i = 0; i < P; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			int l = sc.nextInt();
			Edge e = new Edge(t, l);
			
			G.get(f).add(e);
			e = new Edge(f, l);
			G.get(t).add(e);
		}
		used = new boolean[N+1];
		int cnt  = cnt(1);
		if(cnt <= K){
			out.println("0");
			return;
		}
		else if(cnt >= INF){
			out.println("-1");
			return;
		}
		
		int min = 0;
		int max = 1000000;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			if(check(mid)){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		out.println(max);
	}
	
	boolean check(int mid){
		
		int[] mincnt = new int[N+1];
		for(int i = 1; i <= N; i++){
			mincnt[i] = INF;
		}
		List<Data> list = new ArrayList<Data>();
		Data data = new Data(0, 1);
		list.add(data);
		while(list.size() > 0){
			Data dt = list.remove(0);
			if(dt.p == N){
				return true;
			}
			if(dt.cnt >= mincnt[dt.p]){
				continue;
			}
			mincnt[dt.p] = dt.cnt;
			
			for(Edge e : G.get(dt.p)){
				int cnt = dt.cnt;
				if(e.len > mid){
					cnt++;
					if(cnt > K){
						continue;
					}
				}
				if(cnt >= mincnt[e.t])continue; 
				Data d = new Data(cnt, e.t);
				list.add(d);
			}
		}
		
		return false;
	}

	
	int cnt(int p){
		if(p == N){
			return 0;
		}
		used[p] = true;
		int cnt = INF;
		for(Edge e : G.get(p)){
			if(used[e.t])continue; 
			cnt = min(cnt, 1 + cnt(e.t));
		}
		
		return cnt;
	}
	
	
	class Edge{
		public Edge(int t, int len){
			this.t = t;
			this.len = len;
		}
		int t = 0;
		int len = 0;
	}
	
	class Data{
		public Data(int cnt, int p){
			this.cnt = cnt;
			this.p = p;
		}
		int cnt = 0;
		int p = 0;
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
		POJ3662 p = new POJ3662();
	
		while(true){
			try{
				N = sc.nextInt();
				P = sc.nextInt();
				K = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && P == 0 && K == 0) break;
			p.solve();
		}
	}

}
