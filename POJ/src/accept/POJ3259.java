package accept;

import java.util.*;
import java.io.*;





public class POJ3259 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;

	static int n = 0;
	static int m = 0;
	static int w = 0;

	List<Map<Integer, Integer>> G = null;

	public void solve() throws Exception{
		G = new ArrayList<Map<Integer, Integer>>();
		for(int i = 0; i < n; i++){
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			G.add(map);
		}
		
		
		for(int i = 0; i < m; i++){
			int f = sc.nextInt()-1;
			int t = sc.nextInt()-1;
			int c = sc.nextInt();
			Map<Integer, Integer> map = G.get(f);
			if(map.containsKey(t)){
				int d = map.get(t);
				if(d > c){
					map.put(t, c);
					map = G.get(t);
					map.put(f, c);
				}
			}
			else{
				map.put(t, c);
				map = G.get(t);
				map.put(f, c);
			}
		}
		
		for(int i = 0; i < w; i++){
			int f = sc.nextInt()-1;
			int t = sc.nextInt()-1;
			int c = sc.nextInt();
			Map<Integer, Integer> map = G.get(f);
			map.put(t, -c);
		}
		
		int[] d = new int[n];
		for(int i = 0; i < n; i++){
			d[i] = INF;
		}
		d[0] = 0;
		for(int i = 0; i <= n; i++){
			if(i == n){
				out.println("YES");
				return;
			}
			boolean f = false;
			for(int j = 0; j < n; j++){
				Map<Integer, Integer> m = G.get(j);
				for(int t : m.keySet()){
					if(d[t] > d[j] + m.get(t)){
						f = true;
						d[t] = d[j] + m.get(t);
					}
				}
			}
			if(!f){
				break;
			}
		}		
		out.println("NO");
	}

	class Edge{
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
		POJ3259 p = new POJ3259();
		int T = sc.nextInt();
		int t = 1;
		while(t++ <= T){
			try{
			n = sc.nextInt();
			m = sc.nextInt();
			w = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			
			p.solve();
		}
	}
	


}
