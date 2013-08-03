package oldfailure;
import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ3691 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	int[] d = null;
	Set<Integer> set = null;
	int size = 0;
	String T = null;
	String[] ds = null;
	

	int[][] next = null;
	String[] cs = {"A", "T", "G", "C"};
	
	public void solve() throws Exception{
		ds = new String[n];
		Set<String> nextS = new HashSet<String>();
		Set<String> set = new HashSet<String>();
		for(int i = 0; i < n; i++){
			String s = sc.next();
			ds[i] = s;
			set.add(s);
		}
		T = sc.next();
		for(int i = 0; i < ds.length; i++){
			String s = ds[i];
			for(int j = 1; j < s.length(); j++){
				String cut = s.substring(0, j);
				if(!set.contains(cut)){
					nextS.add(cut);
				}
			}
		}
		next = new int[nextS.size()+1][4];
		Map<String, Integer> nextM = new HashMap<String, Integer>();
		String[] nexta = new String[nextS.size()+1];
		nexta[0] = "";
		nextM.put("", 0);
		int idx = 1;
		for(String s : nextS){
			nextM.put(s, idx);
			nexta[idx++] = s;
		}
		
		for(int i = 0; i < nexta.length; i++){
			for(int j = 0; j < cs.length; j++){
				String ns = nexta[i] + cs[j];
				while(ns.length() > 0){
					if(set.contains(ns)){
						next[i][j] = -1;
						break;
					}
					else if(nextM.containsKey(ns)){
						next[i][j] = nextM.get(ns);
						break;
					}
					ns = ns.substring(1);
				}
				if(ns.length() == 0){
					next[i][j] = 0;
				}
			}
		}
		
		int[][] dp = new int[T.length() + 1][nexta.length];
		for(int i = 0; i < T.length() + 1; i++){
			for(int j = 0; j < nexta.length; j++){
				dp[i][j] = INF;
			}
		}
		dp[0][0] = 0;
		for(int i = 1; i < T.length() + 1; i++){
			String s = T.substring(i-1, i);
			for(int j = 0; j < cs.length; j++){
				int add = 0;
				if(!s.equals(cs[j])){
					add = 1;
				}
				for(int k = 0; k < nexta.length; k++){
					int nexti = next[k][j];
					if(nexti != -1){
						dp[i][nexti] = Math.min(dp[i-1][k] + add, dp[i][nexti]);
					}
				}
			}
		}
	
		
		int ans = INF;
		for(int i = 0; i < nexta.length; i++){
			ans = Math.min(ans, dp[T.length()][i]);
		}
		
		
		if(ans < INF){
			System.out.println(ans);
		}
		else{
			System.out.println("-1");
		}
		
		
	}
	
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		POJ3691 t = new POJ3691();
		int cs = 1;
		while(true){
			n = sc.nextInt();
			if(n == 0){
				break;
			}
			System.out.print("Case " + cs++ + ": ");
			t.solve();
		}
	}


}