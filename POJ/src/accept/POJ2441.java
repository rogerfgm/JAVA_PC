package accept;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;


// MLE
// 単純なビットDP
// コートと選んだ状態のDP
public class POJ2441 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int[] dp = null;
	List<List<Integer>> list = null;
	public void solve() throws Exception{
		list = new ArrayList<List<Integer>>();
		for(int i = 0; i < N; i++){
			List<Integer> l = new ArrayList<Integer>();
			list.add(l);
		}
		
		for(int i = 0; i < N; i++){
			int m = sc.nextInt();
			for(int j = 0; j < m; j++){
				int a = sc.nextInt() - 1;
				list.get(i).add(a);
			}
		}
		dp = new int[1<<M];
		dp[0] = 1;
		for(int i = 0; i < N; i++){
			List<Integer> l = list.get(i);
			int[] ndp = new int[1<<M];
			
			for(int j = 0; j < 1 << M; j++){
				if(dp[j] > 0){
					
					for(int k : l){
						if( (j & (1 << k)) > 0){
							continue;
						}
						int np = j | (1 << k);
						ndp[np] += dp[j];
					}
				}
			}
			dp = ndp;
		}
		int ret = 0;
		for(int i = 0; i < 1 << M; i++){
			ret += dp[i];
		}
		out.println(ret);
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
		POJ2441 p = new POJ2441();

		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && M == 0) break;
			p.solve();
		}
	}

}
