package done.d214;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C214TLE {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] tmp = nextInts();
		int n = tmp[0];
		int k = tmp[1];
		int[] a = nextInts();
		int[] b = nextInts();
		
		int ans = -1;
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		
		for(int i = 1; i <= 10000; i++){
			Set<Integer> set = new HashSet<Integer>();
			map.put(i, set);
		}
		
		
		for(int i = 0; i < n; i++){
			for(int j = 10000; j - b[i] > 0; j--){
				int pb = j - b[i];
				for(int ps : map.get(pb)){
					int ns = ps + a[i];
					if(ns / j > k) continue;
					else if(ns / j == k && ns % j == 0){
						ans = max(ans, ns);
						map.get(j).add(ns);
					}
					else{
						map.get(j).add(ns);
					}
				}
			}
			
			if(a[i]/b[i] == k && a[i]%b[i] == 0) ans = max(ans, a[i]);
			map.get(b[i]).add(a[i]);
		}
		out.println(ans);
		
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
		C214TLE t = new C214TLE();
		t.solve();
		bw.close();
	}

}
