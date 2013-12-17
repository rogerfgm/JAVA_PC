


import java.io.*;
import java.util.*;
import java.math.*;




import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class TLEC217 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		N = t[0];
		int m = t[1];
		int[] col = new int[N];
		t = nextInts();
		int[] num = new int[m+1];
		for(int i = 0; i < N; i++){
			col[i] = t[i];
			num[t[i]]+=2;
		}
		
		Matching flow = new Matching(2 * N + 10);
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(col[i] != col[j]){
					flow.add_edge(i, N + j);
				}
			}
		}

		long ans = flow.bipartite_matching();
		out.println(ans);
		int[] match = flow.match;
		for(int i = 0; i < N; i++){
			if(match[i] != -1){
				int lidx = i;
				int ridx = match[i] - N;
				int left = col[lidx];
				int right = col[ridx];
				out.println(left + " " + right);
				num[col[ridx]]--;
				num[col[lidx]]--;
			}
		}

		for(int i = 1; i <= m; i++){
			while(num[i] > 0){
				out.println(i + " " + i);
				num[i]-=2;
			}
		}
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		TLEC217 t = new TLEC217();
		t.solve();
		bw.close();
	}

	public class Matching {

		int MAXV = 0;
		public List<List<Integer>> edgeList = null;
		public int[] match = null;
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
