package done.d215;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class D {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int n = t[0];
		int m = t[1];
		int p = t[2];
		int[] a = nextInts();
		int[] b = nextInts();
		Arrays.sort(b);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < m; i++){
			if(!map.containsKey(b[i])){
				map.put(b[i], 0);
			}
			int num = map.get(b[i]);
			num++;
			map.put(b[i], num);
			
		}
		
		List<Integer> alist = new ArrayList<Integer>();
		for(int i = 0; i < p; i++){
			int q = i + 1;
			
			if(q + (m-1) * (long)p > n) break;
			Map<Integer, Integer> cmap = new HashMap<Integer, Integer>();
			for(int j = 0; j < m; j++){
				int idx = i + j * p;
				if(!cmap.containsKey(a[idx])){
					cmap.put(a[idx], 0);
				}
				int num = cmap.get(a[idx]);
				num++;
				cmap.put(a[idx], num);
			}
			if(check(map, cmap)){
				alist.add(q);
			}
			int old = i;
			int next = i + m * p;
			while(next < n){
				int num = cmap.get(a[old]);
				num--;
				if(num == 0){
					cmap.remove(a[old]);
				}
				else{
					cmap.put(a[old], num);
				}
				if(!cmap.containsKey(a[next])){
					cmap.put(a[next], 0);
				}
				num = cmap.get(a[next]);
				num++;
				cmap.put(a[next], num);
				if(check(map, cmap)){
					int nq = old + p + 1;
					alist.add(nq);
				}
				old += p;
				next += p;
			}
		}
		
		out.println(alist.size());;
		Integer[] as = new Integer[0];
		as = alist.toArray(as);
		Arrays.sort(as);
		for(int i = 0; i < as.length; i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(as[i]);
		}
	}
	
	boolean check(Map<Integer, Integer> amap, Map<Integer, Integer> bmap){
	
		if(amap.keySet().size() != bmap.keySet().size()){
			return false;
		}
		for(int akey : amap.keySet()){
			if(!bmap.containsKey(akey)){
				return false;
			}
			if(amap.get(akey).intValue() != bmap.get(akey).intValue()){
				return false;
			}
		}
		return true;
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
		File file = new File("test.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("test.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		D t = new D();
		t.solve();
		bw.close();
	}

}
