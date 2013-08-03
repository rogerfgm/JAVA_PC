package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA...
// 真ん中の値で２分探索だけどいまのやり方だと N^2 logN
//　TLEしないやりかたがあるはずなので考える
public class POJ3579 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{
		int[] d = new int[N];
		for(int i = 0; i < N; i++){
			d[i] = sc.nextInt();
		}
		Arrays.sort(d);
		
		long all = (long)N * (N-1) / 2;
		long M = all/2;

		int min = -1;
		int max = d[N-1] + 1;
		while(true){
			long low = 0;
			long hi = 0;
			int mid = (min + max) / 2;
			for(int i = 0; i < N-1; i++){
				for(int j = i+1; j < N; j++){
					int diff = d[j] - d[i];
					if(diff == mid){
					}
					else if(diff < mid){
						low++;
					}
					else{
						hi++;
					}
				}
				if(low > M || hi > M){
					break;
				}
			}
			if(low <= M && hi <= M){
				out.println(mid);
				return;
			}
			if(hi > low){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		
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
		POJ3579 p = new POJ3579();
		while(true){
			
			try{
				N = sc.nextInt();
				if(N == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}

}
