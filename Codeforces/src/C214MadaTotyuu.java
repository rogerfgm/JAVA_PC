


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C214MadaTotyuu {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int n = nextInt();
		int k = nextInt();
		int[] a = nextInts();
		int[] b = nextInts();
		
		int ans = -1;
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for(int i = 1; i <= 10000 && i * k <= 10000; i++){
			int bo = i;
			int si = i * k;
	
			for(int j = 0; j < n; j++){
				if(bo == b[j] && si == a[j]){
					ans = si;
					break;
				}
				Map<Integer, Set<Integer>> newmap = new HashMap<Integer, Set<Integer>>();
				for(int tb : map.keySet()){
					int nb = tb + b[j];
					if(nb > bo) continue;
					if(!newmap.containsKey(nb)){
						newmap.put(nb, new HashSet<Integer>());
					}
					for(int ts : map.get(tb)){
						int ns = ts + a[j];
						if(nb == bo && ns == si){
							ans = si;
							break;
						}
						if(!newmap.get(nb).contains(ns)) newmap.get(nb).add(ns);
					}
					
					
				}
				
				
				
				
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

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
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
		C214MadaTotyuu t = new C214MadaTotyuu();
		t.solve();
		bw.close();
	}

}
