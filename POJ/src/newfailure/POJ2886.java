package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA
public class POJ2886 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int K = 0;
	public void solve() throws Exception{
		
		
		// ここは決まっているから事前に計算しちゃう嘘解放。
		int[] koudo = {1,2,4,6,12,24,36,48,60,120,180,240,360,720,840,1260,1680, 2520,5040,7560,10080,15120,20160,25200,27720,45360,50400,55440,83160,110880,166320,221760,277200,332640,498960,554400};
		int ans = 0;
		int aidx = 0;
		for(int i = 0; i < koudo.length-1; i++){
			if(N < koudo[i+1]){
				ans = num(koudo[i]);
				aidx = koudo[i];
				break;
			}
		}
		
		
	
		
		List<Data> list = new ArrayList<Data>();
		for(int i = 1; i <= N; i++){
			Data data = new Data();
			data.name = sc.next();
			data.p = sc.nextInt();
			list.add(data);
			
//			int num = 0;
//			for(int j = 1; j * j <= i; j++){
//				if(i % j == 0){
//					num++;
//					if(j != i / j) num++;
//				}
//			}
//			if(num > ans){
//				ans = num;
//				aidx = i;
//			}
		}
		
		
		int now = 0;
		for(int i = 1; i <= N; i++){
			if(K > 0){
				now += K-1;
				now %= list.size();
			}
			else{
				now -= K;
				if(now < 0){
					int tmp = -now;
					int size = tmp / list.size() + 1;
					now += list.size() * size;
				}
				
				now %= list.size();
			}
			Data d = list.remove(now);
			K = d.p;
			
			if(i == aidx){
				out.println(d.name + " " + ans);
			}
		}
		
		
	}
	
	int num(int n){
		int ret = 0;
		for(int i = 1; i * i <= n; i++){
			if(n % i == 0){
				ret++;
				if(i != n / i) ret++;
			}
		}
		return ret;
	}
	
	class Data{
		String name = "";
		int p = 0;
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
		POJ2886 p = new POJ2886();

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
