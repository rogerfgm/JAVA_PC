package tmp;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class C {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		int N = sc.nextInt();
		int[] sp = new int[N];
		int[] ps = new int[N];
		for(int i = 0; i < N; i++){
			String tmp = sc.next();
			sp[i] = sc.nextInt();
			ps[i] = sc.nextInt();
		}
		
		double ans = INF;
		
		for(int i = 0; i < N-1; i++){
			List<Data> list = new ArrayList<Data>();
			for(int j = i+1; j < N; j++){
				if(i == j) continue;
				Data d = new Data();
				double fw = ps[i] + 5;
				double bk = ps[i];
				if(sp[i] == sp[j]){
					if(ps[j] <= fw && fw <= ps[j] + 5 || ps[j] <= bk && bk <= ps[j] + 5){
						d.st = 0;
						d.ed = INF;
						list.add(d);
					}
				}
				else{
					
					double spd = max(sp[i], sp[j]) - min(sp[i], sp[j]);
					// start
					if(ps[j] <= fw && fw <= ps[j] + 5 || ps[j] <= bk && bk <= ps[j] + 5){
						d.st = 0;
					}
					else{
						if(sp[i] < sp[j] && ps[i] < ps[j] || sp[i] > sp[j] && ps[i] > ps[j]){
							continue;
						}
						
						int dist = max(ps[i], ps[j]) - min(ps[i], ps[j]) -5;
						d.st = (double) dist / spd;
					}
					
					// end
					if(ps[j] <= fw && fw <= ps[j] + 5 || ps[j] <= bk && bk <= ps[j] + 5){
						int dist = 0;
						if(sp[i] > sp[j]){
							dist = ps[j] + 5 - ps[i];
						}
						else{
							dist = ps[i] + 5 - ps[j];
						}
						d.ed = dist / spd;
					}
					else{
						double dist = max(ps[i], ps[j]) + 5 -  min(ps[i], ps[j]) ;
						d.ed = dist / spd;
					}
					list.add(d);
				}
				
			}
			
			List<Data> list2 = new ArrayList<Data>();
			for(int j = i+1; j < N-1; j++){
				for(int k = j+1; k < N; k++){
					Data d = new Data();
					double fw = ps[j] + 5;
					double bk = ps[j];
					if(sp[j] == sp[k]){
						if(ps[k] <= fw && fw <= ps[k] + 5 || ps[k] <= bk && bk <= ps[k] + 5){
							d.st = 0;
							d.ed = INF;
							list2.add(d);
						}
					}
					else{
						
						double spd = max(sp[j], sp[k]) - min(sp[j], sp[k]);
						// start
						if(ps[k] <= fw && fw <= ps[k] + 5 || ps[k] <= bk && bk <= ps[k] + 5){
							d.st = 0;
						}
						else{
							if(sp[j] < sp[k] && ps[j] < ps[k] || sp[j] > sp[k] && ps[j] > ps[k]){
								continue;
							}
							
							int dist = max(ps[j], ps[k]) - min(ps[j], ps[k]) -5;
							d.st = (double) dist / spd;
						}
						
						// end
						if(ps[k] <= fw && fw <= ps[k] + 5 || ps[k] <= bk && bk <= ps[k] + 5){
							int dist = 0;
							if(sp[j] > sp[k]){
								dist = ps[k] + 5 - ps[j];
							}
							else{
								dist = ps[j] + 5 - ps[k];
							}
							d.ed = dist / spd;
						}
						else{
							double dist = max(ps[j], ps[k]) + 5 -  min(ps[j], ps[k]) ;
							d.ed = dist / spd;
						}
						list2.add(d);
					}
				}
			}
			
			
			
			for(int j = 0; j < list.size(); j++){
				Data d = list.get(j);
				for(int k = 0; k < list2.size(); k++){
					Data cd = list2.get(k);
					if(d.st <= cd.st){
						if(d.ed > cd.st){
							ans = min(ans, cd.st);
						}
					}
					else{
						if(cd.ed > d.st){
							ans = min(ans, d.st);
						}
					}
				}
			}
		}
		if(ans == INF){
			out.println("Possible");
		}
		else{
			out.println(ans);
		}
			
	}
	
	class Data{
//		public Data(double st, double ed){
//			this.st = st;
//			this.ed = ed;
//		}
		double st = 0;
		double ed = 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		C b = new C();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
