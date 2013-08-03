package accept;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ2549 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int S = 0;
	public void solve() throws Exception{
		int[] d = new int[S];
		for(int i = 0; i < S; i++){
			d[i] = sc.nextInt();
		}
		Arrays.sort(d);
		int ans = Integer.MIN_VALUE;
	
		if(S < 4){
			out.println("no solution");
			return;
		}
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < S-1; i++){
			for(int j = i+1; j < S; j++){
				int m = d[j] + d[i];
				if(!map.containsKey(m)){
					List<Integer> list = new ArrayList<Integer>();
					map.put(m, list);
				}
				map.get(m).add(d[i]);
				map.get(m).add(d[j]);
			}
		}
		
		for(int i = 0; i < S ; i++){
			for(int j = 0; j < S; j++){
				if( i == j) continue;
				int m = d[i] - d[j];
				if(map.containsKey(m)){
					List<Integer> list = map.get(m);
					for(int k = 0; k < list.size(); k+=2){
						int a = list.get(k);
						int b = list.get(k+1);
						if(d[i] != a && d[i] != b && d[j] != a && d[j] != b){
							ans = max(ans, d[i]);
						}
					}
					
				}
			}
		}
		if(ans == Integer.MIN_VALUE){
			out.println("no solution");
		}
		else{
			out.println(ans);
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
		POJ2549 p = new POJ2549();
	
		while(true){
			try{
				S = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if( S == 0) break;
			p.solve();
		}
	}

}
