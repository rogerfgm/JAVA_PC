package done.d185;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class A185 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		String omg = "OMG>.< I don't know!";
		String F = "Freda's";
		String R = "Rainbow's";
		int n = parseInt(br.readLine());
		for(int i = 0; i < n; i++){
			String s = br.readLine();
			
			boolean l = s.startsWith("miao.");
			boolean f = s.endsWith("lala.");
			if(f && l || !f && !l){
				out.println(omg);
			}
			else if(f){
				out.println(F);
			}
			else{
				out.println(R);
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
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		A185 t = new A185();
		t.solve();
		bw.close();
	}

}
