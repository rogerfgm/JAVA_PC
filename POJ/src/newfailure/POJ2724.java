package newfailure;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;

//Runtime Error
//どこでruntime errorになるかわからず
// 一度ばらしてアスタでつながりうるやつを組みにして二部マッチング。
public class POJ2724 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;


	public void solve() throws Exception{
		
		int idx = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();

		for(int i = 0; i < M; i++){
			String s = sc.next();
			if(s.contains("*")){
				String ns1 = s.replaceAll("\\*", "0");
				String ns2 = s.replaceAll("\\*", "1");
				map.put(ns1, idx++);
				map.put(ns2, idx++);
				
			}
			else{
				map.put(s, idx++);
			}
		}
		
		int n = map.size();
		Matching mc = new Matching(n);
		for(String s : map.keySet()){
			for(int i = 0; i < s.length(); i++){
				char c = '1';
				if(s.charAt(i) == c){
					c = '0';
				}
				String ns = s.substring(0, i) + c + s.substring(i+1);
				if(map.containsKey(ns) && map.get(ns).intValue() > map.get(s).intValue()){
					mc.add_edge(map.get(s), map.get(ns));
				}
			}
		}
		
		int ans = n - mc.bipartite_matching();
		out.println(ans);
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
		POJ2724 p = new POJ2724();
	
		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && M == 0) break;
			p.solve();
		}
	}
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

}
