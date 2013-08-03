package oldfailure;
import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ3581 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int n = 0;

	public void solve() throws Exception{
		n = sc.nextInt();
		int[] d = readIntArray(n);
		int a1 = 0;
		for(int i = 1; i < n -2; i++){
			if(d[i] <= d[i-1]){
				a1 = i;
			}
			else{
				break;
			}
		}
		
		int[] nokori = new int[n-a1 -1];
		for(int i = 0; i < n - a1 -1; i++){
			nokori[i] = d[d.length - 1 - i];
		}
		Setubiji st = new Setubiji();
		Integer[] sa = st.constract_sa(nokori);
		int a2 = 0;
		if(sa[0] == 0){
			a2 = a1 + nokori.length - sa[1];
		}
		else{
			a2 = a1 + nokori.length - sa[0];
		}
		for(int i = a1; i >= 0; i--){
			System.out.println(d[i]);
		}
		for(int i = a2 ; i > a1; i--){
			System.out.println(d[i]);
		}
		for(int i = d.length-1; i > a2; i--){
			System.out.println(d[i]);
		}
	}
	
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    public class Setubiji {

    	final int MAX_N = 100000;
    	int[] rank = new int[MAX_N+1];
    	int[] tmp = new int[MAX_N+1];
    	public Integer[] sa = null;
    	public Integer[] lcp = null;
    	int n = 0;
    	int k = 0;
    	
    	
    	int compare_sa(int i, int j){
    		if(rank[i] != rank[j]) return rank[i] - rank[j];
    		int ri = i + k <= n ? rank[i + k] : -1;
    		int rj = j + k <= n ? rank[j + k] : -1;
    		return ri - rj;
    	}
    	// compare （rank[i], rank[i+k]） and (rank[j], rank[j+k])
    	Comparator<Integer> comp = new Comparator<Integer>() {

    		@Override
    		public int compare(Integer i, Integer j) {
    			if(rank[i] != rank[j]) return rank[i] - rank[j];
    			int ri = i + k <= n ? rank[i + k] : -1;
    			int rj = j + k <= n ? rank[j + k] : -1;
    			return ri - rj;
    		}
    	};
    	
    	
    	Integer[] constract_sa(int[] S){
    		n = S.length;
    		sa = new Integer[n];
    		
    		//initial oen charactor. rank is charactor code
    		for(int i = 0; i < n; i++){
    			sa[i] = i;
    			rank[i] = S[i];
    		}
    		
    		//sort with 2k charactors (already sorted with k charactors)
    		for(k = 1; k <= n; k*= 2){
    			Arrays.sort(sa, comp);
    			
    			// create new rank in tmp array and then copy
    			tmp[sa[0]] = 0;
    			for(int i = 1; i < n; i++){
    				tmp[sa[i]] = tmp[sa[i-1]] + (compare_sa(sa[i-1], sa[i]) < 0 ? 1 : 0);
    			}
    			for(int i = 0; i < n; i++){
    				rank[i] = tmp[i];
    			}
    		}
    		return sa;
    	}
    	
    	/**
    	 * 
    	 * @param S target
    	 * @param sa setubiji hairetu
    	 */
    	Integer[] construct_lcp(String S, Integer[] sa){
    		this.sa = sa;
    		n = S.length();
    		lcp = new Integer[n+1];
    		
    		for(int i = 0; i <= n; i++) rank[sa[i]] = i;
    		int h = 0;
    		lcp[0] = 0;
    		for(int i = 0; i < n; i++){
    			int j = sa[rank[i]-1];
    			
    			if(h > 0) h--;
    			for(; j + h < n && i + h < n; h++){
    				if(S.charAt(j+h) != S.charAt(i+h)) break;
    			}
    			lcp[rank[i]-1] = h;
    		}
    		return lcp;
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
		POJ3581 t = new POJ3581();
		
		t.solve();
		
	}


}