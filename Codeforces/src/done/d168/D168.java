package done.d168;
import java.util.*;
import java.io.*;
import java.math.*;





public class D168 {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int n = 0;

	int[] d = null;
	
	List<List<Integer>> el = new ArrayList<List<Integer>>();
	
	public void solve() throws Exception{

		n = sc.nextInt();
		d = new int[n+1];
		for(int i = 0; i <= n; i++){
			List<Integer> l = new ArrayList<Integer>();
			el.add(l);
		}
		
		for(int i = 0; i < n-1; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			el.get(f).add(t);
			el.get(t).add(f);
		}
		
		for(int i = 0; i < n; i++){
			d[i+1] = sc.nextInt();
		}
		long[] ret = check(-1, 1);
		System.out.println(ret[0] + ret[1]);
	}
	
	long[] check(int prev, int p){
		long[] ret = new long[2];
		List<Integer> l = el.get(p);
		for(int i = 0; i < l.size(); i++){
			int np = l.get(i);
			if( np != prev){
				long[] r = check(p, np);
				ret[0] = Math.max(ret[0], r[0]);
				ret[1] = Math.max(ret[1], r[1]);
			}
		}
		long val = ret[0] - ret[1];
		val = val + d[p];
		if(val > 0){
			ret[1] += Math.abs(val);
		}
		else{
			ret[0] += Math.abs(val);
		}
		
		return ret;
	}
	
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		D168 t = new D168();
		t.solve();
		
	}
	
	
}