package done.d161;


import java.util.*;
import java.io.*;


public class D {
	
	//int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int n = 0;
	int m = 0;
	int k = 0;
	boolean[][] path = null;
	List<List<Integer>> vs = new ArrayList<List<Integer>>();
	boolean[] used = null;
	public void solve() throws Exception{

		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		path = new boolean[n+1][n+1];
		for(int i = 0; i <= n; i++){
			List<Integer> l = new ArrayList<Integer>();
			vs.add(l);
		}
		for(int i = 0; i < m; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			path[f][t] = true;
			path[t][f] = true;
			vs.get(f).add(t);
			vs.get(t).add(f);
		}
		
		used = new boolean[n+1];
		
		check(1, 1, k+1);
		//System.out.println("1");
	}
	
	boolean check(int goal, int pos, int rem){
		used[pos] = true;
		for(int to : vs.get(pos)){
			if(to == goal){
				if(rem <= 1){
					int num = k + 2 - rem;
					System.out.println(num);
					System.out.print(pos);
					return true;
				}
			}
			else{
				if(!used[to]){
					boolean ret = check(goal, to, rem-1);
					if(ret){
						System.out.print(" " + pos );
						return true;
					}
				}
			}
			
		}
		used[pos] = false;
		
		return false;
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
