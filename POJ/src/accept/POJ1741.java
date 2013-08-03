package accept;


import java.util.*;
import java.io.*;


public class POJ1741 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	static int k = 0;
	
	List<List<Edge>> G = null;
	
	int[] subtree_size = null;
	boolean[] centroid = null;

	int ans = 0;
	
	class Edge{
		public Edge(int t, int l){
			this.t = t;
			this.l = l;
		}
		int t = 0;
		int l = 0;
	}
	
	public void solve() throws Exception{
		ans = 0;
		G =  new ArrayList<List<Edge>>();
		for(int i = 0; i <= n; i++){
			List<Edge> l = new ArrayList<Edge>();
			G.add(l);
		}
		subtree_size = new int[n+1];
		centroid = new boolean[n+1];
		for(int i = 0; i < n-1; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			int l = sc.nextInt();
			Edge e = new Edge(t, l);
			G.get(f).add(e);
			e = new Edge(f, l);
			G.get(t).add(e);
		}
		
		solve_subproblem(1);
		System.out.println(ans);
		
	}
	
	// 部分木のサイズ（subtree_size)を計算する再帰関数
	int compute_subtree_size(int v, int p){
		int c = 1;
		for(Edge e : G.get(v)){
			if(e.t != p && !centroid[e.t]){
				c += compute_subtree_size(e.t, v);
			}
		}
		subtree_size[v] = c;
		
		return c;
	}
	
	//重心となる頂点を探す再帰関数。tは連結成分全体の大きさ
	// v以下で、削除により残る最大の部分木の頂点数が最小となる頂点の、
	//(残る最大の部分木の頂点数、頂点番号）を返す
	int[] search_centroid(int v, int p, int t){
		int[] res = new int[2];
		res[0] = INF;
		res[1] = -1;
		int s = 1, m = 0;
		for(int i = 0; i < G.get(v).size(); i++){
			Edge e = G.get(v).get(i);
			if(e.t != p && !centroid[e.t]){
				int[] ret = search_centroid(e.t, v, t);
				if(ret[0] < res[0]){
					res = ret;
				}
				m = Math.max(m, subtree_size[e.t]);
				s += subtree_size[e.t];
			}
		}
		
		m = Math.max(m, t - s);
		if(m < res[0]){
			res = new int[2];
			res[0] = m;
			res[1] = v;
		}
		
		return res;
	}
	
	// 部分木に含まれる全頂点の重心までの距離を列挙する再帰関数
	void enumerate_paths(int v, int p, int d, List<Integer> ds){
		ds.add(d);
		for(Edge e : G.get(v)){
			if(e.t == p || centroid[e.t])continue;
			if(d + e.l <= k){
				enumerate_paths(e.t, v, d + e.l, ds);
			}
		}
	}
	
	// 和がk以下となる組の数を求める
	int count_paires(List<Integer> ds){
		int res = 0;
		Integer[] da = new Integer[ds.size()];
		da = ds.toArray(da);
		Arrays.sort(da);
		int j = da.length-1;
		for(int i = 0; i < ds.size(); i++){
			while(j >= 0 && da[i] + da[j] > k){
				j--;
			}
			res += j+1;
			if(j >= i) res--;
		}
		
		return res / 2;
	}
	
	// 頂点vが属する部分着に関して、重心を探し木を分割し問題を解く再帰関数
	void solve_subproblem(int v){
		compute_subtree_size(v, -1);
		int s = search_centroid(v, -1, subtree_size[v])[1];
		
		centroid[s] = true;
		
		for(Edge e : G.get(s)){
			if(centroid[e.t])continue;
			solve_subproblem(e.t);
		}
		
		
		List<Integer> ds = new ArrayList<Integer>();
		ds.add(0);
		for(Edge e : G.get(s)){
			if(centroid[e.t]) continue;
			if(e.l <= k){
				List<Integer> nds = new ArrayList<Integer>();
				enumerate_paths(e.t, s, e.l, nds);
				ans -= count_paires(nds);
				ds.addAll(nds);
			}
		}
		
		ans += count_paires(ds);

		centroid[s] = false;
	}

	

    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1741 t = new POJ1741();
		
		int idx = 1;
		while(true){
			n = sc.nextInt();
			k = sc.nextInt();
			if(n == 0 && k == 0){
				break;
			}
			//System.out.print("Instance #" + idx + ": ");
			t.solve();
			idx++;
		}
	}
	

}