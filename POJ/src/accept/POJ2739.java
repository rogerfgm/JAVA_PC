package accept;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 素数を列挙してしゃくとりするだけ
public class POJ2739 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static String S = null;
	
	static int[] d = null;
	public void solve() throws Exception{
		int ans = 0;
		int sum = d[0];
		int r = 0;
		int l = 0;
		while(r < d.length){
			if(sum == N){
				ans++;
			}

			if(sum <= N){
				r++;
				if(r >= d.length) break;
				sum += d[r];
			}
			else if(sum > N){
				sum-= d[l];
				l++;
			}
		}
		out.println(ans);
	}
	
	static void init(){
		boolean[] p = new boolean[10001];
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 2; i <= 10000; i++){
			if(!p[i]){
				l.add(i);
				for(int j = 2; ; j++){
					long a = i * j;
					if(a > 10000){
						break;
					}
					int b = j;
					while(a <= 10000){
						p[(int)a] = true;
						a *= b;
					}
				}
			}
		}
		d = new int[l.size()];
		for(int i = 0; i < d.length; i++){
			d[i] = l.get(i);
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
		POJ2739 p = new POJ2739();
		init();
		while(true){
			try{
				N = sc.nextInt();
	
			}
			catch(Exception ex){
				break;
			}
			if(N == 0) break;
			p.solve();
		}
	}

}
