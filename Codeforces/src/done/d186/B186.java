package done.d186;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B186 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		String s = br.readLine();
		BIT bit = new BIT(s.length());
		
		for(int i = 0; i < s.length()-1; i++){
			if(s.charAt(i) == s.charAt(i+1)){
				bit.add(i+1, 1);
			}
		}
		int m = parseInt(br.readLine());
		for(int i = 0; i < m; i++){
			s = br.readLine();
			String[] sp = s.split(" ");
			int l = parseInt(sp[0]);
			int r = parseInt(sp[1]);
			int ans = bit.sum(r-1) - bit.sum(l-1);
			out.println(ans);
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
		B186 t = new B186();
		t.solve();
		bw.close();
	}
	public class BIT {

		public BIT(int n){
			this.N = n;
			bit = new int[N+2];
		}
		
	    int N = 100000;
	    int bit[] = null; 
		
	  
	    int sum0(int i){
	    	return sum(i+1);
	    }
	    
	    void add0(int i, int x){
	    	add(i+1, x);
	    }
	    

	    int sum(int i){
	    	int s = 0;
	    	while(i > 0){
	    		s += bit[i];
	    		i-=i&-i;
	    	}
	    	return s;
	    }
	    

	    void add(int i, int x){
	    	while(i <= N){
	    		bit[i] = bit[i] + x;
	    		i += i & -i;
	    	}
	    }
	}
}
