package newfailure;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// TLE
public class POJ3685 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int ans = 0;
	public void solve() throws Exception{
		
		//int n = i * i + 100000 * i + j*j - 100000 * j + i * j;
		if(M == 1){
			int i = 1;
			int j = N;
			int n = i * i + 100000 * i + j*j - 100000 * j + i * j;
			out.println(n);
			return;
		}
		else if(M == N * N){
			int i = N;
			int j = 1;
			int n = i * i + 100000 * i + j*j - 100000 * j + i * j;
			out.println(n);
			return;
		}
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		
		while(min + 1 < max){
			ans = Integer.MIN_VALUE;
			int mid = (min + max) / 2;
			int sum = 0;
			for(int i = N; i >= 1; i--){
				sum +=  find(i, mid);
				if(sum > M){
					break;
				}
			}
			if(sum == M){
				out.println(ans);
				return;
			}
			if(sum < M){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		ans = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++){
			find(i, max);
		}
		out.println(ans);
		return;
		
	}

	int find(int i, int th){

		int j = N;
		int min = i * i + 100000 * i + j*j - 100000 * j + i * j;
		j = 1;
		int max = i * i + 100000 * i + j*j - 100000 * j + i * j;
		if(th < min){
			return 0;
		}
		if(th == min){
			ans = max(ans, min);
			return 1;
		}
		if(th >= max){
			ans = max(ans, max);
			return N;
		}
		
		min = 1;
		max = N;
		
		while(min + 1 < max){
			j = (min + max) / 2;
			int n = i * i + 100000 * i + j*j - 100000 * j + i * j;
			if(n == th){
				return N-j + 1;
			}
			else if(n < th){
				max = j;
			}
			else{
				min = j;
			}
		}
		j = max;
		int n = i * i + 100000 * i + j*j - 100000 * j + i * j;
		ans = max(ans, n);
		return N-max+1;
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
		POJ3685 p = new POJ3685();
		int T = sc.nextInt();
		int t = 1;
		while(t++ <= T){
			
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
