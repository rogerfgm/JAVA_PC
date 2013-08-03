import java.util.*;

// Verified POJ2186
public class SCC {
	int MAXV = 0;
	List<List<Integer>> G = null;
	List<List<Integer>> rG = null;
	List<Integer> vs; 
	boolean[] used = null;
	int[] cmp = null;
	
	public SCC(int maxv){
		MAXV = maxv;
		init();
	}
	void init(){
		G = new ArrayList<List<Integer>>();
		rG = new ArrayList<List<Integer>>();
		for(int i = 0; i < MAXV; i++){
			List<Integer> list = new ArrayList<Integer>();
			G.add(list);
			list = new ArrayList<Integer>();
			rG.add(list);
		}
		vs = new ArrayList<Integer>();
		used = new boolean[MAXV];
		cmp = new int[MAXV];
	}
	
	public void add_edge(int from, int to){
		G.get(from).add(to);
		rG.get(to).add(from);
	}
	
	void dfs(int v){
		used[v] = true;
		for(int i = 0; i < G.get(v).size(); i++){
			if (!used[G.get(v).get(i)]) dfs(G.get(v).get(i));
		}
		vs.add(v);
	}
	
	void rdfs(int v, int k){
		used[v] = true;
		cmp[v] = k;
		for(int i = 0; i < rG.get(v).size(); i++){
			if(!used[rG.get(v).get(i)]) rdfs(rG.get(v).get(i), k);
		}
	}
	
	public int scc(){
		used = new boolean[MAXV];
		vs = new ArrayList<Integer>();
		for(int v = 0; v < MAXV; v++){
			if(!used[v]) dfs(v);
		}
		used = new boolean[MAXV];
		int k = 0;
		for(int i = vs.size()-1; i >= 0; i--){
			if(!used[vs.get(i)]) rdfs(vs.get(i), k++);
		}
		return k;
	}
}
