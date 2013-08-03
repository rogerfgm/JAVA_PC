package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// Runtime Error
public class POJ2429 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static long G = 0;
	static long L = 0;
	
	int max = 10000000;
	public void solve() throws Exception{
		if(G == 1){
			out.println(G + " " + L);
		}
		//Map<Long, Integer> gmap = prime_factor(G);
		Map<Long, Integer> lmap = prime_factor(L/G);
		List<Long> list = new ArrayList<Long>();
		for(long key : lmap.keySet()){
			int num = lmap.get(key);
			long add = (long)pow(key, num);
			list.add(add);
		}
		long[] ds = new long[list.size()];
		for(int i = 0; i < list.size(); i++){
			ds[i] = list.get(i);
		}
		Arrays.sort(ds);
		long a = G;
		long b = G;
		
		long tmp = G;
		
		for(int i = ds.length-1; i >= 0; i--){
			if(a <= b){
				a = a * ds[i];
			}
			else{
				b = b * ds[i];
			}
			tmp *= ds[i];
		}
		if(tmp != L){
			long tmp2 = L / tmp;
			out.println(min(tmp, tmp2) + " " + max(tmp, tmp2));
			return;
		}
		out.println(min(a, b) + " " + max(a, b));
	}
	
	
	
	Map<Long, Integer> prime_factor(long n){
		Map<Long, Integer> res = new HashMap<Long, Integer>();
		for(int i = 2; i <= max; i++){
			int num = 0;
			while( n % i == 0){
				num++;
				n /= i;
			}
			if(num != 0){
				res.put((long)i, num);
			}
		}
		if(n != 1){
			if(res.containsKey(n)){
				res.put(n, 1);
			}
			else{
				int num = res.get(n) + 1;
				res.put(n, num);
			}
		}
		
		return res;
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
		POJ2429 p = new POJ2429();

		while(true){
			try{
				G = sc.nextLong();
				L = sc.nextLong();
				if(G == 0 && L == 0){
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
