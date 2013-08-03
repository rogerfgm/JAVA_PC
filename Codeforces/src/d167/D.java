package d167;

import java.util.*;
import java.io.*;


public class D {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	
	public void solve() throws Exception{

		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = sc.nextInt();
		}
		for(int i = 0;i < n; i++){
			b[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		
	
	
		Set<Integer> same = new HashSet<Integer>();
		for(int i = 0; i < n; i++){
			if(a[i] == b[i]){
				same.add(a[i]);
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < n; i++){
			if(map.containsKey(a[i])){
				int cnt = map.get(a[i]) + 1;
				map.put(a[i], cnt);
			}
			else{
				map.put(a[i], 1);
			}
			if(map.containsKey(b[i])){
				int cnt = map.get(b[i]) + 1;
				map.put(b[i], cnt);
			}
			else{
				map.put(b[i], 1);
			}
		}
		
		long ans = 1;
		for(int key : map.keySet()){
			if(map.get(key).intValue() == 1){
				continue;
			}
			else{
				int cnt = map.get(key);
				boolean iss = same.contains(key);
				if(iss){
					ans *= nc(cnt, 2);
					while(ans % 2 == 0 && m %2 == 0 && m > 0){
						ans /= 2;
						m /= 2;
					}
					ans %= m;
					if(cnt > 2){
						ans *= kaijyou(cnt - 2, m);
						if(ans % 2 == 0 && m %2 == 0 && m > 0){
							ans /= 2;
							m /= 2;
						}
						ans %= m;
					}
				}
				else{
					ans *= kaijyou(cnt, m);
					while(ans % 2 == 0 && m %2 == 0 && m > 0){
						ans /= 2;
						m /= 2;
					}
					ans %= m;
				}
			}
		}
		ans %= m;
		System.out.print(ans);
		
	}
	
	long kaijyou(long n, long m){
		long ret = 1;
		while(n > 1){
			ret *= n;
			while(ret % 2 == 0 && m % 2 == 0 && m > 0){
				ret /= 2;
				m /= 2;
			}
			ret %= m;
			n--;
		}
		return ret;
	}
	
	long nc(long n, long c){
		if(n < c){
			return 0;
		}
		if(n-c < c){
			c = n-c;
		}
		
		long ans = 1;
		long t = c;
		while(t >= 1){
			ans *= n;
			n--;
			t--;
		}
		
		while(c > 1){
			ans /= c;
			c--;
		}
		
		return ans;
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
		D t = new D();
		t.solve();

	}
	
	
}