package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA
public class POJ3104 {

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
		int K = sc.nextInt();
		int min = 0;
		int max = 1000000000;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			boolean f = true;
			int n = mid;
			for(int i = 0; i < N; i++){
				int rem = d[i] - mid;
				if(rem > 0){
					n -= rem / K;
					if(rem % K > 0){
						n--;
					}
					if(n < 0){
						f = false;
						break;
					}
				}
			}
			if(f){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		out.println(max);
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
		POJ3104 p = new POJ3104();

		while(true){
			try{
				N = sc.nextInt();
				if(N == 0 ){
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
