package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ3185 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 20;
	static int M = 0;
	public void solve() throws Exception{
		boolean[] f = new boolean[N];
		int p = 0;
		for(int i = 0; i < N; i++){
			int a = sc.nextInt();
			if(a == 1) {
				f[i] = true;
				p |= 1 << i;
			}
		}
		
		int ans = check(0, p);
		
		out.println(ans);
		
	}

	int check(int idx, int p){
		int ret  = INF;
		if(idx == 0){
			int np = p;
			np ^= 1;
			np ^= 2;
			ret = min(check(idx+1, p), ret);
			ret = min(check(idx+1, np) + 1, ret);
		}
		else if(idx == 19){
			if(  (p & 1 << (idx-1)) > 0 && (p & 1 << idx) > 0){
				return 1;
			}
			else if(  (p & 1 << (idx-1)) == 0 && (p & 1 << idx) == 0){
				return 0;
			}
			else{
				return INF;
			}
		}
		else{
			if( (p & 1 << (idx-1)) > 0){
				int np = p;
				np ^= (1 << idx-1);
				np ^= (1 << idx);
				np ^= (1 << idx+1);
				ret = min(check(idx+1, np)+1, ret);
			}
			else{
				ret = min(check(idx+1, p), ret);
			}
		}
		
		
		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3185 p = new POJ3185();
		while(true){
			
			try{
				p.solve();
			}
			catch(Exception ex){
				break;
			}
		
			
		}
	}

}
