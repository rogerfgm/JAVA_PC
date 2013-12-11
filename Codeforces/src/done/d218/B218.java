package done.d218;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B218 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	long base = 1000000007;
	Map<Long, Integer> map = null;
	public void solve() throws Exception{

		int[] t = nextInts();
		int a = t[0];
		int b = t[1];
		map = new HashMap<Long, Integer>();
		int ans = check(a, b);
		if(ans >= INF){
			out.println("-1");
		}
		else{
			out.println(ans);
		}
	
		
	}
	
	int check(long a, long b){
		if(a == b){
			return 0;
		}
		if(b > a){
			long tmp = b;
			b = a;
			a = tmp;
		}
		long key = a * base + b;
		if(map.containsKey(key)){
			return map.get(key);
		}
		int ans = INF;
		if(a % 2 == 0){
			ans = min(ans, 1 + check(a/2, b));
		}
		if(a % 3 == 0){
			ans = min(ans, 1 + check(a/3, b));
		}
		if(a % 5 == 0){
			ans = min(ans, 1 + check(a/5, b));
		}
		map.put(key, ans);
		return ans;
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
		B218 t = new B218();
		t.solve();
		bw.close();
	}

}
