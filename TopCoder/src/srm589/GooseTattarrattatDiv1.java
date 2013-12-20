package srm589;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class GooseTattarrattatDiv1 {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;

	Set<Integer> set = null;
	List<Integer> list = null;
	String s = null;
	
	public int getmin(String S){
		s = S;
		N = S.length();
		int ans = 0;
		
		boolean[] used = new boolean[N];

		for(int i = 0; i < N; i++){
			if(used[i]) continue;
			int pair = N - i - 1;
			if(S.charAt(i) != S.charAt(pair)){
				
				Set<Character> set = new HashSet<Character>();
				get(S.charAt(i), set);
				Map<Character, Integer> map = new HashMap<Character, Integer>();
				for(int j = 0; j < N; j++){
					if(set.contains(s.charAt(j))){
						if(!map.containsKey(s.charAt(j))){
							map.put(s.charAt(j), 0);
						}
						int cnt = map.get(s.charAt(j)) + 1;
						map.put(s.charAt(j), cnt);
					}
				}
				int[] num = new int[map.size()];
				int idx = 0;
				for(Character key : map.keySet()){
					num[idx++] = map.get(key);
				}
				Arrays.sort(num);
				for(int j = 0; j < num.length-1; j++){
					ans += num[j];
				}
				for(int j = 0; j < N; j++){
					if(set.contains(s.charAt(j))){
						used[j] = true;
					}
				}
			}
			
		}


		
		return ans;
	}
	
	void get(char c, Set<Character> set){
		if(set.contains(c)){
			return;
		}
		set.add(c);
		for(int i = 0; i < N; i++){
			if(s.charAt(i) == c){
				int pair = N - i - 1;
				get(s.charAt(pair), set);
			}
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GooseTattarrattatDiv1 t = new GooseTattarrattatDiv1();
		String s = "jibmizhufhpcdwzogczhhzwgptrzljud";
		int r = t.getmin(s);
		out.println(r);
	}

}
