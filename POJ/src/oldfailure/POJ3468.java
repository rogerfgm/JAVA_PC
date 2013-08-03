package oldfailure;
import java.util.*;
import java.io.*;


public class POJ3468 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	long[] sg1 = null;
	long[] sg2 = null;
	int n = 0;
	public void solve() throws Exception{

		int n = sc.nextInt();
		int q = sc.nextInt();

		int N = 1;
		while(true){
			N *= 2;
			if(N -1 >= n){
				N--;
				break;
			}
		}
		
		sg1 = new long[2*N];
		sg2 = new long[2*N];

		for(int i = 0; i < n; i++){
			long v = sc.nextLong();
			sg1[N+i] = v;
			int p = N + i;
			while(p > 0){
				p = (p-1) / 2;
				sg1[p] = sg1[2*p+1] + sg1[2*p + 2];
			}
		}
		
		while(q-- > 0){
			String s = sc.next();
			if(s.equals("Q")){
				int l = sc.nextInt();
				int r = sc.nextInt();
				long ans = query1(l, r, 0, 1, N);
				System.out.println(ans);
			}
			else{
				int l = sc.nextInt();
				int r = sc.nextInt();
				int v = sc.nextInt();
				upd2(l, r, 0, v, 1, N);
			}
		}
	}
	
	
	long query1(int l, int r, int p, int pl, int pr){
		if(r < pl || pr < l  ){
			return 0;
		}
		if(l <=  pl &&  pr <= r){
			return sg1[p] + (pr - pl + 1) * sg2[p];
		}
		
		return (pr - pl + 1) * sg2[p] + query1(l, r, 2*p + 1, pl, (pl + pr) / 2) + query1(l, r, 2*p + 2, (pl + pr) / 2 + 1, pr);
	}
	
	long query2(int l, int r){
		
		return 0;
	}
	
	/**
	 * pl, prはその接点の範囲。l, rは更新範囲
	 * @param l
	 * @param r
	 * @param p
	 * @param v
	 * @param pl
	 * @param pr
	 */
	void upd2(int l, int r, int p, long v, int pl, int pr){
		if(r < pl || pr < l){
			return;
		}
		if(l <=  pl &&  pr <= r){
			sg2[p] += v;
			return;
		}
		
		upd2(l, r, 2*p + 1, v, pl, (pl + pr) / 2);
		upd2(l, r, 2*p + 2, v, (pl + pr) / 2 + 1, pr);
	}

//	void updsg1(int p, long v){
//		p = n + p;
//		while(p > 0){
//			sg[p] += v;
//			p /= 2;
//		}
//	}

	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3468 t = new POJ3468();
		t.solve();
	}


}