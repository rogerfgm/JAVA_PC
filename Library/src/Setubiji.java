import java.util.Arrays;
import java.util.Comparator;

/**
 * read Main function
 * 1. copy this class as inner class
 * 2. new this class
 * 3. call constract_sa and return is setubiji
 * @author dfukuda
 *
 */
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
	// compare irank[i], rank[i+k]�j and (rank[j], rank[j+k])
	Comparator<Integer> comp = new Comparator<Integer>() {

		public int compare(Integer i, Integer j) {
			if(rank[i] != rank[j]) return rank[i] - rank[j];
			int ri = i + k <= n ? rank[i + k] : -1;
			int rj = j + k <= n ? rank[j + k] : -1;
			return ri - rj;
		}
	};
	
	
	Integer[] constract_sa(String S){
		n = S.length();
		sa = new Integer[n+1];
		
		//initial oen charactor. rank is charactor code
		for(int i = 0; i <= n; i++){
			sa[i] = i;
			rank[i] = i < n ? S.charAt(i) : -1;
		}
		
		//sort with 2k charactors (already sorted with k charactors)
		for(k = 1; k <= n; k*= 2){
			Arrays.sort(sa, comp);
			
			// create new rank in tmp array and then copy
			tmp[sa[0]] = 0;
			for(int i = 1; i <= n; i++){
				tmp[sa[i]] = tmp[sa[i-1]] + (compare_sa(sa[i-1], sa[i]) < 0 ? 1 : 0);
			}
			for(int i = 0; i <= n; i++){
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "abrakatabra";
		Setubiji t = new Setubiji();
		Integer[] sa = t.constract_sa(s);
		for(int i = 0; i < sa.length; i++){
			System.out.println(i + " " + sa[i] + " " +  s.substring(sa[i]));
		}
		Integer[] lcp = t.construct_lcp(s, sa);
		for(int i = 0; i < lcp.length; i++){
			System.out.println(i + " " + lcp[i]);
		}
	}

}
