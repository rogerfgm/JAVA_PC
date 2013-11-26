package done.d191;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A191 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		N = sc.nextInt();
		int[] d = readIntArray(N);
		
		int ans = 0;
		
		for(int i = 0; i < d.length; i++){
			for(int j = i; j < d.length; j++){
				int c = 0;
				for(int k = 0; k < N; k++){
					if(k >= i && k <= j){
						if(d[k] == 0){
							c++;
						}
					}
					else{
						if(d[k] > 0){
							c++;
						}
					}
				}
				ans = max(ans, c);
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
		bw = new BufferedWriter(new PrintWriter(out));
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		A191 t = new A191();
		t.solve();
		bw.close();
	}

}
