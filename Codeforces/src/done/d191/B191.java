package done.d191;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B191 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		
		
		int base = 5500001;
		int max = 10000000;
		Set<Integer> set = new HashSet<Integer>();
		bw.write(base + "");
		int prev = base;
		for(int i = 1; i < N; i++){
			for(long j = 1; ; j++){
				long t = j * prev;
				if(t > max){
					break;
				}
				set.add((int)t);
			}
			
			while(true){
				if(set.contains(prev)){
					prev++;
				}
				break;
			}
			bw.write(" " + prev);
		}
		//out.println(prev);
		
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
		B191 t = new B191();
		t.solve();
		bw.close();
	}

}
