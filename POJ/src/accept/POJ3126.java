package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 最初に4桁の素数を求めて、1つ違いのものにコスト１の辺があるとして最短路
public class POJ3126 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	public void solve() throws Exception{
		int max = 10000;
		boolean[] f = new boolean[max];
		
		List<Integer> ps = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 2; i <= 9999; i++){
			if(!f[i]){
				if(i >= 1000) {
					ps.add(i);
					map.put(i, ps.size()-1);
				}
				
				for(int j = 1;; j++){
					if(i * j >= max) break;
					f[i*j] = true;
				}
			}
		}
		List<List<Integer>> es = new ArrayList<List<Integer>>(); 
		for(int i = 0; i< ps.size(); i++){
			List<Integer> l = new ArrayList<Integer>();
			es.add(l);
		}
		
		M = ps.size();
		
		for(int i = 0; i < ps.size(); i++){
			int n = ps.get(i);
			for(int j = 0; j < 4; j++){
				int dv = (int)pow(10, j);
				int t = (n / dv) % 10;;
				for(int k = 0; k < 10; k++){
					if(k == t) continue;
					int nn = n / (dv*10) * (dv*10) + k * dv + n % dv;
					if(map.containsKey(nn)){
						int idx = map.get(n);
						int nidx = map.get(nn);
						es.get(idx).add(nidx);
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++){
			int s = sc.nextInt();
			int t = sc.nextInt();
			int sidx = map.get(s);
			int tidx = map.get(t);
		
			List<Edge> list = new ArrayList<Edge>();
			list.add(new Edge(sidx, 0));
			boolean[] used = new boolean[M];
			
			while(true){
				Edge e = list.remove(0);
				used[e.t]= true; 
				if(e.t == tidx){
					out.println(e.c);
					break;
				}
				for(int nt : es.get(e.t)){
					if(used[nt]) continue;
					Edge ne = new Edge(nt, e.c + 1);
					list.add(ne);
				}
			}
			
		}
		
		
	}

	class Edge{
		public Edge(int t, int c){
			this.t= t;
			this.c = c;
		}
		int t = 0;
		int c = 0;
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
		POJ3126 p = new POJ3126();
		while(true){
			
			try{
				N = sc.nextInt();
				if(N == 0){
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
