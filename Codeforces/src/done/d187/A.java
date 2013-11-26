package done.d187;


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class A {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		int[] a = new int[N];
		int[] b = new int[N];
		
		for(int i = 0; i < N; i++){
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		
		int ans = 0;
		for(int i = 0;i < N;i ++){
			boolean f = false;
			for(int j = 0; j < N; j++){
				if(i == j){
					continue;
				}
				if(a[i] == b[j]){
					f = true;
				}
			}
			if(!f){
				ans++;
			}
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

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
	
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		A t = new A();
		t.solve();

	}

}
