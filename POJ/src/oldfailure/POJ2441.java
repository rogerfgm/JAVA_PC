package oldfailure;
import java.io.*;
import java.util.*;


public class POJ2441 {
	static Scanner sc = null;
	int N = 0;
	int M = 0;
	int[] dp = null;
	int[] cow = null;
	public void solve() throws Exception{			
		N = sc.nextInt();
		M = sc.nextInt();

		cow = new int[N];
		for(int i = 0; i < N; i++){
			int m = sc.nextInt();
			int c = 0;
			for(int j = 0; j < m; j++){
				int f = sc.nextInt() - 1;
				c |= 1 << f;
			}
			cow[i] = c;
		}
		dp = new int[1<<M];
		dp[0] = 1;
		for(int i = 0;i < N; i++){
			int[] ndp = new int[1<<M];
			for(int j = 0; j < M; j++){
				if( (cow[i] & 1 << j) > 0){
					for(int k = 0; k < 1 << M; k++){
						if( (k & 1 << j) == 0){
							ndp[k | 1 << j] += dp[k];
						}
					}
				}
			}
			dp = ndp;
		}
		int ret = 0;
		for(int i = 0; i < 1 << M; i++){
			ret += dp[i];
		}
		System.out.println(ret);
		
	}
	
	
	public static void main(String[] args) throws Exception{

		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ2441 t = new POJ2441();
		t.solve();

	}
	
}
