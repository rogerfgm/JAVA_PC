import java.util.*;



// Verified POJ3041
public class Matching {

	int MAXV = 0;
	public List<List<Integer>> edgeList = null;
	int[] match = null;
	boolean[] used = null;
	
	public Matching(int maxv){
		MAXV = maxv;
		init();
	}
	
	public void add_edge(int u, int v){
		edgeList.get(u).add(v);
		edgeList.get(v).add(u);
	}
	
	void init(){
		edgeList = new ArrayList<List<Integer>>();
		for(int i = 0; i < MAXV; i++){
			List<Integer> list = new ArrayList<Integer>();
			edgeList.add(list);
		}
		match = new int[MAXV];
		used = new boolean[MAXV];
	}
	
	boolean dfs(int v){
		used[v] = true;
		for(int i = 0; i < edgeList.get(v).size(); i++){
			int u = edgeList.get(v).get(i), w = match[u];
			if(w < 0 || !used[w] && dfs(w)){
				match[v] = u;
				match[u] = v;
				return true;
			}
		}
		return false;
	}
	
	public int bipartite_matching() {
		int res = 0;
		for(int i = 0; i < match.length; i++){
			match[i] = -1;
		}
		for(int v = 0; v < MAXV; v++){
			if(match[v] < 0){
				used = new boolean[MAXV];
				if(dfs(v)){
					res++;
				}
			}
		}

		return res;
	}

}
