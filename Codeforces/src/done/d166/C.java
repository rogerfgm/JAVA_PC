package done.d166;
import java.io.*;
import java.util.*;







public class C {
	static Scanner sc = null;
	
	
	
	
	public void solve() throws Exception{

		int n = sc.nextInt();
		int k = sc.nextInt();
		if(n / k < 3){
			System.out.println("-1");
			return;
		}
		int[] ans = new int[n+1];
		int mul = 0;
		while(true){
			int now = mul * k + 1;
			for(int i = 0; i < k; i++){
				if(now + i > n){
					for(int j = 1; j <= n; j++){
						if(j != 1){
							System.out.print(" ");
						}
						System.out.print(ans[j]);
					}
					return;
				}
				ans[now + i] = (k + mul + i) % k + 1;
			}
			
			mul++;
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
		C t = new C();
		t.solve();

	}
	
	
}
