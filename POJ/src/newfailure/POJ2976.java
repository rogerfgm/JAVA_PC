package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 重さでソートして重さ順？そのための２分探索？
// TLE
public class POJ2976 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int K = 0;
	
	public void solve() throws Exception{
		int[] ai = new int[N];
		int[] bi = new int[N];
		for(int i = 0; i < N; i++){
			ai[i] = sc.nextInt();
		}
		for(int i = 0; i < N; i++){
			bi[i] = sc.nextInt();
		}
		Data[] ds = new Data[N];
		for(int i = 0; i < N; i++){
			Data dt = new Data();
			dt.a = ai[i];
			dt.b = bi[i];
			ds[i] = dt;
		}
		Arrays.sort(ds, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				double d1 = (double)o1.a / o1.b;
				double d2 = (double)o2.a / o2.b;
				if(d1 < d2){
					return 1;
				}
				else if(d1 == d2){
					return 0;
				}
				else{
					return -1;
				}
			}
		});
		
		List<Data> sms = new ArrayList<Data>();
		List<Data> las = new ArrayList<Data>();
		long sb = 0;
		long sa = 0;

		for(int i = 0; i < N-K; i++){
			sms.add(ds[i]);
			sb += ds[i].b;
			sa += ds[i].a;
		}
		for(int i = 0; i < K; i++){
			las.add(ds[N-K+i]);
		}
		double max = (double) sa / sb;
		
		while(true){
			boolean f = false;
			for(int i = 0; i < sms.size(); i++){
				Data d = sms.get(i);
				long nsa = sa - d.a;
				long nsb = sb - d.b;
				for(int j = 0; j < las.size(); j++){
					Data ld = las.get(j);
					nsa += ld.a;
					nsb += ld.b;
					double ave = (double)nsa / nsb;
					if(ave > max - DF){
						max = ave;
						f = true;
						Data fs = sms.remove(i);
						Data fl = las.remove(j);
						sms.add(fl);
						las.add(fs);
						break;
					}
				}
				if(f){
					break;
				}
			}
			
			if(!f){
				break;
			}
		}
		int ans = (int)((max + DF) * 100);
		out.println(ans);
	}
	
	
	class Data{
		int a = 0;
		int b = 0;
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
		POJ2976 p = new POJ2976();

		while(true){
			try{
				N = sc.nextInt();
				K = sc.nextInt();
				if(N == 0 && K == 0){
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
