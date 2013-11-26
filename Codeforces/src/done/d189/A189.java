package done.d189;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class A189 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		String s = sc.next();
		// 1 14, 144
		if(check(s)){
			out.println("YES");
		}
		else{
			out.println("NO");
		}
		
	}
	
	boolean check(String s){
		if(s.length() == 0){
			return true;
		}
		if(s.startsWith("1")){
			String ns = s.substring(1);
			if(check(ns)){
				return true;
			}
		}
		if(s.startsWith("14")){
			String ns = s.substring(2);
			if(check(ns)){
				return true;
			}
		}
		if(s.startsWith("144")){
			String ns = s.substring(3);
			if(check(ns)){
				return true;
			}
		}
		
		
		return false;
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
		A189 t = new A189();
		t.solve();
		bw.close();
	}

}
