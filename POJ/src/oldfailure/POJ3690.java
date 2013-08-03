package oldfailure;
import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ3690 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int N = 0;
	static int M = 0;
	static int T = 0;
	static int P = 0;
	static int Q = 0;
	
	long XB = 581237;
	long YB = 624721;
	long MOD = (long)Math.pow(2, 32);
	
	
	public void solve() throws Exception{
		char[][] cs = new char[N][M];
		for(int i = 0; i < N; i++){
			String s = sc.next();
			for(int j = 0; j < M; j++){
				cs[i][j] = s.charAt(j);
			}
		}
		long[][] dx = new long[N][M];
		for(int i = 0; i < N; i++){
			long a = 0;
			long t = 1;
			for(int j = 0; j < Q; j++){
				t *= XB;
				t %= MOD;
			}
			for(int j = 0; j < Q; j++){
				a = a * XB + cs[i][j];
				a %= MOD;
			}
			dx[i][0] = a;
			for(int j = Q; j < M; j++){
				a = a * XB + cs[i][j] - cs[i][j-Q] * t;
				a %= MOD;
				dx[i][j-Q+1] = a;
			}
		}
		long[][] dy = new long[N][M];
		for(int i = 0; i < M; i++){
			long a = 0;
			long t = 1;
			for(int j = 0; j < P; j++){
				t *= YB;
				t %= MOD;
			}
			for(int j = 0; j < P; j++){
				a = a * YB + cs[j][i];
				a %= MOD;
			}
			dy[0][i] = a;
			for(int j = P; j < N; j++){
				a = a * YB + cs[j][i] - cs[j-P][i] * t;
				a %= MOD;
				dy[j - P + 1][i] = a;
			}
		}
		int ans = 0;
		while(T-- > 0){
			sc.nextLine();
			char[][] t = new char[P][Q];
			for(int i =0; i < P; i++){
				String s = sc.next();
				for(int j = 0; j < Q; j++){
					t[i][j] = s.charAt(j);
				}
			}
			long[] tc = new long[P];
			
			for(int i = 0; i < P; i++){
				long a = 0;
				for(int j = 0; j < Q; j++){
					a = a * XB + t[i][j];
					a %= MOD;
				}
				tc[i] = a;
			}
			long a = 0;
			for(int j = 0; j < P; j++){
				a = a * YB + t[j][0];
				a %= MOD;
			}
			boolean found = false;
			for(int i = 0; i < N - P + 1; i++){
				for(int j = 0; j < M - Q + 1; j++){
					if(dx[i][j] == tc[0] && dy[i][j] == a){
						boolean f = true;
						for(int k = 1; k < Q; k++){
							if(dx[i+k][j] != tc[k]){
								f = false;
								break;
							}
						}
						if(f){
							found = true;
							break;
						}
					}
				}
				if(found){
					break;
				}
			}
			if(found){
				ans++;
			}
		}
		System.out.println(ans);
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
		sc =  new Scanner(System.in);
		POJ3690 t = new POJ3690();
		int idx = 1;
		while(true){
			N = sc.nextInt();
			M = sc.nextInt();
			T = sc.nextInt();
			P = sc.nextInt();
			Q = sc.nextInt();
			if(N == 0 && M == 0){
				break;
			}
			System.out.print("Case " + idx++ + ": ");
			t.solve();
		}
	}


}