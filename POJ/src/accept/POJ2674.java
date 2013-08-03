package accept;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ2674 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{
		double L = sc.nextDouble();
		double V = sc.nextDouble();
		

		String[] name = new String[N];
		A[] as = new A[N];
		for(int i = 0; i < N; i++){
	
			String s = sc.next().toLowerCase();
			A a = new A();
			if(s.equals("n")){
				a.right = false;
			}
			else if(!s.equals("p")){
				throw new Exception("test");
			}
			double pos = sc.nextDouble();
			name[i] = sc.next();
			double len = 0;
			if(a.right){
				len = L - pos;
			}
			else{
				len = pos;
			}
			a.time = len / V;
			as[i] = a;
		}
	
		Arrays.sort(as, new Comparator<A>() {
			public int compare(A o1, A o2) {
				if(o1.time == o2.time){
					return 0;
				}
				if(o1.time < o2.time){
					return -1;
				}
				else{
					return 1;
				}
			}
		});
		boolean lastRight = as[as.length-1].right;
		int cnt = 0;
		for(int i = 0; i < as.length; i++){
			if(as[i].right == lastRight){
				cnt++;
			}
		}
		int last = -1;
		if(lastRight){
			last = N-cnt;
		}
		else{
			last = cnt-1;
		}
		String nm = name[last];
		double time = as[as.length-1].time ;
		int sei = (int)time;
		int shou = (int)(time * 100) % 100;
		String S = Integer.toString(sei);
		while(S.length() < 10){
			S = " " + S;
		}
		String S2 = Integer.toString(shou);
		while(S2.length() < 2){
			S2 = "0" + S2;
		}
		S = S + "." + S2;
		out.println(S + " " + nm);
	
	}
	
	class A{
		double time = 0;
		boolean right = true;
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
		POJ2674 p = new POJ2674();
	
		while(true){
			try{
				N = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if( N == 0) break;
			p.solve();
		}
	}

}
