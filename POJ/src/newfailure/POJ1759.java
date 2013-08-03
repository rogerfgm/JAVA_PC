package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA
public class POJ1759 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.0001;

	static int N = 0;
	static double A = 0;
	double mn = 0;
	double[] d = null;
	public void solve() throws Exception{
		d = new double[N+1];
		d[1] = A;
		d[2] = 0;
		
		double min = -10;
		double max = Integer.MAX_VALUE;
		mn = Double.MAX_VALUE;

		while(min + DF < max){
			double mid = (min + max) / 2;
			d[2] = mid;
			mn = Double.MAX_VALUE;
			check(3);
			if(mn + 0.00000000001 < 0){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		
		String ans = getAns(d[N]);
		out.println(ans);
		// Hi = (Hi-1 + Hi+1)/2 - 1
		// Hi = 2 * [Hi-1] - [Hi-2] + 2
	}
	
	String getAns(double a){
		int sei = (int)a;
		a *= 100;
		int shou = (int)a;
		shou = shou % 100;
		String ss = Integer.toString(shou);
		if(ss.length() < 2){
			ss = "0" + ss;
		}
		return sei + "." + ss;
	}
	
	void check(int idx){
		d[idx] = 2 * d[idx-1] - d[idx-2] + 2;
		mn = min(d[idx], mn);
		if(idx == N){
			return;
		}
		check(idx+1);
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
		POJ1759 p = new POJ1759();

		while(true){
			try{
				N = sc.nextInt();
				A = sc.nextDouble();
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
