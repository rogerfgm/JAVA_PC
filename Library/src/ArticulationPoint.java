import java.io.*;
import java.util.*;


/*
 * 
 * 
 * 
 * 
 * 
 * 
http://algorithms.blog55.fc2.com/blog-entry-32.html#
以下に示す Depth First Search (DFS) の応用によって、連結グラフ G の全ての Articulation Points を検出することができます。 


 G の任意のノードをスタート点として、DFS を行う。グラフ G の各ノード u に対して、DFS における訪問の順番 prenum[u] を記録します。 

 DFS によって生成される木（DFS Tree）における u の親 parent[u] を記録します。ここで、この DFS Tree を T とします。 

 各ノード u に対して、以下のうちの最小値として lowest[u] を計算すます。 
 i. prenum[u] 
ii. G の Back edge (u, v) が存在するとき、ノード v における prenum[v]. Back edge (u, v) とは、ノード u から T に属するノード v に向かう T に属さない G のエッジです　 
iii. T に属するノード u のすべての子供 x に対する lowest[x] 
 

 Articulation Points は以下のように決定されます 
 (1) T のルート r が2つ以上の子供をもつとき（必要十分条件）、 r は G の Articulation Point です。 
(2) 各ノード u において、u の親 parent[u] を p とすると、prenum[p] ≦ lowest[u] ならば（必要十分条件）、p は Articulation Point である（p がルートの場合は (1) を適用します）。 
 




 */



public class ArticulationPoint {

	
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	int V = 0;
	int root = 0;
	int pre = 1; // prenum
	
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
			
			used = new boolean[V+1];
			
			init();
			root = 1;
			dfs(root, -1);
		
			Set<Integer> parSet = new HashSet<Integer>();
			Set<Integer> articuSet = new HashSet<Integer>();

			for(int i = 1; i <= V; i++){
				if(parent[i] > 0){
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
			
			for(int a : articuSet){
				System.out.println(a);
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
		ArticulationPoint t = new ArticulationPoint();
		t.solve();

	}

}
