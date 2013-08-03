package accept;


import java.util.*;
import java.io.*;


public class POJ1990 {
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	static PrintStream out = null;
	static int n = 0;
	
	Data[] ds = null;
	public void solve() throws Exception{
		ds = new Data[n];
		
		for(int i = 0; i < n; i++){
			Data d = new Data();
			d.v = sc.nextInt();
			d.x = sc.nextInt();
			ds[i] = d;
		}
		Arrays.sort(ds, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o1.v - o2.v;
			}
		});
		
		BIT distB = new BIT(20000);
		BIT numB = new BIT(20000);
		long ans = 0;
		for(int i = 0; i < n; i++){
			Data d = ds[i];
			
			long numS = numB.sum(d.x-1);
			long numL = numB.sum(20000) - numB.sum(d.x-1);
			ans += (numS * d.x -  distB.sum(d.x-1)) * d.v;
			ans += (distB.sum(20000) - distB.sum(d.x) - numL * d.x ) * d.v;
			distB.add(d.x, d.x);
			numB.add(d.x, 1);
		}
		System.out.println(ans);
		
	}
	
	class Data{
		int x = 0;
		int v = 0;
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
		POJ1990 t = new POJ1990();

		while(true){
			try{
			n = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(n == 0) break;
			t.solve();
		}
	}
	public class BIT {

		public BIT(int n){
			this.N = n;
			bit = new int[N+1]; // N+1こ必要。
			// 配列は１から始まるため。0はないので注意。0 indexでも個数を渡せば大丈夫
		}
		
	    int N = 100000;
	    int bit[] = null; 
		
	    // 0　indexの時はこっちつかう
	    int sum0(int i){
	    	return sum(i+1);
	    }
	    
	    // 0　indexの時はこっちつかう
	    void add0(int i, int x){
	    	add(i+1, x);
	    }
	    
	    // 1からiまでのsumを求める。
	    // indexは1から始まるのを注意。
	    // 5から10までのsumを求めたければ、sum(10) - sum(4)が答え。
	    // indexは1からのため、場合によてずらすときに注意。
	    int sum(int i){
	    	int s = 0;
	    	while(i > 0){
	    		s += bit[i];
	    		i-=i&-i;
	    	}
	    	return s;
	    }
	    
	    // indexは1から始まるのを注意。
	    // 0から始まる配列のindexが5の値はadd(6, x)になる。
	    void add(int i, int x){
	    	while(i <= N){
	    		bit[i] = bit[i] + x;
	    		i += i & -i;
	    	}
	    }
	}


}
