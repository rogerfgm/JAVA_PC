package accept;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ2100 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static long n = 0;
	
	public void solve() throws Exception{
		long min = 1;
		long max = 1;
		long sum = 1;
		while(true){
			long next = max + 1;
			if(sum + next * next > n){
				break;
			}
			sum += next * next;
			max = next;
		}
		List<int[]> ans = new ArrayList<int[]>();
		while(true){
			if(sum == n){
				int num = (int)(max - min + 1);
				int[] a = new int[num+1];
				a[0] = num;
				for(int i = 1; i < a.length; i++){
					a[i] = (int)(min + i -1);
				}
				ans.add(a);
			}
			if(sum > n){
				sum -= min * min;
				min++;
			}
			else{
				max++;
				if(max * max > n){
					break;
				}
				sum += max * max;
			}
		}
		out.println(ans.size());
		for(int i = 0; i < ans.size(); i++){
			int[] a = ans.get(i);
			for(int j = 0; j < a.length; j++){
				if(j != 0){
					out.print(" ");
				}
				out.print(a[j]);
			}
			out.println();
		}
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
		POJ2100 p = new POJ2100();

		while(true){
			try{
				n = sc.nextLong();
			}
			catch(Exception ex){
				break;
			}
			
			p.solve();
		}
	}
	
}
