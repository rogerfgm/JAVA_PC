package d192;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B192 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int[] ti = nextInts();
		int n = ti[0];
		int m = ti[1];
		Set<Integer> set  = new HashSet<Integer>();
		for(int i = 0; i < m; i++){
			ti = nextInts();
			set.add(ti[0]);
			set.add(ti[1]);
		}
		int mid = -1;
		for(int i = 1; i <= n; i++){
			if(!set.contains(i)){
				mid = i;
				break;
			}
		}
		if(n == 1){
			out.println("0");
			return;
		}
		else if(n == 2){
			out.println("1");
			out.println("1 2");
			return;
		}
		else{
			int ans = n - 1;
			out.println(ans);
			for(int i = 1; i <= n; i++){
				if(i == mid) continue;
				out.println(i + " " + mid);
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
		B192 t = new B192();
		t.solve();
		bw.close();
	}

}
