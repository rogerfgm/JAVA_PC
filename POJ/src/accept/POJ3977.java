package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 半分全列挙
// ２つに分けてあわせる
// あわせるとき前の結果（位置）を使う
public class POJ3977 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	long[] da = null;
	long[] db = null;
	int na = 0;
	int nb = 0;
	long ans = 0;
	long anum = 0;
	public void solve() throws Exception{
		na = N / 2;
		nb = N / 2;
		if(N % 2 == 1){
			na++;
		}
		da = new long[na];
		db = new long[nb];
		ans = Long.MAX_VALUE;
		anum = 1;
		for(int i = 0; i < na; i++){
			da[i] = sc.nextLong();
			ans = min(ans, abs(da[i]));
		}
		if(nb == 0){
			out.println(ans + " 1" );
			return;
		}
		for(int i = 0; i < nb; i++){
			db[i] = sc.nextLong();
			ans = min(ans, abs(db[i]));
		}
		Data[] dpa = new Data[(1<<na) - 1];
		Data[] dpb = new Data[(1<<nb) -1];
		create(na, da, dpa);
		create(nb, db, dpb);
		
		Arrays.sort(dpa, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				long t = o1.d - o2.d;
				if(t < 0) return -1;
				else if(t == 0) return 0;
				else return 1;
			}
		});
		Arrays.sort(dpb, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				long t = o2.d - o1.d;
				if(t < 0) return -1;
				else if(t == 0) return 0;
				else return 1;
			}
		});
		
		int bidx = 0;
		for(int i = 0; i < dpa.length; i++){
			
			while(bidx < dpb.length-1 && abs(dpa[i].d + dpb[bidx].d) >= abs(dpa[i].d + dpb[bidx+1].d)){
				getans(dpa[i], dpb[bidx]);
				bidx++;
			}
			getans(dpa[i], dpb[bidx]);

			
		}
		

		out.println(ans + " " + anum);
	}
	
	void getans(Data da, Data db){
		long sum = abs(da.d + db.d);
		if(sum <= ans){
			int num = getnum(da.p) + getnum(db.p);
			if(sum == ans){
				anum = min(anum, num);
			}
			else{
				ans = sum;
				anum = num;
			}
		}
	}
	
	class Data{
		long d = 0;
		int p = 0;
	}
	
	int getnum(int p){
		int num = 0;
		while(p > 0){
			if((p & 1) > 0){
				num++;
			}
			p = p  >> 1;
		}
		return num;
	}

	void create(int n, long[] d, Data[] dp){
		for(int i = 1; i < 1 << n; i++){
			long sum = 0;
			for(int j = 0; j < n; j++){
				if( (i & (1 << j)) > 0){
					sum += d[j];
				}
			}
			Data dt = new Data();
			dt.d = sum;
			dt.p = i;
			dp[i-1] = dt;
			if(abs(sum) <= ans){
				int num = getnum(i);
				if(abs(sum) == ans){
					anum = min(anum, num);
				}
				else{
					ans = abs(sum);
					anum = num;
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
		POJ3977 p = new POJ3977();
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
