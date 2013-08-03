import java.util.*;

// POJ2914
public class MinimumCut {
	public List<List<Edge>> G = null;
	
	int INF = Integer.MAX_VALUE / 10;
	
	int N = 0;
	public MinimumCut(int n){
		N = n;
		init();
	}
	
	void init(){
		G = new ArrayList<List<Edge>>();
		for(int i = 0; i < N; i++){
			List<Edge> l = new ArrayList<Edge>();
			G.add(l);
		}
	}
	
	void add_edge(int f, int t, int c){
		Edge e = new Edge(t, c);
		G.get(f).add(e);
	}
	
	int minimumCut(){
		
		int[][] h = new int[N][N];
	
		List<Integer> V = new ArrayList<Integer>();
		for(int i = 0; i < N; i++){
			for(Edge e : G.get(i)){
				h[i][e.to]+= e.weight; 
			}
			V.add(i);
		}
		
		int cut = INF;
		for(int m = N; m > 1; m--){
			int[] ws = new int[m];
			int u, v;
			int w;
			u = v = w = 0;
			for(int k = 0; k < m; k++){
				u = v; v = max_element(ws) - 0;
				w = ws[v]; ws[v] = -1;
				for(int i = 0; i < m; i++) if(ws[i] >= 0) ws[i] += h[V.get(v)][V.get(i)];
			}
			for(int i = 0; i < m; i++){
				h[V.get(i)][V.get(u)] += h[V.get(i)][V.get(v)];
				h[V.get(u)][V.get(i)] += h[V.get(v)][V.get(i)];
			}
			V.remove(v);
			cut = Math.min(cut, w);
		}
		
		return cut;
	}
	
	int max_element(int[] ws){
		int max = -INF;
		int idx = 0;
		for(int i = 0; i < ws.length; i++){
			if(ws[i] > max){
				max = ws[i];
				idx = i;
			}
		}
		return idx;
	}
	
	
	public class Edge{
		public Edge( int t, int c){
			
			to = t;
			weight = c;
		}
		int to = 0;
		int weight = 0;
	}
}
