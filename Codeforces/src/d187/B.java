package d187;


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	int N = 0;
	
	public void solve() throws Exception{
		String[] ss = br.readLine().split(" ");
		N = parseInt(ss[0]);
		int M = parseInt(ss[1]);
		int[] d = new int[N];
		ss = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			d[i] = parseInt(ss[i]);
		}
		int sum = 0;
		for(int i = 0;i < M; i++){
			ss = br.readLine().split(" ");
			
			if(ss[0].equals("2")){
				sum += parseInt(ss[1]);
			}
			else if(ss[0].equals("3")){
				int idx = parseInt(ss[1]) - 1;
				int ans = d[idx] + sum;
				out.println(ans);
			}
			else{
				int idx = parseInt(ss[1])-1;
				int v = parseInt(ss[2]);
				d[idx] = v - sum;
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

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
	
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		B t = new B();
		t.solve();

	}

}
