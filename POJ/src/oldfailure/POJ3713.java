package oldfailure;
import java.io.*;
import java.util.*;




public class POJ3713 {

	
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;

	int V = 0;
	int root = 0;
	int pre = 1; // prenum
	
	int rm = 0;
	
	List<List<Integer>> edgeList = null;
	int[] prenum = null;
	int[] lowest = null;
	int[] parent = null;
	
	boolean[] used = null;
	void init(){
		pre = 1;
		root = 0;
		prenum = new int[V+1];
		lowest = new int[V+1];
		parent = new int[V+1];
		used = new boolean[V+1];
		
		for(int i = 0; i <= V; i++){
			lowest[i] = INF;
		}
	}

	
	void add_edge(int from, int to){
		edgeList.get(from).add(to);
		edgeList.get(to).add(from);
	}
	
	
	int dfs(int v, int p){
		prenum[v] = pre++;
		lowest[v] = prenum[v];
		parent[v] = p;
		used[v] = true;
		
		
		for(int u : edgeList.get(v)){
			if(u == rm){
				continue;
			}
			if(!used[u]){
				lowest[v] = Math.min(lowest[v], dfs(u, v));
			}
			else if(u != p){
				lowest[v] = Math.min(prenum[u], lowest[v]);
			}
		}
		
		return lowest[v];
	}
	

	
	public void solve() throws Exception{

		
		while(true){
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0){
				break;
			}
			V = n;
			edgeList = new ArrayList<List<Integer>>();
			for(int i = 0; i <= V; i++){
				List<Integer> l = new ArrayList<Integer>();
				edgeList.add(l);
			}
			
			for(int i = 0; i < m; i++){
				int a = sc.nextInt();
				int b = sc.nextInt();
				add_edge(a, b);
			}
			
			boolean flag = true;
			if(n < 4){
				flag = false;
			}
			for(rm = 0; rm < V; rm++){
				init();
				root = 1;
				if(rm == 1){
					root = 2;
				}
				dfs(root, -1);
			
				Set<Integer> parSet = new HashSet<Integer>();
				Set<Integer> articuSet = new HashSet<Integer>();
	
				for(int i = 0; i < V; i++){
					if(i == rm){
						continue;
					}
					if(!used[i]){
						flag = false;
						break;
					}
					if(parent[i] >= 0){
						int p = parent[i];
						if(p != root && prenum[p] <= lowest[i]){
							articuSet.add(p);
						}
						if(parSet.contains(p)){
							articuSet.add(p);
						}
						parSet.add(p);
					}
				}
				if(articuSet.size() > 0){
					flag = false;
					break;
				}
				
			}
			
			if(flag){
				System.out.println("YES");
			}
			else{
				System.out.println("NO");
			}
			
			
			sc.nextLine();
		}
		
		
		
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
		POJ3713 t = new POJ3713();
		t.solve();

	}

}
