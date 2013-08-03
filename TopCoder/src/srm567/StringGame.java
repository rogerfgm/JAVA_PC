package srm567;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class StringGame {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	List<Map<String, Integer>> list = null;
	public int[] getWinningStrings(String[] S) {
		list = new ArrayList<Map<String, Integer>>();
		for(int i = 0; i < S.length; i++){
			String s = S[i];
			Map<String, Integer> map = new HashMap<String, Integer>();
			for(int j = 0; j < s.length(); j++){
				String t = s.substring(j, j+1);
				int num = 1;
				if(map.containsKey(t)){
					num = map.get(t) + 1;
				}
				map.put(t, num);
			}
			list.add(map);
		}
		List<Integer> ans = new ArrayList<Integer>();
		
		for(int i = 0; i < list.size(); i++){
			Set<Integer> ends = new HashSet<Integer>();
			Set<String> used = new HashSet<String>();
			while(true){
				boolean flag = false;
				for(String s : list.get(i).keySet()){
					if(used.contains(s)) continue;
					int ret = check(ends, s, i, false);
					if(ret == 2){
						ans.add(i);
						break;
					}
					else if(ret == 1){
						check(ends, s, i, true);
						flag = true;
						used.add(s);
						break;
					}
				}
				if(!flag){
					break;
				}
			}
		}
		
		int[] a = new int[ans.size()];
		for(int i = 0; i < ans.size(); i++){
			a[i] = ans.get(i);
		}
		return a;
	}
	
	int check( Set<Integer> ends, String s, int idx, boolean add){
		
		boolean f2 = true;
		boolean f1 = true;
		Map<String, Integer> map = list.get(idx);
		for(int i = 0; i < list.size(); i++){
			if(i == idx) continue;
			if(ends.contains(i)) continue;
			Map<String, Integer> cmap = list.get(i);
			if(!cmap.containsKey(s)){
				if(add) ends.add(i);
				continue;
			}
			if(cmap.get(s) >= map.get(s)){
				f2 = false;
				if(cmap.get(s) > map.get(s)){
					f1 = false;
				}
			}
			else{
				if(add) ends.add(i);
			}
		}
		if(f2) return 2;
		if(f1) return 1;
		return 0;
		
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringGame t = new StringGame();
		String[] S =  {"a", "b", "c", "d"};
		int[] ret = t.getWinningStrings(S);
		for(int i : ret){
			out.println(i);
		}
		
	}

}
