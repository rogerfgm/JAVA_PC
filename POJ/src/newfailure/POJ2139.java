package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// とりあえずTLEで書いてみる
public class POJ2139 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;

	public void solve() throws Exception{
		int[][] d = new int[N+1][N+1];
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				if(i == j) continue;
				d[i][j] = INF;
			}
		}
		for(int i = 0; i < M; i++){
			int n = sc.nextInt();
			int[] l = new int[n];
			for(int j = 0; j < n; j++){
				l[j] = sc.nextInt();
			}
			for(int j = 0; j < n-1; j++){
				for(int k = j+1; k < n; k++){
					int f = l[j];
					int t = l[k];
					d[f][t] = d[t][f] = 1;
				}
			}
		}

		for(int k = 1; k <= N; k++){
			for(int i = 1; i <= N; i++){
				for(int j = 1; j <= N; j++){
					d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}

		int min = INF;
		for(int i = 1; i <= N; i++){
			double sum = 0;
			for(int j = 1; j <= N; j++){
				sum += d[i][j];
			}
			sum = sum * 100 / (N-1);
			//int ave = (int)(sum + 0.5);
			int ave = (int)sum;
			min = min(min, ave);
		}
		out.println(min);

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
		POJ2139 p = new POJ2139();

		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}

}