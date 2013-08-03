package giveup;

import java.io.*;
import java.util.*;

// 数値積分だが、その前のどこの面積をどうやってというところがわからない。
// あり本の問題なのであとで解説を精読する
public class AOJ1313 {

	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	static int m = 0;
	
	class P1{
		int x = 0;
		int y = 0;
	}

	P1[] p1s = null;
	P1[] p2s = null;
	public void solve() throws Exception{
		
		p1s = new P1[m];
		p2s = new P1[n];
		Set<Integer> set = new HashSet<Integer>();
	
		for(int i = 0; i < m; i++){
			P1 p1 = new P1();
			p1.x = sc.nextInt();
			p1.y = sc.nextInt();
			p1s[i] = p1;
			set.add(p1.x);
		}
		for(int i = 0;i < n; i++){
			P1 p2 = new P1();
			p2.x = sc.nextInt();
			p2.y = sc.nextInt();
			p2s[i] = p2;
			set.add(p2.x);
		}
		Integer[] xs = set.toArray(new Integer[0]);
		Arrays.sort(xs);
		
		double ans = 0;
		for(int i = 0; i < xs.length-1; i++){
			double b = xs[i+1];
			double a = xs[i];
			double c = (b + a) / 2;
			double fb = width(b, 1) * width(b, 2);
			double fa = width(a, 1) * width(a, 2);
			double fc = width(c, 1) * width(c, 2);
			ans += (b - a) * (fb + 4 * fc + fa) / 6;
			
		}
		
		System.out.println(ans);
	}
	
	double width(double x, int t){
		P1[] ps = null;
		List<Double> list = new ArrayList<Double>();
		if(t == 1){
			ps = p1s;
		}
		else{
			ps = p2s;
		}
		
		for(int i = 0; i < ps.length; i++){
			P1 p = ps[i];
			P1 pn = ps[(i+1 ) % ps.length];
			
			int min = Math.min(p.x, pn.x);
			int max = Math.max(p.x, pn.x);
			if(x == p.x){
				list.add((double)p.y);
			}
			if(x > min && x < max){
				
				double y = p.y + ((double)pn.y - p.y) * (double)(x - p.x) / (pn.x - p.x);
				list.add(y);
			}
		}
		if(list.size() == 0 || list.size() == 1){
			return 0;
		}
		else{
			double dist = list.get(0) - list.get(1);
			return Math.abs(dist);
		}
		

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
		AOJ1313 t = new AOJ1313();
		while(true){
			m = sc.nextInt();
			n = sc.nextInt();
			if(n == 0 && m == 0){
				break;
			}
			t.solve();	
		}		
	}
}
