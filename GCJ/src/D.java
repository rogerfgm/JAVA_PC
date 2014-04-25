

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class D {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		String s = br.readLine();
		N = parseInt(s);
		s = br.readLine();
		String[] sp = s.split(" ");
		double[] nao = new double[N];
		double[] ken = new double[N];
		for(int i = 0; i < N; i++){
			nao[i] = Double.parseDouble(sp[i]);
		}
		s = br.readLine();
		sp = s.split(" ");
		for(int i = 0; i < N; i++){
			ken[i] = Double.parseDouble(sp[i]);
		}
		Arrays.sort(nao);
		Arrays.sort(ken);
		boolean[] used = new boolean[N];
		int opt = 0;
		for(int i = 0; i < N; i++){
			int last = N - i;
			int ni = i;
			int ki = 0;
			boolean flag = true;
			for(; ni < N; ni++, ki++){
				if(nao[ni] < ken[ki]){
					flag = false;
				}
			}
			if(flag){
				opt = N - i;
				break;
			}
		}
		int normal = 0;
		used = new boolean[N];
		for(int i = 0; i < N; i++){
			boolean f = false;
			int jidx = -1;
			for(int j = 0; j < N; j++){
				if(!used[j] && nao[i] < ken[j]){
					f = true;
					jidx = j;
					break;
				}
				else if(jidx < 0 && !used[j]){
					jidx = j;
				}
			}
			used[jidx] = true;
			if(!f){
				normal++;
			}
		}
		out.println(opt + " " + normal);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("D-large.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		D b = new D();
		int T = 0;
		if(sc != null){
			T = sc.nextInt();
		}
		else{
			T = parseInt(br.readLine());
		}
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
