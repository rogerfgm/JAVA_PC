package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// Runtime Error
public class POJ3421 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static Map<Integer, int[]> dp = new HashMap<Integer, int[]>();
	static int MAX = 220;
	static boolean[] isPrime = new boolean[221];
	public void solve() throws Exception{
		int[] ret = check(N);
		out.println(ret[0] + " " + ret[1]);
	}
	
	int[] check(int n){
		if(dp.containsKey(n)){
			return dp.get(n);
		}
		if(n == 1){
			int[] ret = new int[2];
			ret[0] = 0;
			ret[1] = 0;
			dp.put(n, ret);
			return ret;
		}
		else if(isPrime[n]){
			int[] ret = new int[2];
			ret[0] = 1;
			ret[1] = 1;
			dp.put(n, ret);
			return ret;
		}
		List<Integer> div = divisor(n);
		int maxlen = 0;
		int maxnum = 0;
		for(int d : div){
			if(d == 1) continue;
			int[] ret = check(n / d);
			if(ret[0] > maxlen){
				maxlen = ret[0];
				maxnum = ret[1];
			}
			else if(ret[0] == maxlen){
				maxnum += ret[1];
			}
		}
		
		int[] ret = new int[2];
		ret[0] = maxlen + 1;
		ret[1] = maxnum;
		dp.put(n, ret);
		return ret;
	}
	
	
	List<Integer> divisor(int n){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i * i <= n; i++){
			if(n % i == 0){
				list.add(i);
				if(i != n / i) list.add(n / i);
			}
		}
		return list;
	}
	
	
	static void init(){
		for(int i = 2; i <= MAX; i++){
			isPrime[i] = true;
		}
		
		for(int i = 2; i <= MAX; i++){
			if(isPrime[i]){
				for(int j = 2; ; j++){
					int nx = i * j;
					if(nx > MAX){
						break;
					}
					isPrime[nx] = false;
				}
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
		POJ3421 p = new POJ3421();

		while(true){
			try{
				
				init();

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
