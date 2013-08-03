package d167;
import java.io.*;
import java.util.*;


public class B {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int MAX = 200001;
	Map<Integer, Integer> fs = null;
	public void solve() throws Exception{

		int n = sc.nextInt();
		int[] d = new int[n];
		for(int i = 0; i < n; i++){
			d[i]= sc.nextInt();
		}
		Arrays.sort(d);

		fs = new HashMap<Integer, Integer>();
		
		fs.put(0, 0);
		for(int i = 0; i < d.length; i++){
			if(!fs.containsKey(d[i])){
				int num = getf(d[i]);
				fs.put(d[i], num);
			}
		}
	
	
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < d.length; i++){
			int num = d[i];
			if(map.containsKey(fs.get(num))){
				int cnt = map.get(fs.get(num)) + 1;
				map.put(fs.get(num), cnt);
			}
			else{
				map.put(fs.get(num), 1);
			}
		}
		long ans = 0;
		for(int key : map.keySet()){
			int cnt = map.get(key);
			if(cnt > 1){
				ans += nc(cnt, 2);
			}
		}
		System.out.println(ans);
		
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
	
	int getf(int num){
		if(fs.containsKey(num)){
			return fs.get(num);
		}
		if(num % 2 == 1){
			return getf(num / 2) + 1;
		}
		else{
			return getf(num / 2);
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
		B t = new B();
		t.solve();

	}
	
	
}
